package com.kh.product.domain.svc;

import com.kh.product.domain.Product;
import com.kh.product.domain.dao.ProductDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ProductSVCImpl implements ProductSVC{

    private final ProductDAO productDAO;

    @Override
    public Product upload(Product product) {
        return productDAO.insert(product);
    }

    @Override
    public List<Product> list() {
        return productDAO.findAll();
    }

    @Override
    public Product detail(Long product_num) {
        return productDAO.findByProduct_num(product_num);
    }

    @Override
    public int delete(Long product_num) {
        return productDAO.delete(product_num);
    }

    @Override
    public Product update(Long product_num, Product product) {
        return productDAO.update(product_num,product);
    }
}
