package com.cathaybank.homework.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cathaybank.homework.dto.CoindeskTransformedDto;
import com.cathaybank.homework.dto.CurrencyInfoDto;
import com.cathaybank.homework.entity.Currency;
import com.cathaybank.homework.reponsitory.CurrencyRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CoindeskService {

     private final RestTemplate restTemplate = new RestTemplate();


     @Autowired
     private CurrencyRepository currencyRepository;

     // 原始 API 呼叫
     public String fetchRaw() {
        return restTemplate.getForObject("https://kengp3.github.io/blog/coindesk.json", String.class);
     }

     // 資料轉換
     public CoindeskTransformedDto fetchAndTransform() {
      // 1.取得原始資料
      String rawJson = fetchRaw();

      // 2.解析 JSON
      ObjectMapper mapper = new ObjectMapper();
      JsonNode root;
      try {
         root = mapper.readTree(rawJson);
         
         // 3.建立回傳DTO
         CoindeskTransformedDto result = new CoindeskTransformedDto();

         // 4. 轉換時間格式 - 從 ISO 格式轉為 yyyy/MM/dd HH:mm:ss
            String timeISO = root.path("time").path("updatedISO").asText();
            DateTimeFormatter inputFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            
            LocalDateTime dateTime = LocalDateTime.parse(timeISO, inputFormatter);
            result.setUpdateTime(outputFormatter.format(dateTime));
            
            // 5. 提取幣別與匯率，並找出中文名稱
            List<CurrencyInfoDto> currencies = new ArrayList<>();
            JsonNode bpi = root.path("bpi");
            Iterator<String> fieldNames = bpi.fieldNames();
            
            while (fieldNames.hasNext()) {
                String code = fieldNames.next();
                JsonNode currencyNode = bpi.path(code);
                
                CurrencyInfoDto currencyInfo = new CurrencyInfoDto();
                currencyInfo.setCode(code);
                
                // 從資料庫查詢中文名稱
                Currency currency = currencyRepository.findByCode(code).orElse(null);
                currencyInfo.setCodeName(currency != null ? currency.getCodeName() : "");
                
                // 設定匯率
                currencyInfo.setRate(new BigDecimal(currencyNode.path("rate_float").asText()));
                
                currencies.add(currencyInfo);
            }
            
            result.setCurrencies(currencies);
            return result;
      } catch (Exception e) {
         throw new RuntimeException("Error transforming coindesk data", e);
      }
      
     }
}
