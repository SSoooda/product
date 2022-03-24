package com.kh.product.domain.dao;

import com.kh.product.domain.Product;

import java.util.List;

public interface ProductDAO {

    /**
     * 등록
     * @param product
     * @return
     */
    Product insert (Product product);

    /**
     * 전체조회
     * @return
     */
    List<Product> findAll();

    /**
     * 조회
     * @param product_num
     * @return
     */
    Product findByProduct_num(Long product_num);

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
