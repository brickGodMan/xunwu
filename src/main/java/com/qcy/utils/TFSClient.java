package com.qcy.utils;

import com.taobao.common.tfs.TfsManager;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName TFSClient
 * @Description TODO
 * @Author qiancy
 * @Date 2019/2/22 13:56
 * @Version 1.0
 **/
public class TFSClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(TFSClient.class);
    /*****
     * <pre>
     * 功能：删除tfs上的图片
     * 创建人：lijc
     * 创建时间：2016-1-25 下午3:28:51
     * </pre>
     * @param tfsFileName 带扩展名的name
     * @return
     * @version 1.0.0
     */
    public static boolean deleteFile(String tfsFileName,TfsManager tfsManager){
        int index = tfsFileName.lastIndexOf(".");
        String tfsSuffix = tfsFileName.substring(index);
        tfsFileName = tfsFileName.substring(0,index);
        return tfsManager.unlinkFile(tfsFileName, tfsSuffix);
    }
    /***
     * <pre>
     * 功能：上传文件到TFS
     * 创建人：lijc
     * 创建时间：2015年9月6日 上午11:51:15
     * </pre>
     *
     * @param inputStream
     *            文件流
     * @param tfsSuffix 扩展名。eg: .jpg、   .png.  [扩展名前面要带。]
     * @param tfsName
     *            默认传null，非null情况下是替换文件(非null情况下传的值必须是真实的，哪怕是已经删除了的tfsName也可以，但自己手工改造的不行)
     * @return 文件上传后的新文件名
     * @version 1.0.0
     */
    public static String uploadFile(InputStream inputStream, String tfsSuffix, String tfsName, TfsManager tfsManager) {
        String newName = null;
        try {
            byte[] data = IOUtils.toByteArray(inputStream);
            newName = uploadFileToTfs(data, tfsSuffix, tfsName, tfsManager);
        } catch (IOException e) {
            LOGGER.error("IOException:{}", e);
        }
        return newName;
    }
    /***
     * <pre>
     * 功能：判断当前nameserver是否可用
     * 创建人：JokenWang
     * 创建时间：2015年9月6日 下午1:05:03
     * </pre>
     *
     * @param
     * @return true：可用，false：不可用
     * @version 1.0.0
     */
    private static boolean isEnable(TfsManager tfsManager){
        return tfsManager.isEnable();
    }
    /*****
     * <pre>
     * 功能：上传文件到tfs
     * 创建人：lijc
     * 创建时间：2016-1-23 下午5:56:26
     * </pre>
     *
     * @param data
     *            上传到TFS的文件二进制数组
     * @param tfsSuffix
     *            扩展名。eg: .jpg、 .png
     * @param tfsName
     *            默认传null，非null情况下是替换文件(非null情况下传的值必须是真实的，哪怕是已经删除了的tfsName也可以，但自己手工改造的不行)
     * @return
     * @version 1.0.0
     */
    private static String uploadFileToTfs(byte[] data, String tfsSuffix, String tfsName, TfsManager tfsManager) {
        String fileName = null;
        if (isEnable(tfsManager)) {
            boolean deleted;
            tfsSuffix = null == tfsSuffix ? tfsSuffix : (tfsSuffix.startsWith(".") ? tfsSuffix.trim() : "." + tfsSuffix.trim());
            if (null != tfsName && tfsName.trim().length() > 0) {
                int index = tfsName.lastIndexOf(".");
                tfsName = index > 0 ? tfsName.substring(0, index) : tfsName;
                deleted = tfsManager.unlinkFile(tfsName, tfsSuffix);
                if (!deleted) {
                    //业务需要，存在指定名字上传的情况，上传前该文件是不存在的，删除肯定失败。
                    LOGGER.warn("tfs file not exists : " + tfsName + " , plean check it.");
                }
            }
            fileName = tfsManager.saveFile(data, tfsName, tfsSuffix);
            fileName = fileName + (null == tfsSuffix ? "" : tfsSuffix.toLowerCase());
        } else {
            LOGGER.error("TFS nameserver:{} is not running", tfsManager.getMasterIP());
        }
        return fileName;
    }
}
