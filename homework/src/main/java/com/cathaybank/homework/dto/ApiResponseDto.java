package com.cathaybank.homework.dto;

import java.util.Date;

public class ApiResponseDto<T> {

    private String message;
    private T data;
    private String status;
    private Date timestamp;

    public ApiResponseDto(String message, T data, String status) {
        this.message = message;
        this.data = data;
        this.status = status;
        this.timestamp = new Date();
    }

    /** getter */
    public String getMessage() { 
        return message; 
    }
    public T getData() { 
        return data; 
    }
    public String getStatus() { 
        return status; 
    }
    public Date getTimestamp() { 
        return timestamp; 
    }


    /** setter */
    public void setMessage(String message) { 
        this.message = message; 
    }
    public void setData(T data) { 
        this.data = data; 
    }
    public void setStatus(String status) { 
        this.status = status; 
    }
    public void setTimestamp(Date timestamp) { 
        this.timestamp = timestamp; 
    }
}
