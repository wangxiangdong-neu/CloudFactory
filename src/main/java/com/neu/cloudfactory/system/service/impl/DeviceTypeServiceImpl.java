package com.neu.cloudfactory.system.service.impl;

import com.neu.cloudfactory.common.entity.FebsConstant;
import com.neu.cloudfactory.common.entity.QueryRequest;
import com.neu.cloudfactory.common.utils.SortUtil;
import com.neu.cloudfactory.system.entity.DeviceType;
import com.neu.cloudfactory.system.mapper.DeviceTypeMapper;
import com.neu.cloudfactory.system.service.IDeviceTypeService;
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
 * @date 2021-07-17 10:17:23
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class DeviceTypeServiceImpl extends ServiceImpl<DeviceTypeMapper, DeviceType> implements IDeviceTypeService {

    private final DeviceTypeMapper deviceTypeMapper;

    @Override
    public DeviceType findByName(String type) {
        return baseMapper.findByName(type);
    }

    @Override
    public DeviceType findBydtId(long dtId) {
        return baseMapper.findBydtId(dtId);
    }

    @Override
    public DeviceType findDeviceTypeDetailList(String type) {
        DeviceType param = new DeviceType();
        param.setType(type);
        List<DeviceType> deviceTypes = baseMapper.findDeviceTypeDetail(param);
        return CollectionUtils.isNotEmpty(deviceTypes) ? deviceTypes.get(0) : null;
    }

    @Override
    public IPage<DeviceType> findDeviceTypeDetailList(QueryRequest request, DeviceType deviceType) {
        Page<DeviceType> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countDeviceTypeDetail(deviceType));
        SortUtil.handlePageSort(request, page, "dt_id", FebsConstant.ORDER_ASC, false);
        return baseMapper.findDeviceTypeDetailPage(page, deviceType);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createDeviceType(DeviceType deviceType) {
        this.save(deviceType);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDeviceType(DeviceType deviceType) {
        updateById(deviceType);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDeviceTypes(String[] deviceTypeIds) {
        List<String> list = Arrays.asList(deviceTypeIds);
        removeByIds(list);
    }
}
