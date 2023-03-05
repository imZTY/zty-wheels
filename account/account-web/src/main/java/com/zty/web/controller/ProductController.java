package com.zty.web.controller;

import java.util.ArrayList;
import java.util.List;

import com.github.pagehelper.Page;
import com.zty.common.DO.AccountInfoDO;
import com.zty.common.DO.AccountProductRelationDO;
import com.zty.common.DO.ProductInfoDO;
import com.zty.common.service.AccountService;
import com.zty.common.service.ProductService;
import com.zty.framework.annotation.CheckToken;
import com.zty.framework.dto.ResultDTO;
import com.zty.framework.exception.BusinessException;
import com.zty.framework.exception.NetworkException;
import com.zty.framework.exception.ParamCheckException;
import com.zty.web.request.QueryProductTreeRequest;
import com.zty.web.response.QueryProductTreeResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 产品管理接口:
 * 1. 管理台 - 分页、新增、修改(禁用)
 * 2. 账号相关 - 绑定、解绑
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    private ProductService productService;

    @CheckToken
    @GetMapping("/getTree")
    public ResultDTO getTree(QueryProductTreeRequest request){
        int currentUID = request.getCurrentUID();
        // 参数校验
        if (currentUID == 0){
            return ResultDTO.error(403, "用户未登陆");
        }
        // 执行业务
        try {
            ProductInfoDO queryRoot = new ProductInfoDO();
            queryRoot.setParentId(0);
            List<ProductInfoDO> rootProducts = productService.getAllProduct(queryRoot);
            List<QueryProductTreeResponse> responseList = new ArrayList<>();
            if (!CollectionUtils.isEmpty(rootProducts)) {
                rootProducts.stream().forEach(root -> {
                    ProductInfoDO queryChild = new ProductInfoDO();
                    queryChild.setParentId(root.getId());
                    List<ProductInfoDO> childProducts = productService.getAllProduct(queryChild);
                    responseList.add(new QueryProductTreeResponse(root, childProducts));
                });
            }
            return ResultDTO.success(responseList);
        } catch (ClassCastException e) {
            log.error("获取产品树异常, ", e);
            return ResultDTO.error(500, "类型转换异常"+e.getMessage());
        } catch (ParamCheckException e) {
            log.warn("获取产品树 参数检查异常, ", e);
            if (StringUtils.isNotBlank(e.getKeyName())) {
                return ResultDTO.error(403, e.getErrorMsg(), e);
            } else {
                return ResultDTO.error(403, e.getErrorMsg(), request);
            }
        } catch (BusinessException e) {
            log.error("获取产品树 业务处理异常, ", e);
            return ResultDTO.error(500, e.getErrorMsg(), request);
        } catch (NetworkException e) {
            log.error("获取产品树 网络通信异常, ", e);
            return ResultDTO.error(504, e.getErrorMsg(), request);
        } catch (Exception e) {
            log.error("获取产品树 未知异常, ", e);
            return ResultDTO.error(500, e.getMessage(), request);
        }
    }

    @CheckToken
    @GetMapping("/pageList")
    public ResultDTO pageList(ProductInfoDO productInfoDO){
        int currentUID = productInfoDO.getCurrentUID();
        // 参数校验
        if (currentUID == 0){
            return ResultDTO.error(403, "用户未登陆");
        }
        // 检查当前账号是否有超管权限
        if (!accountService.checkIsAdmin(currentUID, 1)) {
            return ResultDTO.error(403, "当前账号无权操作");
        }
        // 执行业务
        try {
            Page<ProductInfoDO> pageResult = productService.getProductByPage(productInfoDO);
            return ResultDTO.success(pageResult.getResult(), pageResult.getTotal());
        } catch (ClassCastException e) {
            log.error("分页查询产品列表异常, ", e);
            return ResultDTO.error(500, "类型转换异常"+e.getMessage());
        } catch (ParamCheckException e) {
            log.warn("分页查询产品 参数检查异常, ", e);
            if (StringUtils.isNotBlank(e.getKeyName())) {
                return ResultDTO.error(403, e.getErrorMsg(), e);
            } else {
                return ResultDTO.error(403, e.getErrorMsg(), productInfoDO);
            }
        } catch (BusinessException e) {
            log.error("分页查询产品 业务处理异常, ", e);
            return ResultDTO.error(500, e.getErrorMsg(), productInfoDO);
        } catch (NetworkException e) {
            log.error("分页查询产品 网络通信异常, ", e);
            return ResultDTO.error(504, e.getErrorMsg(), productInfoDO);
        } catch (Exception e) {
            log.error("分页查询产品 未知异常, ", e);
            return ResultDTO.error(500, e.getMessage(), productInfoDO);
        }
    }

    @CheckToken
    @GetMapping("/getAll")
    public ResultDTO getAll(AccountProductRelationDO relationDO){
        int currentUID = relationDO.getCurrentUID();
        // 参数校验
        if (currentUID == 0){
            return ResultDTO.error(403, "用户未登陆");
        }
        if (relationDO.getAccountId() == null){
            return ResultDTO.error(403, "账号id必传", relationDO);
        }
        // 执行业务
        try {
            AccountInfoDO accountInfoDO = new AccountInfoDO();
            accountInfoDO.setId(relationDO.getAccountId());
            List<ProductInfoDO> result = productService.getAllProduct(accountInfoDO);
            return result == null ? ResultDTO.success(new ArrayList<>()) : ResultDTO.success(result);
        } catch (ClassCastException e) {
            log.error("获取账号已经开通产品异常, ", e);
            return ResultDTO.error(500, "类型转换异常"+e.getMessage());
        } catch (ParamCheckException e) {
            log.warn("获取账号已经开通产品 参数检查异常, ", e);
            if (StringUtils.isNotBlank(e.getKeyName())) {
                return ResultDTO.error(403, e.getErrorMsg(), e);
            } else {
                return ResultDTO.error(403, e.getErrorMsg(), relationDO);
            }
        } catch (BusinessException e) {
            log.error("获取账号已经开通产品 业务处理异常, ", e);
            return ResultDTO.error(500, e.getErrorMsg(), relationDO);
        } catch (NetworkException e) {
            log.error("获取账号已经开通产品 网络通信异常, ", e);
            return ResultDTO.error(504, e.getErrorMsg(), relationDO);
        } catch (Exception e) {
            log.error("获取账号已经开通产品 未知异常, ", e);
            return ResultDTO.error(500, e.getMessage(), relationDO);
        }
    }

    @CheckToken
    @PostMapping("/add")
    public ResultDTO add(ProductInfoDO productInfoDO){
        int currentUID = productInfoDO.getCurrentUID();
        // 参数校验
        if (currentUID == 0){
            return ResultDTO.error(403, "用户未登陆");
        }
        // 检查当前账号是否有超管权限
        if (!accountService.checkIsAdmin(currentUID, 1)) {
            return ResultDTO.error(403, "当前账号无权操作");
        }
        // 执行业务
        try {
            int rows = productService.addProduct(productInfoDO);
            return rows == 1 ? ResultDTO.success(rows) : ResultDTO.error(500, "修改条数异常", productInfoDO);
        } catch (ClassCastException e) {
            log.error("新增产品异常, ", e);
            return ResultDTO.error(500, "类型转换异常"+e.getMessage());
        } catch (ParamCheckException e) {
            log.warn("新增产品异常 参数检查异常, ", e);
            if (StringUtils.isNotBlank(e.getKeyName())) {
                return ResultDTO.error(403, e.getErrorMsg(), e);
            } else {
                return ResultDTO.error(403, e.getErrorMsg(), productInfoDO);
            }
        } catch (BusinessException e) {
            log.error("新增产品异常 业务处理异常, ", e);
            return ResultDTO.error(500, e.getErrorMsg(), productInfoDO);
        } catch (NetworkException e) {
            log.error("新增产品异常 网络通信异常, ", e);
            return ResultDTO.error(504, e.getErrorMsg(), productInfoDO);
        } catch (Exception e) {
            log.error("新增产品异常 未知异常, ", e);
            return ResultDTO.error(500, e.getMessage(), productInfoDO);
        }
    }

    @CheckToken
    @PostMapping("/update")
    public ResultDTO update(ProductInfoDO productInfoDO){
        int currentUID = productInfoDO.getCurrentUID();
        // 参数校验
        if (currentUID == 0){
            return ResultDTO.error(403, "用户未登陆");
        }
        // 检查当前账号是否有超管权限
        if (!accountService.checkIsAdmin(currentUID, 1)) {
            return ResultDTO.error(403, "当前账号无权操作");
        }
        // 执行业务
        try {
            int rows = productService.updateProduct(productInfoDO);
            return rows == 1 ? ResultDTO.success(rows) : ResultDTO.error(500, "修改条数异常", productInfoDO);
        } catch (ClassCastException e) {
            log.error("修改产品异常, ", e);
            return ResultDTO.error(500, "类型转换异常"+e.getMessage());
        } catch (ParamCheckException e) {
            log.warn("修改产品 参数检查异常, ", e);
            if (StringUtils.isNotBlank(e.getKeyName())) {
                return ResultDTO.error(403, e.getErrorMsg(), e);
            } else {
                return ResultDTO.error(403, e.getErrorMsg(), productInfoDO);
            }
        } catch (BusinessException e) {
            log.error("修改产品 业务处理异常, ", e);
            return ResultDTO.error(500, e.getErrorMsg(), productInfoDO);
        } catch (NetworkException e) {
            log.error("修改产品 网络通信异常, ", e);
            return ResultDTO.error(504, e.getErrorMsg(), productInfoDO);
        } catch (Exception e) {
            log.error("修改产品 未知异常, ", e);
            return ResultDTO.error(500, e.getMessage(), productInfoDO);
        }
    }



    @CheckToken
    @PostMapping("/link")
    public ResultDTO link(AccountProductRelationDO productRelationDO){
        int currentUID = productRelationDO.getCurrentUID();
        // 参数校验
        if (currentUID == 0){
            return ResultDTO.error(403, "用户未登陆");
        }
        // 检查当前账号是否有超管权限
        if (!accountService.checkIsAdmin(currentUID, 1)) {
            return ResultDTO.error(403, "当前账号无权操作");
        }
        // 执行业务
        try {
            int rows = productService.linkProduct(productRelationDO);
            return rows == 1 ? ResultDTO.success(rows) : ResultDTO.error(500, "修改条数异常", productRelationDO);
        } catch (ClassCastException e) {
            log.error("绑定产品异常, ", e);
            return ResultDTO.error(500, "类型转换异常"+e.getMessage());
        } catch (ParamCheckException e) {
            log.warn("绑定产品 参数检查异常, ", e);
            if (StringUtils.isNotBlank(e.getKeyName())) {
                return ResultDTO.error(403, e.getErrorMsg(), e);
            } else {
                return ResultDTO.error(403, e.getErrorMsg(), productRelationDO);
            }
        } catch (BusinessException e) {
            log.error("绑定产品 业务处理异常, ", e);
            return ResultDTO.error(500, e.getErrorMsg(), productRelationDO);
        } catch (NetworkException e) {
            log.error("绑定产品 网络通信异常, ", e);
            return ResultDTO.error(504, e.getErrorMsg(), productRelationDO);
        } catch (Exception e) {
            log.error("绑定产品 未知异常, ", e);
            return ResultDTO.error(500, e.getMessage(), productRelationDO);
        }
    }

    @CheckToken
    @PostMapping("/unlink")
    public ResultDTO unlink(AccountProductRelationDO productRelationDO){
        int currentUID = productRelationDO.getCurrentUID();
        // 参数校验
        if (currentUID == 0){
            return ResultDTO.error(403, "用户未登陆");
        }
        // 检查当前账号是否有超管权限
        if (!accountService.checkIsAdmin(currentUID, 1)) {
            return ResultDTO.error(403, "当前账号无权操作");
        }
        // 执行业务
        try {
            int rows = productService.unlinkProduct(productRelationDO);
            return rows == 1 ? ResultDTO.success(rows) : ResultDTO.error(500, "修改条数异常", productRelationDO);
        } catch (ClassCastException e) {
            log.error("解绑账号产品异常, ", e);
            return ResultDTO.error(500, "类型转换异常"+e.getMessage());
        } catch (ParamCheckException e) {
            log.warn("解绑账号产品 参数检查异常, ", e);
            if (StringUtils.isNotBlank(e.getKeyName())) {
                return ResultDTO.error(403, e.getErrorMsg(), e);
            } else {
                return ResultDTO.error(403, e.getErrorMsg(), productRelationDO);
            }
        } catch (BusinessException e) {
            log.error("解绑账号产品 业务处理异常, ", e);
            return ResultDTO.error(500, e.getErrorMsg(), productRelationDO);
        } catch (NetworkException e) {
            log.error("解绑账号产品 网络通信异常, ", e);
            return ResultDTO.error(504, e.getErrorMsg(), productRelationDO);
        } catch (Exception e) {
            log.error("解绑账号产品 未知异常, ", e);
            return ResultDTO.error(500, e.getMessage(), productRelationDO);
        }
    }
}
