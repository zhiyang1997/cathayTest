package com.cathaybank.homework.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cathaybank.homework.dto.ApiResponseDto;
import com.cathaybank.homework.entity.Currency;
import com.cathaybank.homework.reponsitory.CurrencyRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/currencies")
public class CurrencyController {

    @Autowired
    private CurrencyRepository repo;
    
    // 查詢
    @GetMapping
    public ResponseEntity<ApiResponseDto<List<Currency>>> list() { 
        try {
            List<Currency> currencies = repo.findAll();
            return ResponseEntity.ok(new ApiResponseDto<>("查詢成功", currencies, "SECCESS"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ApiResponseDto<>("查詢失敗: " + e.getMessage(), null, "FAILED"));
        }
    }


    // 新增
    @PostMapping
    public ResponseEntity<ApiResponseDto<Currency>> create(@RequestBody Currency currency) { 
        try {
            Currency saved = repo.save(currency);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponseDto<>("新增成功", saved, "SUCCESS"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ApiResponseDto<>("新增失敗: " + e.getMessage(), null, "FAILED"));
        }
    }
    
    // 修改
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<Currency>> update(@PathVariable Integer id, @RequestBody Currency currency) {
        try {
            // 檢查記錄是否存在
            if (!repo.existsById(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponseDto<>("修改失敗: 找不到 ID 為 " + id + " 的資料", null, "FAILED"));
            }
            
            currency.setId(id);
            Currency updated = repo.save(currency);
            return ResponseEntity.ok(
                new ApiResponseDto<>("修改成功", updated, "SUCCESS"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ApiResponseDto<>("修改失敗: " + e.getMessage(), null, "FAILED"));
        }
    }
    
    // 刪除
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<Void>> delete(@PathVariable Integer id) { 
        try {
            // 檢查記錄是否存在
            if (!repo.existsById(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponseDto<>("刪除失敗: 找不到 ID 為 " + id + " 的資料", null, "FAILED"));
            }
            
            repo.deleteById(id);
            return ResponseEntity.ok(
                new ApiResponseDto<>("刪除成功", null, "SUCCESS"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ApiResponseDto<>("刪除失敗: " + e.getMessage(), null, "FAILED"));
        }
    }
}
