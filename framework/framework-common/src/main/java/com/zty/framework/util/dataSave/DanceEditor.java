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

    /**
     * 若文件不存在，则创建
     * 若文件已存在，则在末尾加内容
     * @throws IOException
     */
    public void bufferDance() throws IOException{
        File file = new File( this.filePath );
        boolean isFirstDance = false;
        if(!file.exists()) {
            try {
                isFirstDance = true;
                file.createNewFile();
            } catch (IOException e) {
                throw e;
            }
        }
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        try {
            if (isFirstDance) {
                // 初舞，创建文件并写入标题
                osw = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
                bw = new BufferedWriter(osw);
                bw.append(dancer.headDance());
                bw.append(dancer.rowDance());
            }else {
                // 续舞，在末尾添加内容
                osw = new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8");
                bw = new BufferedWriter(osw);
                bw.append(dancer.rowDance());
            }
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
