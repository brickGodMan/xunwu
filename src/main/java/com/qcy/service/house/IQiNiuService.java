package com.qcy.service.house;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;

import java.io.File;
import java.io.InputStream;

/**
 * @ClassName IQiNiuService
 * @Description 七牛文件上传服务
 * @Author qiancy
 * @Date 2018/11/16 14:47
 * @Version 1.0
 **/
public interface IQiNiuService {
    Response uploadFile(File file) throws QiniuException;

    Response uploadFile(InputStream inputStream) throws QiniuException;

    Response delete(String key) throws QiniuException;
}
