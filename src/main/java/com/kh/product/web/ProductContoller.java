package com.kh.product.web;

import com.kh.product.domain.svc.ProductSVC;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ProductContoller {

    private final ProductSVC productSVC;

    @GetMapping("/product")
    public String productMain(){
        return "product";
    }

}
