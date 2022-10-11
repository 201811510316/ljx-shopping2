package com.ljx.springcloud.controller;

import com.ljx.springcloud.pojo.goodsSlide;
import com.ljx.springcloud.service.goodsSliderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class goodsSliderController {
    @Autowired
    goodsSliderService goodsSliderService;

    //查看单个商品轮播图信息
    @GetMapping("/goods/goodsSlideId")
    public ResponseEntity<List<goodsSlide>> queryBySlide(@RequestParam("id")Integer id){
        List<goodsSlide> goodsSlides = goodsSliderService.queryBySelectSlide(id);
        if(goodsSlides.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(goodsSlides);
    }
}
