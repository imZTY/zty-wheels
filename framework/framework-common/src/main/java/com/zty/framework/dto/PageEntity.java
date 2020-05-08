package com.zty.framework.dto;

import javax.management.ReflectionException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * @author tianyi
 * @date 2019-01-15 22:13
 */
public class PageEntity implements Serializable {

    private int[] ids;

    /**
     * 默认设置为：每页30行
     */
    private int rows = 30;

    private int page;

    private int pageStart;

    public int[] getIds() {
        return ids;
    }

    public void setIds(int[] ids) {
        this.ids = ids;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageStart() {
        return pageStart;
    }

    public void setPageStart(int pageStart) {
        this.pageStart = pageStart;
    }

}
