package com.neu.cloudfactory.system.service;

import com.neu.cloudfactory.system.entity.Device;

import com.neu.cloudfactory.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *  Service接口
 *
 * @author WXD
 * @date 2021-07-14 19:05:14
 */
public interface IDeviceService extends IService<Device> {
//    /**
//     * 查询（分页）
//     *
//     * @param request QueryRequest
//     * @param device device
//     * @return IPage<Device>
//     */
//    IPage<Device> findDevices(QueryRequest request, Device device);
//
//    /**
//     * 查询（所有）
//     *
//     * @param device device
//     * @return List<Device>
//     */
//    List<Device> findDevices(Device device);
//
//    /**
//     * 新增
//     *
//     * @param device device
//     */
//    void createDevice(Device device);
//
//    /**
//     * 修改
//     *
//     * @param device device
//     */
//    void updateDevice(Device device);
//
//    /**
//     * 删除
//     *
//     * @param device device
//     */
//    void deleteDevice(Device device);


    /**
     * ====================================================================================================
     * 通过设备名称查找设备
     *
     * @param name 设备名称
     * @return 设备
     */
    Device findByName(String name);

    Device findBydId(long dId);

    /**
     * 查找设备详细信息
     *
     * @param request request
     * @param device    设备对象，用于传递查询条件
     * @return IPage
     */
    IPage<Device> findDeviceDetailList(Device device, QueryRequest request);

    IPage<Device> findDeviceDetailList(long fId, QueryRequest request);

    /**
     * 通过设备名称查找设备详细信息
     *
     * @param name 设备名称
     * @return 设备信息
     */
    Device findDeviceDetailList(String name);

    /**
     * 新增设备
     *
     * @param device device
     */
    void createDevice(Device device);

    /**
     * 删除设备
     *
     * @param deviceIds 设备 id数组
     */
    void deleteDevices(String[] deviceIds);

    /**
     * 修改设备
     *
     * @param device device
     */
    void updateDevice(Device device);
}
