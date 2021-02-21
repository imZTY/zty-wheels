package com.zty.framework.util.picture;

public abstract class FileDancer {

    /**
     * 直接将字符串存入文件
     * @param filePath
     * @param content
     * @return
     */
    public abstract boolean saveFile(String filePath, String content);

    /**
     * 将字节数组传入文件
     * @param filePath
     * @param content
     * @return
     */
    public abstract boolean saveFile(String filePath, byte[] content);

}
