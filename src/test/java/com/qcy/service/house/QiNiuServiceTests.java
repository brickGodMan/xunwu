package com.qcy.service.house;

import com.qcy.ApplicationTests;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.util.Auth;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @ClassName QiNiuServiceTests
 * @Description 测试文件上传
 * @Author qiancy
 * @Date 2018/10/26 14:18
 * @Version 1.0
 **/
public class QiNiuServiceTests extends ApplicationTests {
    @Autowired
    private IQiNiuService qiNiuService;

    @Test
    public void testUploadFile() {
        String fileName = "D:/code/soufang/tmp/b7c47030.jpg";
        File file = new File(fileName);

        Assert.assertTrue(file.exists());

        try {
            Response response = qiNiuService.uploadFile(file);
            Assert.assertTrue(response.isOK());
        } catch (QiniuException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete() {
        String key = "Fi4PUqDtf6E5QN63s-EPE4qXABS-";
        try {
            Response response = qiNiuService.delete(key);
            Assert.assertTrue(response.isOK());
        } catch (QiniuException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void download() throws UnsupportedEncodingException {
        String fileName = "Fi4PUqDtf6E5QN63s-EPE4qXABS-";
        String domainOfBucket = "http://pjph51xc5.bkt.clouddn.com";
        String encodedFileName = URLEncoder.encode(fileName, "utf-8");
        String publicUrl = String.format("%s/%s", domainOfBucket, encodedFileName);

        String accessKey = "5odIaQzhNvXtPyuxpBfImbaRUGxdqJepp5q0bqhm";
        String secretKey = "WoEJtNmG10w3-MNx18jYZ9yROVHss-269xCw8s_v";
        Auth auth = Auth.create(accessKey, secretKey);
        long expireInSeconds = 3600;//1小时，可以自定义链接过期时间
        String finalUrl = auth.privateDownloadUrl(publicUrl, expireInSeconds);

        System.out.println(finalUrl);

    }
}
