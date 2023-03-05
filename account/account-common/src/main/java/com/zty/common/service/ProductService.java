package com.zty.common.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.zty.common.DO.AccountInfoDO;
import com.zty.common.DO.AccountProductRelationDO;
import com.zty.common.DO.ProductInfoDO;

/**
 * 产品
 */
public interface ProductService {

    /**
     * 分页获取产品
     * @param productInfoDO
     * @return
     */
    public Page<ProductInfoDO> getProductByPage(ProductInfoDO productInfoDO);

    /**
     * 获取满足条件的所有产品
     * @param productInfoDO
     * @return
     */
    public List<ProductInfoDO> getAllProduct(ProductInfoDO productInfoDO);

    /**
     * 获取该账号的所有产品
     * @param accountInfoDO
     * @return
     */
    public List<ProductInfoDO> getAllProduct(AccountInfoDO accountInfoDO);

    /**
     * 新增产品
     * @return 成功条数
     */
    public int addProduct(ProductInfoDO productInfoDO);

    /**
     * 修改产品
     * @return 成功条数
     */
    public int updateProduct(ProductInfoDO productInfoDO);

    /**
     * 开通产品
     * @return 成功条数
     */
    public int linkProduct(AccountProductRelationDO accountProductRelationDO);

    /**
     * 解除产品
     * @return 成功条数
     */
    public int unlinkProduct(AccountProductRelationDO accountProductRelationDO);

    /**
     * 检查账号是否已开通产品
     * @return
     */
    public boolean checkIsLink(int accountId, Integer... productIds);

    /**
     * 获取默认产品
     * @return
     */
    public ProductInfoDO getDefalutProduct();
}
