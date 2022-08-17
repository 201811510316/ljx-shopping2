package com.ljx.springcloud.controller;

import com.ljx.springcloud.pojo.goodsImg;
import com.ljx.springcloud.pojo.goodsTupian;
import com.ljx.springcloud.service.goodsImgService;
import com.ljx.springcloud.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class goodsImgController {

    @Autowired
    goodsImgService goodsImgService;

    //查看商品的详情图片
    @GetMapping("/goods/tupian")
    public ResponseEntity<PageResult<goodsTupian>> queryByGoodsTupian(@RequestParam(value = "page",defaultValue = "1")Integer page,@RequestParam(value = "rows",defaultValue = "4")Integer rows){
        PageResult<goodsTupian> result = goodsImgService.qureyByGoodsTupian(page, rows);
        if(result==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

    //回显商品图片信息
    @GetMapping("/goods/goodsImgId")
    public ResponseEntity<goodsImg> queryByGoodsImg(@RequestParam("id")Integer id){
        goodsImg goodsImg = goodsImgService.queryByGoodsImg(id);
        if(goodsImg==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(goodsImg);
    }

    //保存商品修改信息
    @PutMapping("/goods/goodsImg/update")
    public ResponseEntity<Void> queryByUpdate(@RequestBody goodsImg goodsImg){
        try {
            goodsImgService.save(goodsImg);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //添加商品图片详情
    @PostMapping("/goods/add")
    public ResponseEntity<Void> queryByAdd(@RequestBody goodsImg goodsImg){
        try {
            goodsImgService.goodsImgAdd(goodsImg);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
