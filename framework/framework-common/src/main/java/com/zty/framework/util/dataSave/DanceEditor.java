package com.zty.framework.util.dataSave;

import java.io.*;

public class DanceEditor {

    /* 某种格式的数据存储类 */
    private DataDancer dancer;

    /* 数据文件的存储路径 */
    private String filePath;

    /* 构造的时候必须传入需要用到的内容 */
    public DanceEditor(String filePath, DataDancer dancer) {
        this.filePath = filePath;
        this.dancer = dancer;
    }

    /**
     * 文件存储之舞
     */
    public void dance() throws IOException {
        File file = new File( this.filePath );
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
//                e.printStackTrace();
                throw e;
            }
        }
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        try {
            osw = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            // 手动加上BOM标识
            osw.write(new String(new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF }));
            bw = new BufferedWriter(osw);
            bw.append(dancer.headDance());
            bw.append(dancer.rowDance());
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            try {
                bw.close();
                osw.close();
            } catch (IOException e) {
//                e.printStackTrace();
                throw e;
            }
        }
    }

    public DataDancer getDancer() {
        return dancer;
    }

    public void setDancer(DataDancer dancer) {
        this.dancer = dancer;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }


}
