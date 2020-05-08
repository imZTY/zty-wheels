package com.zty.framework.dto;

/**
 * @author tianyi
 * @date 2020-05-01 18:00
 */
public class DataDTO extends PageEntity {

    // 当前用户的id，再权限检验的时候自动获取
    private int currentUID = 0;

    public int getCurrentUID() {
        return currentUID;
    }

    public void setCurrentUID(int currentUID) {
        this.currentUID = currentUID;
    }
}
