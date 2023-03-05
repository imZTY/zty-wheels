package com.zty.common.dto;

import java.io.Serializable;

/**
 * @author tianyi
 * @date 2020-05-21 17:44
 */
public class MessageDTO implements Serializable {

    public MessageDTO(String data) {
        this.data = data;
    }

    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
                "id=" + data +
                '}';
    }
}
