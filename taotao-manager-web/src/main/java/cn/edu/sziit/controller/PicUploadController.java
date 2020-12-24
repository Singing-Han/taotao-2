package cn.edu.sziit.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import cn.edu.sziit.pojo.PicUploadResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/*
 *  @创建时间:  2020/12/24 10:53
 *  @描述：    TODO
 */
@RestController
public class PicUploadController {

    @Autowired
    private FastFileStorageClient storageClient;

    private static String[] TYPE = { ".jpg", ".jpeg", ".png", ".bmp", ".gif" };

    @RequestMapping("/rest/pic/upload")
    public PicUploadResult upload(MultipartFile uploadFile) throws IOException {

        // 声明标志位
        boolean flag = false;

        // 初始化返回数据,初始化上传失败
        PicUploadResult picUploadResult = new PicUploadResult();
        picUploadResult.setError(1);

        // 校验后缀
        for (String type : TYPE) {

            //获取上传图片的后缀名
            String oname = uploadFile.getOriginalFilename();
            // 如果后缀是要求的格式结尾，标志位设置为true，跳出寻汗
            if (StringUtils.endsWithIgnoreCase(oname, type)) {
                flag = true;
                break;
            }
        }

        // 如果校验失败，直接返回
        if (!flag) {
            return picUploadResult;
        }

        // 重置标志位
        flag = false;

        // 图片内容校验
        try {
            BufferedImage image = ImageIO.read(uploadFile.getInputStream());
            if (image != null) {
                picUploadResult.setHeight(String.valueOf(image.getHeight()));
                picUploadResult.setWidth(String.valueOf(image.getWidth()));
                flag = true;
            }
        } catch (Exception e) {
        }

        // 校验成功，需要上传图片
        if (flag) {

            //获取图片的后缀名
            String ext = StringUtils.substringAfterLast(uploadFile.getOriginalFilename(), ".");


            //开始上传图片
            //参数一 :上传的文件对应的输入流
            //参数二： 上传的文件大小
            //参数三： 文件的后缀
            //参数四： 元数据的集合，图片创建时间，等等。。。
            StorePath storePath = storageClient.uploadFile(uploadFile.getInputStream(), uploadFile.getSize(), ext, null);

            //http://192.168.37.161/group1/M00/00/00/wKgloV_j-feAfdwsAABp2a94NDM729_big.jpg
            //storePath.getPath();  M00/00/00/wKgloV_j-feAfdwsAABp2a94NDM729_big.jpg
            //storePath.getGroup();  group1
            //storePath.getFullPath();  //group1/M00/00/00/wKgloV_j-feAfdwsAABp2a94NDM729_big.jpg


            System.out.println("path=" + storePath.getFullPath());
            String picUrl = "http://192.168.37.161/"+ storePath.getFullPath();

            // 设置图片url
            picUploadResult.setUrl(picUrl);

            // 上传成功设置为0
            picUploadResult.setError(0);
        }

        return picUploadResult;
    }
}
