package com.cathaybank.homework.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CURRENCY_ID")
    private Integer id;
    
    @Column(name = "CURRENCY_CODE", unique = true)
    private String code;

    @Column(name = "CURRENCY_NAME")
    private String name;

    /** getter */
    public String getCode() { 
        return code; 
    }
    public String getName() { 
        return name; 
    }
    public Integer getId() { 
        return id; 
    }

    /** setter */
    public void setCode(String code) { 
        this.code = code; 
    }
    public void setName(String name) { 
        this.name = name; 
    }
    public void setId(Integer id) { 
        this.id = id; 
    }
}
