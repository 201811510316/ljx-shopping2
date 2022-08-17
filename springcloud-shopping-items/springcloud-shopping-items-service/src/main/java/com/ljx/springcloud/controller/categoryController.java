package com.ljx.springcloud.controller;

import com.ljx.springcloud.pojo.goodsClassification;
import com.ljx.springcloud.service.categoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("category")
public class categoryController {

    @Autowired
    categoryService categoryService;

    //以分页-查询商品分类的种类
    @GetMapping("/list")
    public ResponseEntity<List<goodsClassification>> queryByCategory(){
        List<goodsClassification> goodsClassifications = categoryService.queryListByCategory();
        if(goodsClassifications==null || goodsClassifications.size()<1){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(goodsClassifications);
    }
}
