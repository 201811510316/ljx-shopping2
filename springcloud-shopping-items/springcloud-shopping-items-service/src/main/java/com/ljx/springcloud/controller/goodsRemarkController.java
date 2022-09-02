package com.ljx.springcloud.controller;

import com.ljx.springcloud.pojo.goodsRemark;
import com.ljx.springcloud.service.goodsRemarkService;
import com.ljx.springcloud.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//商品备注信息管理
@RestController
public class goodsRemarkController {
    @Autowired
    goodsRemarkService goodsRemarkService;

    //查看
    @GetMapping("/remark/page")
    public ResponseEntity<PageResult<goodsRemark>> selectByRemark(@RequestParam(value = "page",defaultValue = "1")Integer page,@RequestParam(value = "rows",defaultValue = "4")Integer rows){
        PageResult<goodsRemark> goodsRemark = goodsRemarkService.findByGoodsRemark(page, rows);
        if(goodsRemark!=null){
            return ResponseEntity.ok(goodsRemark);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    //添加
    @PostMapping("/remark/add")
    public ResponseEntity<String> addByRemark(@RequestBody goodsRemark goodsRemark){
        Integer byAddRemark = goodsRemarkService.findByAddRemark(goodsRemark);
        if(byAddRemark>0){
            return ResponseEntity.ok("添加成功");
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    //查看单个商品的评价
    @GetMapping("/remark/goodsid")
    public ResponseEntity<goodsRemark> selectById(@RequestParam("id")Integer id){
        goodsRemark bySelectId = goodsRemarkService.findBySelectId(id);
        if(bySelectId!=null){
            return ResponseEntity.ok(bySelectId);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    //修改
    @PutMapping("/remark/update")
    public ResponseEntity<String> updateByRemark(@RequestBody goodsRemark goodsRemark){
        Integer remark = goodsRemarkService.findByUpdateRemark(goodsRemark);
        if(remark>0){
            return ResponseEntity.ok("修改成功");
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    //删除
    @DeleteMapping("/remark/delete")
    public ResponseEntity<String> deleteByRemark(@RequestParam("id")Integer id){
        Integer remark = goodsRemarkService.findDeleteRemark(id);
        if(remark>0){
            return ResponseEntity.ok("删除成功");
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
