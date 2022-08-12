package com.neu.cloudfactory.system.service.impl;

import com.neu.cloudfactory.common.entity.FebsConstant;
import com.neu.cloudfactory.common.entity.QueryRequest;
import com.neu.cloudfactory.common.utils.SortUtil;
import com.neu.cloudfactory.system.entity.ProductType;
import com.neu.cloudfactory.system.mapper.ProductTypeMapper;
import com.neu.cloudfactory.system.service.IProductTypeService;
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
 * @date 2021-07-17 22:10:03
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ProductTypeServiceImpl extends ServiceImpl<ProductTypeMapper, ProductType> implements IProductTypeService {

    private final ProductTypeMapper productTypeMapper;

    @Override
    public ProductType findByName(String type) {
        return baseMapper.findByName(type);
    }

    @Override
    public ProductType findByptId(long ptId) {
        return baseMapper.findByptId(ptId);
    }

    @Override
    public ProductType findProductTypeDetailList(String type) {
        ProductType param = new ProductType();
        param.setType(type);
        List<ProductType> productTypes = baseMapper.findProductTypeDetail(param);
        return CollectionUtils.isNotEmpty(productTypes) ? productTypes.get(0) : null;
    }

    @Override
    public IPage<ProductType> findProductTypeDetailList(QueryRequest request, ProductType productType) {
        Page<ProductType> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countProductTypeDetail(productType));
        SortUtil.handlePageSort(request, page, "pt_id", FebsConstant.ORDER_ASC, false);
        return baseMapper.findProductTypeDetailPage(page, productType);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createProductType(ProductType productType) {
        this.save(productType);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateProductType(ProductType productType) {
        updateById(productType);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteProductTypes(String[] productTypeIds) {
        List<String> list = Arrays.asList(productTypeIds);
        removeByIds(list);
    }
}
