package com.neu.cloudfactory.system.mapper;

import com.neu.cloudfactory.system.entity.Factory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  Mapper
 *
 * @author wxd
 * @date 2021-07-16 21:44:37
 */
public interface FactoryMapper extends BaseMapper<Factory> {
    /**
     * 通过工厂名称查找工厂
     *
     * @param fname 工厂名称
     * @return 设备
     */
    Factory findByName(String fname);

    Factory findByfId(long fId);

    /**
     * 查找工厂详细信息
     *
     * @param page 分页对象
     * @param factory 工厂对象，用于传递查询条件
     * @return Ipage
     */
    <T> IPage<Factory> findFactoryDetailPage(Page<T> page, @Param("factory") Factory factory);

    long countFactoryDetail(@Param("factory") Factory factory);

    /**
     * 查找工厂详细信息
     *
     * @param factory 工厂对象，用于传递查询条件
     * @return List<Factory>
     */
    List<Factory> findFactoryDetail(@Param("factory") Factory factory);


}
