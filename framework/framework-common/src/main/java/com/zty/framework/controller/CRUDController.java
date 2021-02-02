package com.zty.framework.controller;

import com.zty.framework.dto.DataDTO;
import com.zty.framework.dto.ResultDTO;

import java.util.List;

/**
 * @author tianyi
 * @date 2020-05-14 02:00
 */
public interface CRUDController<T extends DataDTO, K extends DataDTO> {

    public ResultDTO add(T data);

    public ResultDTO update(T data);

    public ResultDTO<List<K>> listByPage(T data);

    public ResultDTO delete(T data);
}
