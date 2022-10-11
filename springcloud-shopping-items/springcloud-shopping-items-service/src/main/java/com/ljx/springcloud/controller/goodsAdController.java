package com.ljx.springcloud.controller;

import com.ljx.springcloud.pojo.goodsAd;
import com.ljx.springcloud.service.goodsAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//商品广告信息管理
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

    //查看商品广告
    @GetMapping("/goods/goodsAdId")
    public ResponseEntity<goodsAd> queryById(@RequestParam("id")Integer id){
        goodsAd goodsAd = goodsAdService.queryById(id);
        if(goodsAd==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(goodsAd);
    }

    //添加商品广告
    @PostMapping("/goods/add")
    public ResponseEntity<String> queryByAdd(@RequestBody goodsAd goodsAd){
        goodsAd goodsAd1 = new goodsAd();
        goodsAd1.setName(goodsAd.getName());
        goodsAd1.setLink("null");
        goodsAd1.setEnabled(goodsAd.getEnabled());
        Integer integer = goodsAdService.queryByAddGoodsAd(goodsAd1);
        if (integer!=null) {
            return ResponseEntity.ok("添加成功");
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    //删除商品广告
    @DeleteMapping("/goods/delete")
    public ResponseEntity<String> queryByDelete(@RequestParam("id")Integer id){
        Integer integer = goodsAdService.queryByGoodsAdDelete(id);
        if(integer>0){
            return ResponseEntity.ok("删除成功");
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
