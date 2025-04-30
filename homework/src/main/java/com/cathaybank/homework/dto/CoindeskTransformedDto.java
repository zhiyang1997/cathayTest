package com.cathaybank.homework.dto;

import java.util.ArrayList;
import java.util.List;

public class CoindeskTransformedDto {
    private String updateTime;
    private List<CurrencyInfoDto> currencies;

    /** getter */
    public String getUpdateTime() { 
        return updateTime; 
    }
    public List<CurrencyInfoDto> getCurrencies() { 
        return currencies; 
    }

    /** setter */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
    public void setCurrencies(List<CurrencyInfoDto> currencies) {
        this.currencies = new ArrayList<>(currencies);
    }
}
