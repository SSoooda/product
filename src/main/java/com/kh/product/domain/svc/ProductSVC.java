package com.kh.product.domain.svc;

import com.kh.product.domain.Product;

import java.util.List;

public interface ProductSVC {

    /**
     * 등록
     * @param product
     * @return
     */
    Product upload (Product product);

    /**
     * 전체조회
     * @return
     */
    List<Product> list();

    /**
     * 조회
     * @param product_num
     * @return
     */
    Product detail(Long product_num);

    /**
     * 삭제
     * @param product_num
     * @return
     */
    int delete(Long product_num);

    /**
     * 수정
     * @param product_num
     * @param product
     * @return
     */
    Product update(Long product_num,Product product);

}
