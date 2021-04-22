package com.zty.framework.dto;

/**
 * 通用返回模板
 * @author tianyi
 * @date 2018-04-29 01:52
 */
public class ResultDTO<T> {

    /* 错误码. */
    private int resultCode;

    /* 提示信息. */
    private String resultMsg;

    /* 具体内容. */
    private T data;
    
    /* (数据库的)(满足条件的)数据总量 */
    private long count = 0;

    /**
     * 普通构造
     * @return
     */
    public static ResultDTO success(){return success(null);}

    /**
     * 含数据的构造
     * @param object
     * @return
     */
    public static ResultDTO success(Object object){
        ResultDTO rt=new ResultDTO();
        rt.setResultCode(200);
        rt.setResultMsg("成功");
        rt.setData(object);
        return rt;
    }

    /**
     * 含数据与总量的构造(用于分页)
     * @param object
     * @param count
     * @return
     */
    public static ResultDTO success(Object object, int count){
        ResultDTO rt=new ResultDTO();
        rt.setResultCode(200);
        rt.setResultMsg("成功");
        rt.setData(object);
        rt.setCount(count);
        return rt;
    }

    /**
     * 含数据与总量的构造(用于分页)
     * @param object
     * @param count
     * @return
     */
    public static ResultDTO success(Object object, long count){
        ResultDTO rt=new ResultDTO();
        rt.setResultCode(200);
        rt.setResultMsg("成功");
        rt.setData(object);
        rt.setCount(count);
        return rt;
    }

    /**
     * @param code
     * @param msg
     * @return 不含信息对象的错误描述对象
     */
    public static ResultDTO error(Integer code, String msg){
        ResultDTO rt=new ResultDTO();
        rt.setResultCode(code);
        rt.setResultMsg(msg);
        rt.setData(null);
        return rt;
    }

    /**
     * @param code
     * @param msg
     * @param object
     * @return 含信息对象的错误描述对象
     */
    public static ResultDTO error(Integer code, String msg, Object object){
        ResultDTO rt=new ResultDTO();
        rt.setResultCode(code);
        rt.setResultMsg(msg);
        rt.setData(object);
        return rt;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public long getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ResultDTO{" +
                "resultCode=" + resultCode +
                ", resultMsg='" + resultMsg + '\'' +
                ", data=" + data +
                ", count=" + count +
                '}';
    }
}
