package com.zty.framework.util;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * @author Tianyi
 */
public abstract class FileUtil {

    /**
     * 直接将字符串存入文件
     * @param filePath
     * @param content
     * @return
     */
    public static boolean saveFile(String filePath, String content) throws IOException {
        return saveFile(filePath, content.getBytes());
    }

    /**
     * 将字节数组传入文件
     * @param filePath
     * @param content
     * @return
     */
    public static boolean saveFile(String filePath, byte[] content) throws IOException {
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
//            e.printStackTrace();
            throw e;
        } catch (IOException e) {
//            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 文件属性字符串内容
     * @param filePath 文件路径
     * @throws IOException
     */
    public static String printFilePropertis(String filePath) throws IOException {
        File f = new File(filePath);
        StringBuilder sb = new StringBuilder();
        if (f.exists()) {
            sb.append(f.getName() + "的属性如下： 文件长度为：" + f.length());
            sb.append(f.isFile() ? "是文件" : "不是文件");
            sb.append(f.isDirectory() ? "是目录" : "不是目录");
            sb.append(f.canRead() ? "可读取" : "不");
            sb.append(f.canWrite() ? "是隐藏文件" : "");
            sb.append("文件夹的最后修改日期为：" + new Date(f.lastModified()));
            sb.append("文件的完整路径是：" + f.getCanonicalPath());
        } else {
            sb.append(f.getName() + "的属性如下：");
            sb.append(f.isFile() ? "是文件" : "不是文件");
            sb.append(f.isDirectory() ? "是目录" : "不是目录");
            sb.append(f.canRead() ? "可读取" : "不");
            sb.append(f.canWrite() ? "是隐藏文件" : "");
            sb.append("文件的最后修改日期为：" + new Date(f.lastModified()));
        }
        return sb.toString();
    }


    /**
     * 解压函数。抄来的代码，未调整（只能处理文件，未支持对文件夹的处理）
     * @param zipFilePath 压缩包路径
     * @param finalDirPath 内容解压至此文件夹
     * @throws IOException
     */
    public static void unZip(String zipFilePath, String finalDirPath) throws IOException {
        File file = new File(zipFilePath) ;	// 定义压缩文件名称
        File dirFile = new File(finalDirPath) ;	// 输出文件的时候要有文件夹的操作
        if (!dirFile.exists()){
            dirFile.mkdirs();
        }
        ZipFile zipFile = new ZipFile(file, Charset.forName("ASCII")) ;	// 实例化ZipFile对象

        ZipInputStream zipInput = null ;	// 定义压缩输入流
        OutputStream out = null ;	// 定义输出流，用于输出每一个实体内容
        InputStream input = null ;	// 定义输入流，读取每一个ZipEntry
        ZipEntry entry = null ;	// 每一个压缩实体

        zipInput = new ZipInputStream(new FileInputStream(file)) ;	// 实例化ZIpInputStream
        while((entry = zipInput.getNextEntry())!=null){	// 得到一个压缩实体
            File outFile = new File(finalDirPath + "\\" + entry.getName()) ;	// 定义输出的文件路径
            System.out.println(outFile.getCanonicalPath());
            if(!outFile.exists()){	// 判断输出文件是否存在
                outFile.createNewFile() ;	// 创建文件
            }
            input = zipFile.getInputStream(entry) ;	// 得到每一个实体的输入流
            out = new FileOutputStream(outFile) ;	// 实例化文件输出流
            int temp = 0 ;
            while((temp=input.read())!=-1){
                out.write(temp) ;
            }
            input.close() ;		// 关闭输入流
            out.close() ;	// 关闭输出流
        }
        input.close();
        zipInput.close();
    }
}
