package springcloud.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springcloud.service.UploadService;
import springcloud.service.goodsTuPianService;

import javax.servlet.http.HttpSession;

//对商品图片进行增删改
@RestController
//@RequestMapping("upload")
public class UploadControllrt {

    @Autowired
    goodsTuPianService goodsTuPianService;

    //给新商品上传主要图片
    @PostMapping("/image")
    public ResponseEntity<String> uploadImage(@RequestParam("file")MultipartFile file,@RequestParam("id")Integer id){
        Integer byGoodsJPG = goodsTuPianService.findByGoodsJPG(id, file);
        if(byGoodsJPG!=null){
            return ResponseEntity.ok("上传成功");
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    //给商品上传详情图片
    @PostMapping("/update")
    public ResponseEntity<String> updateByImage(@RequestParam("file")MultipartFile file,@RequestParam("id")Integer id){
        Integer byGoodsJPG = goodsTuPianService.updateByGoodsJPG(id,file);
        if(byGoodsJPG!=null){
            return ResponseEntity.ok("上传成功");
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    //对广告图片进行修改
    @PostMapping("/goodsAd")
    public ResponseEntity<String> updateByGoodsAdImage(@RequestParam("file")MultipartFile file,@RequestParam("id")Integer id){
        Integer byGoodsJPG = goodsTuPianService.goodsAdJPG(id,file);
        if(byGoodsJPG!=null){
            return ResponseEntity.ok("上传成功");
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
