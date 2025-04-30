package com.cathaybank.homework.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<Currency> list() { 
        return repo.findAll(); 
    }


    //新增
    @PostMapping
    public Currency create(@RequestBody Currency currency) { 
        return repo.save(currency); 
    }
    

    //修改
    @PutMapping("/{id}")
    public Currency update(@PathVariable Integer id, @RequestBody Currency currency) {
        currency.setId(id);
        return repo.save(currency);
    }

    
    //刪除
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) { 
        repo.deleteById(id); 
    }
}
