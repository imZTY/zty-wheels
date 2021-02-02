package com.zty.common.dto;

import java.io.Serializable;

/**
 * @author tianyi
 * @date 2020-05-21 17:44
 */
public class TaskDTO implements Serializable {

    public TaskDTO(int id) {
        this.id = id;
    }

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TaskDTO{" +
                "id=" + id +
                '}';
    }
}
