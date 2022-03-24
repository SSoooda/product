package com.kh.product.domain.product.dao;

import com.kh.product.domain.Product;
import com.kh.product.domain.dao.ProductDAO;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class ProductDAOImplTest {

    @Autowired
    private ProductDAO productDAO;

    @Test
    @DisplayName("등록")
    void insert() {
        Product product = new Product();
        product.setProduct_name("휴지");
        product.setProduct_cnt(100L);
        product.setProduct_pri(15000L);

        //아마 등록성공하면 리스트 크기가 1 늘어날 것이다
        int before = productDAO.findAll().size();
        productDAO.insert(product);
        int after = productDAO.findAll().size();

        Assertions.assertThat(after).isEqualTo(before+1);
        log.info("before size= {}",before);
        log.info("after size= {}",after);

    }

    @Test
    @DisplayName("전체조회")
    void findAll() {
        List<Product> products = productDAO.findAll();
        Assertions.assertThat(products.size()).isEqualTo(1);
        log.info("products= {}",products);
    }

    @Test
    @DisplayName("조회")
    void findByProduct_num() {
        Long product_num = 6L;
        Product product = productDAO.findByProduct_num(product_num);
        Assertions.assertThat(product.getProduct_name()).isEqualTo("휴지");
        log.info("product= {}",product);
    }

    @Test
    @DisplayName("삭제")
    void delete() {
        Long product_num = 6L;

        //아마 삭제성공하면 리스트 크기가 1 줄어들 것이다
        int before = productDAO.findAll().size();
        productDAO.delete(product_num);
        int after = productDAO.findAll().size();

        Assertions.assertThat(after).isEqualTo(before-1);
        log.info("before size= {}",before);
        log.info("after size= {}",after);
    }

    @Test
    @DisplayName("수정")
    void update() {
        Long product_num = 6L;

        //수정전 찾기
        Product before = productDAO.findByProduct_num(product_num);
        log.info("before name= {}",before.getProduct_name());

        //수정
        before.setProduct_name("당근");
        productDAO.update(product_num,before);

        //수정후 찾기
        Product after = productDAO.findByProduct_num(product_num);
        log.info("after name= {}",after.getProduct_name());


        //수정이 되었으면 같을것이다
        Assertions.assertThat(before).isEqualTo(after);

    }
}