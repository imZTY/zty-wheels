package com.zty.framework.util.dataSave;

import java.util.List;

/**
 * 用于数据存储的工具类 的公共父类
 * 不同的文件格式，创建不同的子类
 * @author Tianyi
 * @Date 2019.04.03
 */
public abstract class DataDancer<T> {

    /**
     * 在生成实例的时候需要数据主体
     * @param objList
     */
    public DataDancer(List<T> objList){
        this.setObjList(objList);
    }

    /* 数据主体 */
    private List<T> objList;

    /* 数据标题 */
    private String header;

    /**
     *
     * @return 数据标题
     */
    public String headDance() {
        return this.getHeader();
    }

    /**
     * 由子类去实现，不同的文件格式创建不同的子类、用不同的流程实现功能
     * @return 数据每一行
     */
    public abstract String rowDance();

    public List<T> getObjList() {
        return objList;
    }

    public void setObjList(List<T> objList) {
        this.objList = objList;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}