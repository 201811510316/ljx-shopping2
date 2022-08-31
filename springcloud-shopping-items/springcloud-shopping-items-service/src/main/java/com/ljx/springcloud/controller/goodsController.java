package com.ljx.springcloud.controller;

import com.ljx.springcloud.pojo.goods;
import com.ljx.springcloud.service.Impl.goodsServiceImpl;
import com.ljx.springcloud.service.categoryService;
import com.ljx.springcloud.utils.PageResult;
import com.ljx.springcloud.pojo.goodsFenLei;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@RestController
public class goodsController {

    @Autowired
    goodsServiceImpl goodsService;

    @Autowired
    categoryService categoryService;
    /**
     *     商品goodsId,
     *     商品名称goodsName,
     *     商品描述detail,
     *     商品库存goodsStock,
     *     商品分类名称categoryName,
     *     商品状态state
     */

    //展示商品列表
    @GetMapping("/spu/page")
    public ResponseEntity<PageResult<goodsFenLei>> queryGoodsByPage(@RequestParam(value = "page",defaultValue = "1") Integer page, @RequestParam(value = "rows",defaultValue = "4") Integer rows, @RequestParam(value = "saleable",defaultValue = "1") Integer saleable){
        PageResult<goodsFenLei> result = goodsService.queryByGoods(page, rows, saleable);
        if(result !=null){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    //新增商品
    @PostMapping("/goods")
    public ResponseEntity<Void> saveGoods(@RequestBody goods goods) {
        goods goods1 = new goods();
        goods1.setGoodsName(goods.getGoodsName());
        goods1.setGoodsPrice(goods.getGoodsPrice());
        goods1.setGoodsSales(0);
        goods1.setGoodsStock(goods.getGoodsStock());
        goods1.setDetail(goods.getDetail());
        goods1.setCategoryId(goods.getCategoryId());
        goods1.setDefaultSize(goods.getDefaultSize());
        goods1.setState(1);
        goods1.setGoodsHot(0);
        goods1.setGoodsNew(1);
        Integer save = goodsService.save(goods1);
        if(save>0){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //查询单个商品信息（用于修改回显）
    @GetMapping("/spu/list")
    public ResponseEntity<goodsFenLei> querySpuDetailById(@RequestParam("id")Integer id){
        goodsFenLei goodsFenLei = goodsService.queryGoodsById(id);
        if(goodsFenLei==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(goodsFenLei);
    }

    //保存修改商品信息
    @PutMapping
    public ResponseEntity<Void> updateGoods(@RequestBody goods goods) {
        try {
            goodsService.update(goods);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //删除商品
    @DeleteMapping("/spu/delete")
    public ResponseEntity<Void> deleteByGoods(@RequestParam("id")Integer id){
        Integer integer = goodsService.deleteByGoods(id);
        if(integer!=null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


}