package com.zty.web.response;

import java.util.List;

import com.zty.common.DO.ProductInfoDO;

/**
 * @author: tianyi.zeng
 * @create: 27/9/2021 - 下午 4:05
 */
public class QueryProductTreeResponse {

    public QueryProductTreeResponse(ProductInfoDO root, List<ProductInfoDO> children) {
        this.root = root;
        this.children = children;
    }

    private ProductInfoDO root;

    private List<ProductInfoDO> children;

    public ProductInfoDO getRoot() {
        return root;
    }

    public void setRoot(ProductInfoDO root) {
        this.root = root;
    }

    public List<ProductInfoDO> getChildren() {
        return children;
    }

    public void setChildren(List<ProductInfoDO> children) {
        this.children = children;
    }
}
