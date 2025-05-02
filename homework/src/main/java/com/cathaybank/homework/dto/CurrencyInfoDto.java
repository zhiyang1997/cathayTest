package com.cathaybank.homework.dto;

import java.math.BigDecimal;

public class CurrencyInfoDto {
    private String code;
    private String name;
    private BigDecimal rate;

    /** getter */
    public String getCode() { 
        return code; 
    }
    public String getName() { 
        return name; 
    }
    public BigDecimal getRate() { 
        return rate; 
    }

    /** setter */
    public void setCode(String code) {
        this.code = code;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
