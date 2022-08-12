package com.neu.cloudfactory.system.service;

import com.neu.cloudfactory.system.entity.WinBid;

import com.neu.cloudfactory.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *  Service接口
 *
 * @author wxd
 * @date 2021-07-19 10:58:53
 */
public interface IWinBidService extends IService<WinBid> {


    WinBid findBywbId(long wbId);

    WinBid findWinBidDetailList(long wbId);

    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param winBid winBid
     * @return IPage<WinBid>
     */
    IPage<WinBid> findWinBidDetailList(QueryRequest request, WinBid winBid);


    /**
     * 新增
     *
     * @param winBid winBid
     */
    void createWinBid(WinBid winBid);

    /**
     * 修改
     *
     * @param winBid winBid
     */
    void updateWinBid(WinBid winBid);

    /**
     * 删除
     *
     * @param winBidIds 工厂id数组
     */
    void deleteWinBids(String[] winBidIds);
}
