package springcloud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.List;

@Service
public class UploadService {

    private static final Logger logger = LoggerFactory.getLogger(UploadService.class);
    //支持的文件类型
    private static final List<String> suffixes = Arrays.asList("image/png","image/jpeg","image/jpg");

    //主要图片
    public String upload(MultipartFile file){
        try{
            //图片信息校验
            //校验文件类型
            String type = file.getContentType();
            if(!suffixes.contains(type)){
                logger.info("上传失败，文件类型不匹配：{}",type);
                return null;
            }
            //校验图片内容
            BufferedImage image = ImageIO.read(file.getInputStream());
            if(image==null){
                logger.info("上传失败，文件内容不符合要求！！！");
                return null;
            }
            //保存图片
            //生成保存目录
//            File dir = new File("D:\\IDEA_springcloud_file\\upload");
            File dir = new File("D:\\IDEA_file\\goods2-shopping\\src\\main\\resources\\static\\img\\a_img");
            if(!dir.exists()){
                dir.mkdirs();
            }
            //保存图片
            file.transferTo(new File(dir,file.getOriginalFilename()));
            //拼接图片地址
            String url = "http://192.168.70.246:8080/img/a_img/"+file.getOriginalFilename();
            return url;
        }catch (Exception e){
            return null;
        }
    }
    //详情图片
    public String uploadJGP(MultipartFile file){
        try{
            //图片信息校验
            //校验文件类型
            String type = file.getContentType();
            if(!suffixes.contains(type)){
                logger.info("上传失败，文件类型不匹配：{}",type);
                return null;
            }
            //校验图片内容
            BufferedImage image = ImageIO.read(file.getInputStream());
            if(image==null){
                logger.info("上传失败，文件内容不符合要求！！！");
                return null;
            }
            //保存图片
            //生成保存目录
//            File dir = new File("D:\\IDEA_springcloud_file\\upload");
            File dir = new File("D:\\IDEA_file\\goods2-shopping\\src\\main\\resources\\static\\img\\c_img");
            if(!dir.exists()){
                dir.mkdirs();
            }
            //保存图片
            file.transferTo(new File(dir,file.getOriginalFilename()));
            //拼接图片地址
            String url = "http://192.168.70.246:8080/img/c_img/"+file.getOriginalFilename();
            return url;
        }catch (Exception e){
            return null;
        }
    }

    //广告图片
    public String uploadAdJGP(MultipartFile file){
        try{
            //图片信息校验
            //校验文件类型
            String type = file.getContentType();
            if(!suffixes.contains(type)){
                logger.info("上传失败，文件类型不匹配：{}",type);
                return null;
            }
            //校验图片内容
            BufferedImage image = ImageIO.read(file.getInputStream());
            if(image==null){
                logger.info("上传失败，文件内容不符合要求！！！");
                return null;
            }
            //保存图片
            //生成保存目录
//            File dir = new File("D:\\IDEA_springcloud_file\\upload");
            File dir = new File("D:\\IDEA_file\\goods2-shopping\\src\\main\\resources\\static\\img\\banner");
            if(!dir.exists()){
                dir.mkdirs();
            }
            //保存图片
            file.transferTo(new File(dir,file.getOriginalFilename()));
            //拼接图片地址
            String url = "http://192.168.70.246:8080/img/banner/"+file.getOriginalFilename();
            return url;
        }catch (Exception e){
            return null;
        }
    }

}
