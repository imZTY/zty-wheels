package com.zty.framework.service;

import com.zty.framework.dto.ResultDTO;

import java.util.List;

/**
 * @author tianyi
 * @date 2020-05-30 20:50
 */
public interface CRUDService<T,K> {

    public int add(T data);

    public int delete(T data);

    public T update(T data);

    public List<T> list(T data);

    public ResultDTO<List<K>> listByPage(T data);
}
