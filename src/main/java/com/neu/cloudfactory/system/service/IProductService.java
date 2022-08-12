package com.neu.cloudfactory.system.service;

import com.neu.cloudfactory.system.entity.Product;
import com.neu.cloudfactory.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *  Service接口
 *
 * @author wxd
 * @date 2021-07-17 15:05:15
 */
public interface IProductService extends IService<Product> {
    Product findByName(String name);

    Product findBypId(long pId);

    Product findProductDetailList(String name);

    IPage<Product> findProductDetailList(QueryRequest request, Product product);

    void createProduct(Product product);

    /**
     * 修改
     *
     * @param product product
     */
    void updateProduct(Product product);

    /**
     * 删除
     *
     * @param productIds id数组
     */
    void deleteProducts(String[] productIds);
}
