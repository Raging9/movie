package com.douchai.common.file;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class UploadUtils {

    private static final String AK = "K5FmoaDc6fvJwV_YcIsda4Ukp2TZa058U11bJGRI";
    private static final String SK = "4m0w3loDrD8kxF2ESZ4ocFIxd0ahsOmzYURZi8Ki";
    private static final String BUCKET = "movie-service";

    /**
     * 上传图片到七牛云
     * @param multipartFile
     * @return
     */
    public static String uploadImages(MultipartFile multipartFile){
        try{
            byte[] bytes = multipartFile.getBytes();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String datePath = simpleDateFormat.format(new Date());
            String originalFilename = multipartFile.getOriginalFilename();
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            String fileName = datePath + "/" + UUID.randomUUID().toString().replace("-", "") + suffix;

            Configuration configuration = new Configuration(Region.huanan());
            UploadManager uploadManager = new UploadManager(configuration);

            Auth auth = Auth.create(AK, SK);
            String uploadToken = auth.uploadToken(BUCKET);
            uploadManager.put(bytes,fileName,uploadToken);
            return fileName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除图片
     * @param fileName
     * @return
     */
    public static boolean delFile(String fileName){
        Configuration configuration = new Configuration(Region.huanan());
        Auth auth = Auth.create(AK,SK);
        BucketManager bucketManager = new BucketManager(auth, configuration);
        Response response = null;
        try {
            response = bucketManager.delete(BUCKET,fileName);
        } catch (QiniuException e) {
            e.printStackTrace();
        }
        if(200 == response.statusCode){
            return true;
        }
        return false;
    }
}
