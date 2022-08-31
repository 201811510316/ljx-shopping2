package springcloud.service;

import com.ljx.springcloud.pojo.goods;
import org.springframework.web.multipart.MultipartFile;

public interface goodsTuPianService {

    //根据id查商品并添加图片
    Integer findByGoodsJPG(Integer id, MultipartFile file);

    //根据id查商品并修改图片
    Integer updateByGoodsJPG(Integer id,MultipartFile file);

    //对广告图片进行修改
    Integer goodsAdJPG(Integer id,MultipartFile file);

}
