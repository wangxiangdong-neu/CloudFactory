package com.neu.cloudfactory.system.service;

import com.neu.cloudfactory.system.entity.ProductType;

import com.neu.cloudfactory.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *  Service接口
 *
 * @author wxd
 * @date 2021-07-17 22:10:03
 */
public interface IProductTypeService extends IService<ProductType> {

    ProductType findByName(String type);

    ProductType findByptId(long ptId);

    ProductType findProductTypeDetailList(String type);

    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param productType productType
     * @return IPage<ProductType>
     */
    IPage<ProductType> findProductTypeDetailList(QueryRequest request, ProductType productType);


    /**
     * 新增
     *
     * @param productType productType
     */
    void createProductType(ProductType productType);

    /**
     * 修改
     *
     * @param productType productType
     */
    void updateProductType(ProductType productType);

    /**
     * 删除
     *
     * @param productTypeIds 工厂id数组
     */
    void deleteProductTypes(String[] productTypeIds);
}
