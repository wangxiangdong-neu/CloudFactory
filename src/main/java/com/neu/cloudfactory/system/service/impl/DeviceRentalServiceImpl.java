package com.neu.cloudfactory.system.service.impl;

import com.neu.cloudfactory.common.entity.QueryRequest;
import com.neu.cloudfactory.system.entity.DeviceRental;
import com.neu.cloudfactory.system.mapper.DeviceRentalMapper;
import com.neu.cloudfactory.system.service.IDeviceRentalService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import lombok.RequiredArgsConstructor;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 *  Service实现
 *
 * @author wxd
 * @date 2021-07-18 22:34:55
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class DeviceRentalServiceImpl extends ServiceImpl<DeviceRentalMapper, DeviceRental> implements IDeviceRentalService {

    private final DeviceRentalMapper deviceRentalMapper;

    @Override
    public IPage<DeviceRental> findDeviceRentals(QueryRequest request, DeviceRental deviceRental) {
        LambdaQueryWrapper<DeviceRental> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<DeviceRental> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<DeviceRental> findDeviceRentals(DeviceRental deviceRental) {
	    LambdaQueryWrapper<DeviceRental> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createDeviceRental(DeviceRental deviceRental) {
        this.save(deviceRental);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDeviceRental(DeviceRental deviceRental) {
        this.saveOrUpdate(deviceRental);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDeviceRental(DeviceRental deviceRental) {
        LambdaQueryWrapper<DeviceRental> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}
}
