package com.neu.cloudfactory.system.service.impl;

import com.neu.cloudfactory.common.entity.FebsConstant;
import com.neu.cloudfactory.common.entity.QueryRequest;
import com.neu.cloudfactory.common.utils.SortUtil;
import com.neu.cloudfactory.system.entity.Product;
import com.neu.cloudfactory.system.mapper.ProductMapper;
import com.neu.cloudfactory.system.service.IProductService;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import lombok.RequiredArgsConstructor;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Arrays;
import java.util.List;

/**
 *  Service实现
 *
 * @author wxd
 * @date 2021-07-17 15:05:15
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    private final ProductMapper productMapper;

    @Override
    public Product findByName(String name) {
        return baseMapper.findByName(name);
    }

    @Override
    public Product findBypId(long pId) {
        return baseMapper.findBypId(pId);
    }

    @Override
    public Product findProductDetailList(String name) {
        Product param = new Product();
        param.setName(name);
        List<Product> products = baseMapper.findProductDetail(param);
        return CollectionUtils.isNotEmpty(products) ? products.get(0) : null;
    }

    @Override
    public IPage<Product> findProductDetailList(QueryRequest request, Product product) {
        Page<Product> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countProductDetail(product));
        SortUtil.handlePageSort(request, page, "p_id", FebsConstant.ORDER_ASC, false);
        return baseMapper.findProductDetailPage(page, product);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createProduct(Product product) {
        this.save(product);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateProduct(Product product) {
        updateById(product);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteProducts(String[] productIds) {
        List<String> list = Arrays.asList(productIds);
        removeByIds(list);
    }
}
