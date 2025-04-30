package com.cathaybank.homework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cathaybank.homework.dto.CoindeskTransformedDto;
import com.cathaybank.homework.service.CoindeskService;

@RestController
@RequestMapping("/coindesk")
public class CoindeskController {
     @Autowired
    private CoindeskService service;

    // 呼叫 coindesk API 
    @GetMapping("/raw")
    public String getRaw() {
        return service.fetchRaw();
    }


    // 資料轉換重組API
    @GetMapping("/transformed")
    public CoindeskTransformedDto getTransformed() {
        return service.fetchAndTransform();
    }
}
