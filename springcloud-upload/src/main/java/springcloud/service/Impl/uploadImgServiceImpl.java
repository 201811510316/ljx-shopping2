package springcloud.service.Impl;

import com.ljx.springcloud.pojo.goods;
import com.ljx.springcloud.pojo.goodsAd;
import com.ljx.springcloud.pojo.goodsImg;
import com.ljx.springcloud.pojo.goodsSlide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import springcloud.service.UploadService;
import springcloud.service.uploadImgService;
import tk.mybatis.mapper.entity.Example;

@Service
public class uploadImgServiceImpl implements uploadImgService {

    @Autowired
    springcloud.mapper.goodsMapper goodsMapper;

    @Autowired
    springcloud.mapper.goodsAdMapper goodsAdMapper;

    @Autowired
    springcloud.mapper.goodsImgMapper goodsImgMapper;

    @Autowired
    springcloud.mapper.goodsSlideMapper goodsSlideMapper;

    @Autowired
    UploadService uploadService;

    //上传商品主要图片
    @Override
    public Integer findByGoodsTupian(Integer id, MultipartFile file) {
        goods goods = goodsMapper.selectByPrimaryKey(id);
        String url = uploadService.upload(file);
        goods.setTupian(url);
        int i = goodsMapper.updateByPrimaryKey(goods);
        return i;
    }

    //上传商品详情图片
    @Override
    public Integer findByGoodsImg(Integer id, MultipartFile file) {
        Example example = new Example(goodsImg.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("goodsId",id);
        goodsImg goodsImg = goodsImgMapper.selectOneByExample(example);
        if(goodsImg==null){
            goodsImg goodsImg1 = new goodsImg();
            goodsImg1.setGoodsId(id);
            goodsImg1.setImg(uploadService.uploadJGP(file));
            return goodsImgMapper.insert(goodsImg1);
        }else{
            goodsImg.setImg(uploadService.uploadJGP(file));
            return goodsImgMapper.updateByPrimaryKey(goodsImg);
        }
    }

    //上传商品广告图片
    @Override
    public Integer findByGoodsAd(Integer id, MultipartFile file) {
        goodsAd goodsAd = goodsAdMapper.selectByPrimaryKey(id);
        String url = uploadService.uploadAdJGP(file);
        goodsAd.setImageUrl(url);
        return goodsAdMapper.updateByPrimaryKey(goodsAd);
    }

    //上传商品轮播图片
    @Override
    public Integer findByGoodsSlide(Integer id, MultipartFile file) {
        goodsSlide goodsSlide = new goodsSlide();
        goodsSlide.setGoodsId(id);
        goodsSlide.setSlideImg(uploadService.uploadSlideJGP(file));
        return goodsSlideMapper.insert(goodsSlide);
    }
}
