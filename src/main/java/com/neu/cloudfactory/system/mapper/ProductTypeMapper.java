package com.neu.cloudfactory.system.mapper;

import com.neu.cloudfactory.system.entity.ProductType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  Mapper
 *
 * @author wxd
 * @date 2021-07-17 22:10:03
 */
public interface ProductTypeMapper extends BaseMapper<ProductType> {

    ProductType findByName(String type);

    ProductType findByptId(long ptId);

    <T> IPage<ProductType> findProductTypeDetailPage(Page<T> page, @Param("productType") ProductType productType);

    long countProductTypeDetail(@Param("productType") ProductType productType);

    List<ProductType> findProductTypeDetail(@Param("productType") ProductType productType);

}
