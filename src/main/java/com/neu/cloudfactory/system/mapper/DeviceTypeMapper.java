package com.neu.cloudfactory.system.mapper;

import com.neu.cloudfactory.system.entity.DeviceType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  Mapper
 *
 * @author wxd
 * @date 2021-07-17 10:17:23
 */
public interface DeviceTypeMapper extends BaseMapper<DeviceType> {
    /**
     * 通过设备类型名称查找设备类型
     *
     * @param type 设备类型
     * @return 设备
     */
    DeviceType findByName(String type);

    DeviceType findBydtId(long dtId);

    /**
     * 查找设备类型详细信息
     *
     * @param page 分页对象
     * @param deviceType 设备类型对象，用于传递查询条件
     * @return Ipage
     */
    <T> IPage<DeviceType> findDeviceTypeDetailPage(Page<T> page, @Param("deviceType") DeviceType deviceType);

    long countDeviceTypeDetail(@Param("deviceType") DeviceType deviceType);

    /**
     * 查找设备类型详细信息
     *
     * @param deviceType 设备类型对象，用于传递查询条件
     * @return List<DeviceType>
     */
    List<DeviceType> findDeviceTypeDetail(@Param("deviceType") DeviceType deviceType);



}
