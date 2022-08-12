package com.neu.cloudfactory.system.service.impl;

import com.neu.cloudfactory.common.entity.FebsConstant;
import com.neu.cloudfactory.common.entity.QueryRequest;
import com.neu.cloudfactory.common.utils.SortUtil;
import com.neu.cloudfactory.system.entity.WinBid;
import com.neu.cloudfactory.system.mapper.WinBidMapper;
import com.neu.cloudfactory.system.service.IWinBidService;
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
 * @date 2021-07-19 10:58:53
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class WinBidServiceImpl extends ServiceImpl<WinBidMapper, WinBid> implements IWinBidService {

    private final WinBidMapper winBidMapper;

    @Override
    public WinBid findBywbId(long wbId) {
        return baseMapper.findBywbId(wbId);
    }

    @Override
    public WinBid findWinBidDetailList(long wbId) {
        WinBid param = new WinBid();
        param.setWbId(wbId);
        List<WinBid> winBids = baseMapper.findWinBidDetail(param);
        return CollectionUtils.isNotEmpty(winBids) ? winBids.get(0) : null;
    }

    @Override
    public IPage<WinBid> findWinBidDetailList(QueryRequest request, WinBid winBid) {
        Page<WinBid> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countWinBidDetail(winBid));
        SortUtil.handlePageSort(request, page, "wb_id", FebsConstant.ORDER_ASC, false);
        return baseMapper.findWinBidDetailPage(page, winBid);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createWinBid(WinBid winBid) {
        this.save(winBid);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateWinBid(WinBid winBid) {
        updateById(winBid);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteWinBids(String[] winBidIds) {
        List<String> list = Arrays.asList(winBidIds);
        removeByIds(list);
    }
}
