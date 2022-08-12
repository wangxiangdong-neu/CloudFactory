package com.neu.cloudfactory.system.service;

import com.neu.cloudfactory.system.entity.DeviceRental;

import com.neu.cloudfactory.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *  Service接口
 *
 * @author wxd
 * @date 2021-07-18 22:34:55
 */
public interface IDeviceRentalService extends IService<DeviceRental> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param deviceRental deviceRental
     * @return IPage<DeviceRental>
     */
    IPage<DeviceRental> findDeviceRentals(QueryRequest request, DeviceRental deviceRental);

    /**
     * 查询（所有）
     *
     * @param deviceRental deviceRental
     * @return List<DeviceRental>
     */
    List<DeviceRental> findDeviceRentals(DeviceRental deviceRental);

    /**
     * 新增
     *
     * @param deviceRental deviceRental
     */
    void createDeviceRental(DeviceRental deviceRental);

    /**
     * 修改
     *
     * @param deviceRental deviceRental
     */
    void updateDeviceRental(DeviceRental deviceRental);

    /**
     * 删除
     *
     * @param deviceRental deviceRental
     */
    void deleteDeviceRental(DeviceRental deviceRental);
}
