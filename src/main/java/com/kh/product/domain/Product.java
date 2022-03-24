package com.kh.product.domain;

import lombok.Data;

@Data
public class Product {
    Long product_num;        // 상품번호 number(10),
    String product_name;     // 상품명   varchar2(40),
    Long product_cnt;        // 상품수량 number(10) DEFAULT 0,
    Long product_pri;        // 상품가격 number(10) DEFAULT 0
}
