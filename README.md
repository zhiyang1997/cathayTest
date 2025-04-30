國泰世華Java Engineer線上作業
使用的springboot版本是2.7.1 此決定的依據是: 網路上survey時看到 2.x → 3.x 會有不少坑 ➜ 保險起見 (也為了節省找bug的時間)，故使用springboot 2 的最後一版 2.7.18

各套件的maven版本是參考springboot官網的資料 https://docs.spring.io/spring-boot/docs/2.7.x/reference/html/dependency-versions.html

API呼叫之URL:

GET： http://localhost:8080/currencies/

POST： http://localhost:8080/currencies/　
PUT： http://localhost:8080/currencies/{id}
DELETE： http://localhost:8080/currencies/{id}


h2 console URL: http://localhost:8080/h2-console/
