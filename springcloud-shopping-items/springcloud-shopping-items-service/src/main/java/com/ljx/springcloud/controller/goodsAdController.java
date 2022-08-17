package com.ljx.springcloud.controller;

import com.ljx.springcloud.pojo.goodsAd;
import com.ljx.springcloud.service.goodsAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class goodsAdController {
    @Autowired
    goodsAdService goodsAdService;

    //查看商品广告
    @GetMapping("/goods/goodsad")
    public ResponseEntity<List<goodsAd>> queryByGoodsAds(){
        List<goodsAd> goodsAds = goodsAdService.queryByGoodsAd();
        if(goodsAds==null || goodsAds.size()<1){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(goodsAds);
    }

    //修改商品广告(回显商品信息)
    @GetMapping("/goods/goodsAdId")
    public ResponseEntity<goodsAd> queryById(@RequestParam("id")Integer id){
        goodsAd goodsAd = goodsAdService.queryById(id);
        if(goodsAd==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(goodsAd);
    }

    //保存修改商品广告信息
    @PutMapping("/goods/update")
    public ResponseEntity<Void> queryByUpdate(@RequestBody goodsAd goodsAd){
        try {
            goodsAdService.queryByUpdate(goodsAd);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
