package com.neu.cloudfactory.system.mapper;

import com.neu.cloudfactory.system.entity.Userorder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 *  Mapper
 *
 * @author wxd
 * @date 2021-07-18 10:13:43
 */
public interface UserorderMapper extends BaseMapper<Userorder> {

    Userorder findByName(String ordercode);

    Userorder findByoId(long oId);

    <T> IPage<Userorder> findUserorderDetailPage(Page<T> page, @Param("userorder") Userorder userorder);

    long countUserorderDetail(@Param("userorder") Userorder userorder);

    List<Userorder> findUserorderDetail(@Param("userorder") Userorder userorder);

}
