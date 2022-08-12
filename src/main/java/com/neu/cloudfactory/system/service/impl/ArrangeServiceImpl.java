package com.neu.cloudfactory.system.service.impl;

import com.neu.cloudfactory.common.entity.FebsConstant;
import com.neu.cloudfactory.common.entity.QueryRequest;
import com.neu.cloudfactory.common.utils.SortUtil;
import com.neu.cloudfactory.system.entity.Arrange;
import com.neu.cloudfactory.system.mapper.ArrangeMapper;
import com.neu.cloudfactory.system.service.IArrangeService;
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
public class ArrangeServiceImpl extends ServiceImpl<ArrangeMapper, Arrange> implements IArrangeService {

    private final ArrangeMapper arrangeMapper;

    @Override
    public Arrange findByName(String name) {
        return baseMapper.findByName(name);
    }

    @Override
    public Arrange findByaId(long aId) {
        return baseMapper.findByaId(aId);
    }

    @Override
    public Arrange findArrangeDetailList(String name) {
        Arrange param = new Arrange();
        param.setStartDate(name);
        List<Arrange> arranges = baseMapper.findArrangeDetail(param);
        return CollectionUtils.isNotEmpty(arranges) ? arranges.get(0) : null;
    }

    @Override
    public IPage<Arrange> findArrangeDetailList(QueryRequest request, Arrange arrange) {
        Page<Arrange> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countArrangeDetail(arrange));
        SortUtil.handlePageSort(request, page, "a_id", FebsConstant.ORDER_ASC, false);
        return baseMapper.findArrangeDetailPage(page, arrange);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createArrange(Arrange arrange) {
        this.save(arrange);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateArrange(Arrange arrange) {
        updateById(arrange);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteArranges(String[] arrangeIds) {
        List<String> list = Arrays.asList(arrangeIds);
        removeByIds(list);
    }
}
