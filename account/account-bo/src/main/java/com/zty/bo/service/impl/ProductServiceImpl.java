package com.zty.bo.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zty.bo.dao.AccountProductRelationDOMapper;
import com.zty.bo.dao.ProductInfoDOMapper;
import com.zty.common.DO.AccountInfoDO;
import com.zty.common.DO.AccountProductRelationDO;
import com.zty.common.DO.ProductInfoDO;
import com.zty.common.DO.example.AccountProductRelationDOExample;
import com.zty.common.DO.example.AccountRoleRelationDOExample;
import com.zty.common.DO.example.ProductInfoDOExample;
import com.zty.common.constant.ProductConstant;
import com.zty.common.service.ProductService;
import com.zty.framework.exception.ParamCheckException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * @author: tianyi.zeng
 * @create: 25/9/2021 - 下午 11:13
 */
@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductInfoDOMapper productInfoDOMapper;

    @Autowired
    private AccountProductRelationDOMapper productRelationDOMapper;

    /**
     * 分页获取产品
     *
     * @param productInfoDO
     * @return
     */
    @Override
    public Page<ProductInfoDO> getProductByPage(ProductInfoDO productInfoDO) {
        ProductInfoDOExample example = new ProductInfoDOExample();
        if (productInfoDO.getParentId() != null) {
            example.createCriteria().andParentIdEqualTo(productInfoDO.getParentId());
        }
        if (productInfoDO.getCreateBy() != null) {
            example.createCriteria().andCreateByEqualTo(productInfoDO.getCreateBy());
        }
        example.setOrderByClause("sort_index ASC");
        PageHelper.startPage(productInfoDO.getPage(), productInfoDO.getRows());
        return (Page<ProductInfoDO>) productInfoDOMapper.selectByExample(example);
    }

    /**
     * 获取满足条件的所有产品
     *
     * @param productInfoDO
     * @return
     */
    @Override
    public List<ProductInfoDO> getAllProduct(ProductInfoDO productInfoDO) {
        ProductInfoDOExample example = new ProductInfoDOExample();
        ProductInfoDOExample.Criteria criteria = example.createCriteria();
        if (productInfoDO.getParentId() != null) {
            criteria.andParentIdEqualTo(productInfoDO.getParentId());
        }
        if (productInfoDO.getCreateBy() != null) {
            criteria.andCreateByEqualTo(productInfoDO.getCreateBy());
        }
        // 只获取启用状态的数据
        criteria.andDisabledEqualTo((byte) 0);
        example.setOrderByClause("sort_index ASC");
        return productInfoDOMapper.selectByExample(example);
    }

    /**
     * 获取该账号的所有产品
     *
     * @param accountInfoDO
     * @return
     */
    @Override
    public List<ProductInfoDO> getAllProduct(AccountInfoDO accountInfoDO) {
        if (accountInfoDO.getId() == null) {
            throw new ParamCheckException("账号id不可为空");
        }
        AccountProductRelationDOExample relationDOExample = new AccountProductRelationDOExample();
        relationDOExample.createCriteria()
                .andAccountIdEqualTo(accountInfoDO.getId())
                .andDisabledEqualTo((byte) 0);
        List<AccountProductRelationDO> existRelations = productRelationDOMapper.selectByExample(relationDOExample);
        if (CollectionUtils.isEmpty(existRelations)) {
            return null;
        }
        ProductInfoDOExample example = new ProductInfoDOExample();
        example.createCriteria().andIdIn(existRelations.stream().map(AccountProductRelationDO::getProductId).collect(Collectors.toList()));
        example.setOrderByClause("sort_index ASC");
        return productInfoDOMapper.selectByExample(example);
    }

    /**
     * 新增产品
     *
     * @param productInfoDO
     * @return 成功条数
     */
    @Override
    public int addProduct(ProductInfoDO productInfoDO) {
        if (StringUtils.isBlank(productInfoDO.getName())) {
            throw new ParamCheckException("产品名称不可为空");
        }
        if (productInfoDO.getSortIndex() == null) {
            productInfoDO.setSortIndex(999);
        }
        if (productInfoDO.getParentId() == null) {
            productInfoDO.setParentId(0);
        }
        productInfoDO.setCreateTime(new Date());
        productInfoDO.setUpdateTime(new Date());
        if (productInfoDO.getParentId() == null) {
            productInfoDO.setParentId(0);
        }
        productInfoDO.setCreateBy(productInfoDO.getCurrentUID());
        productInfoDO.setDisabled((byte) 0);
        return productInfoDOMapper.insertSelective(productInfoDO);
    }

    /**
     * 修改产品
     *
     * @param productInfoDO
     * @return 成功条数
     */
    @Override
    public int updateProduct(ProductInfoDO productInfoDO) {
        if (productInfoDO.getId() == null) {
            throw new ParamCheckException("产品id不可为空");
        }
        productInfoDO.setUpdateTime(new Date());
        return productInfoDOMapper.updateByPrimaryKeySelective(productInfoDO);
    }

    /**
     * 开通产品
     *
     * @param accountProductRelationDO
     * @return 成功条数
     */
    @Override
    public int linkProduct(AccountProductRelationDO accountProductRelationDO) {
        if (accountProductRelationDO.getAccountId() == null) {
            throw new ParamCheckException("账号id不可为空");
        }
        if (accountProductRelationDO.getProductId() == null) {
            throw new ParamCheckException("产品id不可为空");
        }
        AccountProductRelationDOExample relationDOExample = new AccountProductRelationDOExample();
        relationDOExample.createCriteria()
                .andAccountIdEqualTo(accountProductRelationDO.getAccountId())
                .andProductIdEqualTo(accountProductRelationDO.getProductId());
        List<AccountProductRelationDO> existedRelations = productRelationDOMapper.selectByExample(relationDOExample);
        if (!CollectionUtils.isEmpty(existedRelations)) {
            AccountProductRelationDO existedRelation = existedRelations.get(0);
            if (existedRelation.getDisabled() == 0) {
                return 1;
            } else {
                existedRelation.setDisabled((byte) 0);
                existedRelation.setUpdateTime(new Date());
                return productRelationDOMapper.updateByPrimaryKeySelective(existedRelation);
            }
        } else {
            accountProductRelationDO.setCreateTime(new Date());
            accountProductRelationDO.setUpdateTime(new Date());
            accountProductRelationDO.setCreateBy(accountProductRelationDO.getCurrentUID());
            accountProductRelationDO.setDisabled((byte) 0);
            return productRelationDOMapper.insertSelective(accountProductRelationDO);
        }
    }

    /**
     * 解除产品
     *
     * @param accountProductRelationDO
     * @return 成功条数
     */
    @Override
    public int unlinkProduct(AccountProductRelationDO accountProductRelationDO) {
        if (accountProductRelationDO.getAccountId() == null) {
            throw new ParamCheckException("账号id不可为空");
        }
        if (accountProductRelationDO.getProductId() == null) {
            throw new ParamCheckException("产品id不可为空");
        }
        AccountProductRelationDOExample relationDOExample = new AccountProductRelationDOExample();
        relationDOExample.createCriteria()
                .andAccountIdEqualTo(accountProductRelationDO.getAccountId())
                .andProductIdEqualTo(accountProductRelationDO.getProductId());
        List<AccountProductRelationDO> existedRelations = productRelationDOMapper.selectByExample(relationDOExample);
        if (CollectionUtils.isEmpty(existedRelations)) {
            throw new ParamCheckException("查不到账号"+accountProductRelationDO.getAccountId()+"与产品"+accountProductRelationDO.getProductId()+"的绑定关系");
        } else {
            AccountProductRelationDO relationDO = existedRelations.get(0);
            if (relationDO.getDisabled() == null || relationDO.getDisabled() == 1) {
                return 1;
            }
            relationDO.setDisabled((byte) 1);
            relationDO.setUpdateTime(new Date());
            return productRelationDOMapper.updateByPrimaryKey(relationDO);
        }
    }

    /**
     * 检查账号是否已开通产品
     *
     * @return
     */
    @Override
    public boolean checkIsLink(int accountId, Integer... productIds) {
        if (accountId == 0) {
            throw new ParamCheckException("账号id不可为0");
        }
        AccountProductRelationDOExample example = new AccountProductRelationDOExample();
        example.createCriteria().andAccountIdEqualTo(accountId).andProductIdIn(Arrays.asList(productIds)).andDisabledEqualTo((byte)0);
        int count = productRelationDOMapper.countByExample(example);
        return count != 0;
    }

    /**
     * 获取默认产品
     *
     * @return
     */
    @Override
    public ProductInfoDO getDefalutProduct() {
        ProductInfoDOExample example = new ProductInfoDOExample();
        example.createCriteria()
                .andFldS1EqualTo(ProductConstant.DEFAULT_FLAG);
        List<ProductInfoDO> result = productInfoDOMapper.selectByExample(example);
        return CollectionUtils.isEmpty(result) ? null : result.get(0);
    }
}
