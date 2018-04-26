package me.snnupai.door.util;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ImageUtils {
    /**
     * 获取上传凭证
     */
    public static String getUploadCredential() {
        String accessKey = "Cd-Nc98hBcNGY4Lefc3fc5GjGKKi2_zI_darCmJ7";
        String secretKey = "FfDUNWwGWZ4StIZgKTP6eLfxMxrfBsVrbl_EjAE3";
        String bucket = "snnupai";
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        System.out.println(upToken);
        return upToken;
    }


    /**
     * 文件上传
     * @param zone
     *    华东    Zone.zone0()
     *    华北    Zone.zone1()
     *    华南    Zone.zone2()
     *    北美    Zone.zoneNa0()
     * @param upToken 上传凭证
     * @param localFilePath 需要上传的文件本地路径
     * @return
     */
    public static DefaultPutRet fileUpload(Zone zone, String upToken, String localFilePath) {
        // 构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(zone);
        // ...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        // 默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            // 解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
            return putRet;
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                // ignore
                log.error(ex2.getMessage());
            }
        }
        return null;
    }

}
