package com.neu.cloudfactory.system.service;

import com.neu.cloudfactory.system.entity.DeviceType;

import com.neu.cloudfactory.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *  Service接口
 *
 * @author wxd
 * @date 2021-07-17 10:17:23
 */
public interface IDeviceTypeService extends IService<DeviceType> {

    DeviceType findByName(String type);

    DeviceType findBydtId(long dtId);

    DeviceType findDeviceTypeDetailList(String type);
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param deviceType deviceType
     * @return IPage<DeviceType>
     */
    IPage<DeviceType> findDeviceTypeDetailList(QueryRequest request, DeviceType deviceType);


    /**
     * 新增
     *
     * @param deviceType deviceType
     */
    void createDeviceType(DeviceType deviceType);

    /**
     * 修改
     *
     * @param deviceType deviceType
     */
    void updateDeviceType(DeviceType deviceType);

    /**
     * 删除
     *
     * @param deviceTypeIds
     */
    void deleteDeviceTypes(String[] deviceTypeIds);
}
