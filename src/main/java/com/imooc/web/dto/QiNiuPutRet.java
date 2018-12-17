package com.imooc.web.dto;

/**
 * @ClassName QiNiuPutRet
 * @Description 七牛文件上传返回结构体
 * @Author qiancy
 * @Date 2018/12/14 11:51
 * @Version 1.0
 **/
public class QiNiuPutRet {
    public String key;
    public String hash;
    public String bucket;
    public int width;
    public int height;

    @Override
    public String toString() {
        return "QiNiuPutRet{" +
                "key='" + key + '\'' +
                ", hash='" + hash + '\'' +
                ", bucket='" + bucket + '\'' +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
