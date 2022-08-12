package com.neu.cloudfactory.system.service;

import com.neu.cloudfactory.system.entity.Bid;

import com.neu.cloudfactory.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *  Service接口
 *
 * @author wxd
 * @date 2021-07-19 10:11:19
 */
public interface IBidService extends IService<Bid> {

//    Bid findByName(long name);

    Bid findBybId(long bId);

    Bid findBidDetailList(long bId);

    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param bid bid
     * @return IPage<Bid>
     */
    IPage<Bid> findBidDetailList(QueryRequest request, Bid bid);


    /**
     * 新增
     *
     * @param bid bid
     */
    void createBid(Bid bid);

    /**
     * 修改
     *
     * @param bid bid
     */
    void updateBid(Bid bid);

    /**
     * 删除
     *
     * @param bidIds 工厂id数组
     */
    void deleteBids(String[] bidIds);
}
