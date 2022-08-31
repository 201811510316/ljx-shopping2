package springcloud.service.Impl;

import com.ljx.springcloud.pojo.goods;
import com.ljx.springcloud.pojo.goodsAd;
import com.ljx.springcloud.pojo.goodsImg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import springcloud.mapper.goodsAdMapper;
import springcloud.mapper.goodsImgMapper;
import springcloud.mapper.goodsMapper;
import springcloud.service.UploadService;
import springcloud.service.goodsTuPianService;
import tk.mybatis.mapper.entity.Example;

//对商品图片进行增删改
@Service
public class goodsTuPianServiceImpl implements goodsTuPianService {

    @Autowired
    goodsMapper goodsMapper;

    @Autowired
    goodsImgMapper goodsImgMapper;

    @Autowired
    goodsAdMapper goodsAdMapper;

    @Autowired
    UploadService uploadService;

    //给新商品添加/修改主要图片
    @Override
    public Integer findByGoodsJPG(Integer id, MultipartFile file) {
        goods goods = goodsMapper.selectByPrimaryKey(id);
        if(goods!=null){
            String url = uploadService.upload(file);
            goods.setTupian(url);
            int i = goodsMapper.updateByPrimaryKey(goods);
            return i;
        }
        return null;
    }

    //给商品添加/修改详情图片
    @Override
    public Integer updateByGoodsJPG(Integer id, MultipartFile file) {
        goods goods = goodsMapper.selectByPrimaryKey(id);
        if(goods!=null){
            String url = uploadService.uploadJGP(file);

            Example example = new Example(goodsImg.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("goodsId",id);
            goodsImg goodsImg1 = goodsImgMapper.selectOneByExample(example);
            if(goodsImg1!=null){
                goodsImg1.setImg(url);
                return goodsImgMapper.updateByPrimaryKey(goodsImg1);
            }else{
                goodsImg goodsImg = new goodsImg();
                goodsImg.setGoodsId(id);
                goodsImg.setImg(url);
                int insert = goodsImgMapper.insert(goodsImg);
                return insert;
            }
        }
        return null;
    }
    //对广告图片进行修改
    @Override
    public Integer goodsAdJPG(Integer id, MultipartFile file) {
        goodsAd goodsAd = goodsAdMapper.selectByPrimaryKey(id);
        if(goodsAd!=null){
            String url = uploadService.uploadAdJGP(file);
            goodsAd.setImageUrl(url);
            return goodsAdMapper.updateByPrimaryKey(goodsAd);
        }
        return null;
    }

}
