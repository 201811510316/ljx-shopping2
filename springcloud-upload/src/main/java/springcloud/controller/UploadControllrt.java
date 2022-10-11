package springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springcloud.service.Impl.uploadImgServiceImpl;

//对商品图片进行增删改
@RestController
public class UploadControllrt {

    @Autowired
    uploadImgServiceImpl uploadImgService;

    //给新商品添加或修改主要图片
    @PostMapping("/image")
    public ResponseEntity<String> uploadImage(@RequestParam("id")Integer id,@RequestParam("file")MultipartFile file){
        Integer integer = uploadImgService.findByGoodsTupian(id, file);
        if(integer>0){
            return ResponseEntity.ok("上传成功");
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    //给商品添加或修改详情图片
    @PostMapping("/update")
    public ResponseEntity<String> updateByImage(@RequestParam("id")Integer id,@RequestParam("file")MultipartFile file){
        Integer integer = uploadImgService.findByGoodsImg(id, file);
        if(integer>0){
            return ResponseEntity.ok("上传成功");
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    //对广告图片进行添加或修改
    @PostMapping("/goodsAd")
    public ResponseEntity<String> updateByGoodsAdImage(@RequestParam("id")Integer id,@RequestParam("file")MultipartFile file){
        Integer integer = uploadImgService.findByGoodsAd(id, file);
        if(integer>0){
            return ResponseEntity.ok("上传成功");
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    //对商品轮播图片进行管理
    @PostMapping("/goodsSlide")
    public ResponseEntity<String> updateByGoodsSlide(@RequestParam("id")Integer id,@RequestParam("file")MultipartFile file){
        Integer integer = uploadImgService.findByGoodsSlide(id, file);
        if(integer>0){
            return ResponseEntity.ok("上传成功");
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
