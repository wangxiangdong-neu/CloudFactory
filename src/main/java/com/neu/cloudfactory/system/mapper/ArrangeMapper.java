package com.neu.cloudfactory.system.mapper;

import com.neu.cloudfactory.system.entity.Arrange;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  Mapper
 *
 * @author wxd
 * @date 2021-07-18 15:40:23
 */
public interface ArrangeMapper extends BaseMapper<Arrange> {

    Arrange findByName(String name);

    Arrange findByaId(long aId);

    /**
     * 查找工厂详细信息
     *
     * @param page 分页对象
     * @param arrange 工厂对象，用于传递查询条件
     * @return Ipage
     */
    <T> IPage<Arrange> findArrangeDetailPage(Page<T> page, @Param("arrange") Arrange arrange);

    long countArrangeDetail(@Param("arrange") Arrange arrange);

    /**
     * 查找工厂详细信息
     *
     * @param arrange 工厂对象，用于传递查询条件
     * @return List<Arrange>
     */
    List<Arrange> findArrangeDetail(@Param("arrange") Arrange arrange);
}
