package com.kh.product.web;

import com.kh.product.domain.Product;
import com.kh.product.domain.svc.ProductSVC;
import com.kh.product.web.api.ApiResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/product/api")
public class ProductRestController {

    private final ProductSVC productSVC;

    //물건 등록
    @ResponseBody
    @PostMapping
    public ApiResult<Product> upload (@RequestBody Product product){
        log.info("product={}",product);
        Product uploadProduct = productSVC.upload(product);
        ApiResult<Product> result = new ApiResult<>("00","success",uploadProduct);
        return result;
    }

    //전체조회
    @ResponseBody
    @GetMapping
    public ApiResult<List<Product>> Products(){
        List<Product> list = productSVC.list();
        ApiResult<List<Product>> result = null;
        if (list.size()>0){
            result = new ApiResult<>("00","success",list);
        }else {
            result = new ApiResult<>("01","success",null);
        }
        return result;
    }

    //물건 조회
    @ResponseBody
    @GetMapping("/{product_num}")
    public ApiResult<Product> detailByProduct_num(@PathVariable Long product_num){
        Product findedProduct = productSVC.detail(product_num);

        ApiResult<Product> result =null;
        if (findedProduct != null){
            result = new ApiResult<Product>("00","success",findedProduct);
        }else {
            result = new ApiResult<Product>("99","fail",null);
        }
        return result;
    }



    //물건삭제
    @ResponseBody
    @DeleteMapping("/{product_num}")
    public ApiResult<String> delete(@PathVariable Long product_num){
        Product findedProduct = productSVC.detail(product_num);

        ApiResult<String> result =null;
        if (findedProduct != null){
            productSVC.delete(product_num);
            result = new ApiResult<>("00","success",findedProduct.getProduct_name()+"이(가) 삭제되었습니다.");
        }else {
            result = new ApiResult<>("99","fail","상품 아이디가 없습니다.");
        }

        return result;
    }

    //물건수정
    @ResponseBody
    @PatchMapping("/{product_num}")
    public ApiResult<Object> update(@PathVariable Long product_num, @RequestBody Product product){

        Product updateBeforeProduct = productSVC.update(product_num,product);

        ApiResult<Object> result = null;
        if (updateBeforeProduct != null){
            result =new ApiResult<>("00","success",productSVC.detail(product.getProduct_num()));
        }else {
            result =new ApiResult<>("99","fail","상품 아이디가 없습니다.");
        }
        return result;
    }

}
