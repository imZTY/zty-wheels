package com.zty.bo.service;

/**
 * 操作K-V映射表
 * @author tianyi
 * @date 2020-05-02 21:41
 */
public interface HashService {

    public void put(String hash, String hashKey, String hashValue);

    public String get(String hash, String hashKey);

}
