package com.zty.framework.util.picture;

import java.io.*;


/**
 * @author Tianyi
 * @Date 2019年4月12日
 */
public class PictureDancer extends FileDancer {

    @Override
    public boolean saveFile(String filePath, String content) {
        return this.saveFile(filePath, content.getBytes());
    }

    @Override
    public boolean saveFile(String filePath, byte[] content) {
        try {
            File file = new File(filePath);
            if(file.exists()) {
                file.createNewFile();
            }
            OutputStream output = new FileOutputStream(file);
            output.write(content);
            output.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


}
