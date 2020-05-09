package com.zty.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

/**
 * @author tianyi
 * @date 2020-04-19 17:53
 */
@Component
public class PathConfig {

    /**
     * 临时文件目录，临时保存，定期清理
     */
    @Value("${filePath.cache}")
    private String cacheDir;

    /**
     * 永久文件目录，永久保存，定期备份
     */
    @Value("${filePath.static}")
    private String staticDir;

    @Value("${filePath.publicUrl}")
    private String publicUrl;

    @PostConstruct
    private void init(){
        System.out.println("PathConfig初始化文件夹...");
        File cacheDir = new File(this.cacheDir);
        File staticDir = new File(this.staticDir);
        if (!cacheDir.exists()){
            cacheDir.mkdirs();
        }
        if (!staticDir.exists()){
            staticDir.mkdirs();
        }
        try {
            // 转化为绝对路径
            this.cacheDir = cacheDir.getCanonicalPath();
            this.staticDir = staticDir.getCanonicalPath();
        } catch (IOException e) {
            System.out.println("PathConfig初始化文件夹...失败！");
            e.printStackTrace();
        }
    }

    public String getCacheDir() {
        return cacheDir;
    }

    public String getStaticDir() {
        return staticDir;
    }

    public String getPublicUrl() {
        return publicUrl;
    }
}
