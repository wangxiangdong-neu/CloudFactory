package com.neu.cloudfactory.system.mapper;

import com.neu.cloudfactory.system.entity.Bid;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  Mapper
 *
 * @author wxd
 * @date 2021-07-19 10:11:19
 */
public interface BidMapper extends BaseMapper<Bid> {

//    Bid findByName(String name);

    Bid findBybId(long bId);

    <T> IPage<Bid> findBidDetailPage(Page<T> page, @Param("bid") Bid bid);

    long countBidDetail(@Param("bid") Bid bid);

    List<Bid> findBidDetail(@Param("bid") Bid bid);

}
