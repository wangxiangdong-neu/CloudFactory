package com.neu.cloudfactory.system.service.impl;

import com.neu.cloudfactory.common.entity.FebsConstant;
import com.neu.cloudfactory.common.entity.QueryRequest;
import com.neu.cloudfactory.common.utils.SortUtil;
import com.neu.cloudfactory.system.entity.*;
import com.neu.cloudfactory.system.entity.Device;
import com.neu.cloudfactory.system.mapper.DeviceMapper;
import com.neu.cloudfactory.system.service.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neu.cloudfactory.system.service.IDeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author wxd
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements IDeviceService {

    @Override
    public Device findByName(String name) {
        return baseMapper.findByName(name);
    }

    @Override
    public Device findBydId(long dId) {
        return baseMapper.findBydId(dId);
    }

    @Override
    public IPage<Device> findDeviceDetailList(Device device, QueryRequest request) {
        Page<Device> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countDeviceDetail(device));
        SortUtil.handlePageSort(request, page, "d_id", FebsConstant.ORDER_ASC, false);
        return baseMapper.findDeviceDetailPage(page, device);
    }

    @Override
    public IPage<Device> findDeviceDetailList(long fId,QueryRequest request) {
        Page<Device> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        Device param =new Device();
        param.setFId(fId);
        page.setTotal(baseMapper.countDeviceDetail(param));
        SortUtil.handlePageSort(request, page, "f_id", FebsConstant.ORDER_ASC, false);
        return baseMapper.findDeviceDetailPage(page, param);
    }

    @Override
    public Device findDeviceDetailList(String name) {
        Device param = new Device();
        param.setName(name);
        List<Device> devices = baseMapper.findDeviceDetail(param);
        return CollectionUtils.isNotEmpty(devices) ? devices.get(0) : null;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createDevice(Device device) {
        save(device);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDevices(String[] deviceIds) {
        List<String> list = Arrays.asList(deviceIds);
        // 删除设备
        removeByIds(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDevice(Device device) {
        updateById(device);
    }
}
