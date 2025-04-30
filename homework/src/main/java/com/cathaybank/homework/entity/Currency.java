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
    private Integer id;
    
    @Column(name = "CODE", unique = true)
    private String code;

    @Column(name = "CODENAME")
    private String codeName;

    /** getter */
    public String getCode() { 
        return code; 
    }
    public String getCodeName() { 
        return codeName; 
    }
    public Integer getId() { 
        return id; 
    }

    /** setter */
    public void setCode(String code) { 
        this.code = code; 
    }
    public void setCodeName(String codeName) { 
        this.codeName = codeName; 
    }
    public void setId(Integer id) { 
        this.id = id; 
    }
}
