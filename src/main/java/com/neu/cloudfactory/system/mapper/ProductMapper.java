package com.neu.cloudfactory.system.mapper;

import com.neu.cloudfactory.system.entity.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  Mapper
 *
 * @author wxd
 * @date 2021-07-17 15:05:15
 */
public interface ProductMapper extends BaseMapper<Product> {


    Product findByName(String name);

    Product findBypId(long pId);

    <T> IPage<Product> findProductDetailPage(Page<T> page, @Param("product") Product product);

    long countProductDetail(@Param("product") Product product);

    List<Product> findProductDetail(@Param("product") Product product);

}
