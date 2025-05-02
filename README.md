# 國泰世華Java Engineer線上作業

## 專案需求

- **Build Tool**：Maven
- **JDK**：8
- **Spring Boot**：2.7.18
- **資料庫**：H2（ORM 使用 Spring Data JPA）
- **API 來源**：[Coindesk API](https://kengp3.github.io/blog/coindesk.json)

---

## 功能簡述

1. 幣別資料表 CRUD API（查詢、新增、修改、刪除）
2. 呼叫 coindesk API 並顯示原始內容
3. 呼叫 coindesk API，進行資料轉換並回傳（含更新時間、幣別、中文名稱、匯率）

---

## API呼叫之URL

1. GET： http://localhost:8080/currencies/  
2. POST： http://localhost:8080/currencies/  
3. PUT： http://localhost:8080/currencies/{id}  
4. DELETE： http://localhost:8080/currencies/{id}  
5. 呼叫 coindesk API： http://localhost:8080/coindesk/raw  
6. 呼叫 coindesk API，進行資料轉換： http://localhost:8080/coindesk/transformed  

---

## 資料庫網址

h2 console URL: http://localhost:8080/h2-console/

---

## 建立資料庫語法
~~~~sql
CREATE TABLE currency (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    CODE NVARCHAR UNIQUE NOT NULL,
    CODENAME NVARCHAR NOT NULL
);

INSERT INTO currency (CODE, CODENAME) VALUES ('USD', '美元');
INSERT INTO currency (CODE, CODENAME) VALUES ('GBP', '英鎊');
INSERT INTO currency (CODE, CODENAME) VALUES ('EUR', '歐元');
~~~~


