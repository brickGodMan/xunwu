package com.qcy.web.dto;

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
    public String imageUrl;
    public int width;
    public int height;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "QiNiuPutRet{" +
                "key='" + key + '\'' +
                ", hash='" + hash + '\'' +
                ", bucket='" + bucket + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
