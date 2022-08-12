package com.neu.cloudfactory.system.service.impl;

import com.neu.cloudfactory.common.entity.FebsConstant;
import com.neu.cloudfactory.common.entity.QueryRequest;
import com.neu.cloudfactory.common.utils.SortUtil;
import com.neu.cloudfactory.system.entity.Bid;
import com.neu.cloudfactory.system.mapper.BidMapper;
import com.neu.cloudfactory.system.service.IBidService;
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
 * @date 2021-07-19 10:11:19
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class BidServiceImpl extends ServiceImpl<BidMapper, Bid> implements IBidService {

    private final BidMapper bidMapper;

//    @Override
//    public Bid findByName(String name) {
//        return baseMapper.findByName(name);
//    }

    @Override
    public Bid findBybId(long bId) {
        return baseMapper.findBybId(bId);
    }

    @Override
    public Bid findBidDetailList(long bId) {
        Bid param = new Bid();
        param.setBId(bId);
        List<Bid> bids = baseMapper.findBidDetail(param);
        return CollectionUtils.isNotEmpty(bids) ? bids.get(0) : null;
    }

    @Override
    public IPage<Bid> findBidDetailList(QueryRequest request, Bid bid) {
        Page<Bid> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countBidDetail(bid));
        SortUtil.handlePageSort(request, page, "b_id", FebsConstant.ORDER_ASC, false);
        return baseMapper.findBidDetailPage(page, bid);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createBid(Bid bid) {
        this.save(bid);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateBid(Bid bid) {
        updateById(bid);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBids(String[] bidIds) {
        List<String> list = Arrays.asList(bidIds);
        removeByIds(list);
    }
}
