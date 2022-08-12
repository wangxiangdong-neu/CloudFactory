package com.neu.cloudfactory.system.service.impl;


import com.neu.cloudfactory.common.entity.FebsConstant;
import com.neu.cloudfactory.common.entity.QueryRequest;
import com.neu.cloudfactory.common.utils.SortUtil;
import com.neu.cloudfactory.system.entity.Userorder;
import com.neu.cloudfactory.system.mapper.UserorderMapper;
import com.neu.cloudfactory.system.service.IUserorderService;
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
 * @date 2021-07-18 10:13:43
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserorderServiceImpl extends ServiceImpl<UserorderMapper, Userorder> implements IUserorderService {

    private final UserorderMapper userorderMapper;

    @Override
    public Userorder findByName(String ordercode) {
        return baseMapper.findByName(ordercode);
    }

    @Override
    public Userorder findByoId(long oId) {
        return baseMapper.findByoId(oId);
    }

    @Override
    public Userorder findUserorderDetailList(String ordercode) {
        Userorder param = new Userorder();
        param.setOrdercode(ordercode);
        List<Userorder> userorders = baseMapper.findUserorderDetail(param);
        return CollectionUtils.isNotEmpty(userorders) ? userorders.get(0) : null;
    }

    @Override
    public IPage<Userorder> findUserorderDetailList(QueryRequest request, Userorder userorder) {
        Page<Userorder> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countUserorderDetail(userorder));
        SortUtil.handlePageSort(request, page, "o_id", FebsConstant.ORDER_ASC, false);
        return baseMapper.findUserorderDetailPage(page, userorder);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createUserorder(Userorder userorder) {
        this.save(userorder);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUserorder(Userorder userorder) {
        updateById(userorder);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUserorders(String[] userorderIds) {
        List<String> list = Arrays.asList(userorderIds);
        removeByIds(list);
    }
}
