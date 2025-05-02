package com.cathaybank.homework.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import com.cathaybank.homework.dto.CoindeskTransformedDto;
import com.cathaybank.homework.entity.Currency;
import com.cathaybank.homework.reponsitory.CurrencyRepository;


@ExtendWith(MockitoExtension.class)
public class CoindeskServiceTest {

    @Mock
    private CurrencyRepository currencyRepository;
    
    @Mock
    private RestTemplate restTemplate;
    
    @InjectMocks
    private CoindeskService coindeskService;
    
    private String mockCoindeskResponse;
    
    @BeforeEach
    void setUp() {
        // 1. 準備測試用的 Coindesk API 回應
        mockCoindeskResponse = "{"
            + "\"time\": {"
                + "\"updated\": \"Sep 2, 2024 07:07:20 UTC\","
                + "\"updatedISO\": \"2024-09-02T07:07:20+00:00\","
                + "\"updateduk\": \"Sep 2, 2024 at 08:07 BST\""
            + "},"
            + "\"disclaimer\": \"just for test\","
            + "\"chartName\": \"Bitcoin\","
            + "\"bpi\": {"
                + "\"USD\": {"
                    + "\"code\": \"USD\","
                    + "\"symbol\": \"&#36;\","
                    + "\"rate\": \"57,756.298\","
                    + "\"description\": \"United States Dollar\","
                    + "\"rate_float\": 57756.2984"
                + "},"
                + "\"GBP\": {"
                    + "\"code\": \"GBP\","
                    + "\"symbol\": \"&pound;\","
                    + "\"rate\": \"43,984.02\","
                    + "\"description\": \"British Pound Sterling\","
                    + "\"rate_float\": 43984.0203"
                + "},"
                + "\"EUR\": {"
                    + "\"code\": \"EUR\","
                    + "\"symbol\": \"&euro;\","
                    + "\"rate\": \"52,243.287\","
                    + "\"description\": \"Euro\","
                    + "\"rate_float\": 52243.2865"
                + "}"
            + "}"
        + "}";
        
        // 2. 設置 RestTemplate 模擬回傳值
        lenient().when(restTemplate.getForObject(anyString(), eq(String.class)))
        .thenReturn(mockCoindeskResponse);
        
        // 3. 設置 CurrencyRepository 模擬回傳值
        Currency usdCurrency = new Currency();
        usdCurrency.setCode("USD");
        usdCurrency.setCodeName("美元");
        
        Currency gbpCurrency = new Currency();
        gbpCurrency.setCode("GBP");
        gbpCurrency.setCodeName("英鎊");
        
        Currency eurCurrency = new Currency();
        eurCurrency.setCode("EUR");
        eurCurrency.setCodeName("歐元");
        
        when(currencyRepository.findByCode("USD")).thenReturn(Optional.of(usdCurrency));
        when(currencyRepository.findByCode("GBP")).thenReturn(Optional.of(gbpCurrency));
        when(currencyRepository.findByCode("EUR")).thenReturn(Optional.of(eurCurrency));
    }
    @Test
    void testFetchAndTransform() {
        CoindeskTransformedDto result = coindeskService.fetchAndTransform();
        
        assertNotNull(result);
        assertEquals("2024/09/02 07:07:20", result.getUpdateTime());
        assertEquals(3, result.getCurrencies().size());
        
        // 驗證幣別資訊
        assertEquals("USD", result.getCurrencies().get(0).getCode());
        assertEquals("美元", result.getCurrencies().get(0).getCodeName());
        assertEquals(new BigDecimal("57756.2984"), result.getCurrencies().get(0).getRate());
        
        assertEquals("GBP", result.getCurrencies().get(1).getCode());
        assertEquals("英鎊", result.getCurrencies().get(1).getCodeName());
        assertEquals(new BigDecimal("43984.0203"), result.getCurrencies().get(1).getRate());
        
        assertEquals("EUR", result.getCurrencies().get(2).getCode());
        assertEquals("歐元", result.getCurrencies().get(2).getCodeName());
        assertEquals(new BigDecimal("52243.2865"), result.getCurrencies().get(2).getRate());
        
        verify(currencyRepository, times(1)).findByCode("USD");
        verify(currencyRepository, times(1)).findByCode("GBP");
        verify(currencyRepository, times(1)).findByCode("EUR");
    }
}
