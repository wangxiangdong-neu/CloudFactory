package com.neu.cloudfactory.system.service.impl;

import com.neu.cloudfactory.common.entity.FebsConstant;
import com.neu.cloudfactory.common.entity.QueryRequest;
import com.neu.cloudfactory.common.utils.SortUtil;
import com.neu.cloudfactory.system.entity.Factory;
import com.neu.cloudfactory.system.mapper.FactoryMapper;
import com.neu.cloudfactory.system.service.IFactoryService;
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
 * @date 2021-07-16 21:44:37
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class FactoryServiceImpl extends ServiceImpl<FactoryMapper, Factory> implements IFactoryService {

    private final FactoryMapper factoryMapper;

    @Override
    public Factory findByName(String fname) {
        return baseMapper.findByName(fname);
    }

    @Override
    public Factory findByfId(long fId) {
        return baseMapper.findByfId(fId);
    }

    @Override
    public Factory findFactoryDetailList(String fname) {
        Factory param = new Factory();
        param.setFname(fname);
        List<Factory> factories = baseMapper.findFactoryDetail(param);
        return CollectionUtils.isNotEmpty(factories) ? factories.get(0) : null;
    }

    @Override
    public IPage<Factory> findFactoryDetailList(QueryRequest request, Factory factory) {
        Page<Factory> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countFactoryDetail(factory));
        SortUtil.handlePageSort(request, page, "f_id", FebsConstant.ORDER_ASC, false);
        return baseMapper.findFactoryDetailPage(page, factory);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createFactory(Factory factory) {
        this.save(factory);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateFactory(Factory factory) {
        updateById(factory);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteFactorys(String[] factoryIds) {
        List<String> list = Arrays.asList(factoryIds);
        removeByIds(list);
    }
}
