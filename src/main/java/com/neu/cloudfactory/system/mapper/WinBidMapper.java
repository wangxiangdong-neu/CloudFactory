package com.neu.cloudfactory.system.mapper;

import com.neu.cloudfactory.system.entity.WinBid;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  Mapper
 *
 * @author wxd
 * @date 2021-07-19 10:58:53
 */
public interface WinBidMapper extends BaseMapper<WinBid> {

    WinBid findBywbId(long wbId);

    <T> IPage<WinBid> findWinBidDetailPage(Page<T> page, @Param("winBid") WinBid winBid);

    long countWinBidDetail(@Param("winBid") WinBid winBid);

    List<WinBid> findWinBidDetail(@Param("winBid") WinBid winBid);


}
