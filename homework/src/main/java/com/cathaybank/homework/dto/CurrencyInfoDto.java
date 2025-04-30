package com.cathaybank.homework.dto;

import java.math.BigDecimal;

public class CurrencyInfoDto {
    private String code;
    private String codeName;
    private BigDecimal rate;

    /** getter */
    public String getCode() { 
        return code; 
    }
    public String getCodeName() { 
        return codeName; 
    }
    public BigDecimal getRate() { 
        return rate; 
    }

    /** setter */
    public void setCode(String code) {
        this.code = code;
    }
    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
