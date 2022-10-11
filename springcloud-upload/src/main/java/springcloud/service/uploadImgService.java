package springcloud.service;

import org.springframework.web.multipart.MultipartFile;

public interface uploadImgService {
    //上传商品主要图片
    Integer findByGoodsTupian(Integer id, MultipartFile file);

    //上传商品详情图片
    Integer findByGoodsImg(Integer id,MultipartFile file);

    //上传商品广告图片
    Integer findByGoodsAd(Integer id,MultipartFile file);

    //上传商品轮播图片
    Integer findByGoodsSlide(Integer id,MultipartFile file);
}
