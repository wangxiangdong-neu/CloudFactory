package com.neu.cloudfactory.system.mapper;

import com.neu.cloudfactory.system.entity.Device;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  Mapper
 *
 * @author WXD
 * @date 2021-07-14 19:05:14
 */
public interface DeviceMapper extends BaseMapper<Device> {
    /**
     * 通过设备名称查找设备
     *
     * @param deviceName 设备名称
     * @return 设备
     */
    Device findByName(String deviceName);

    Device findBydId(long dId);

    Device findByfId(long fId);

    /**
     * 查找设备详细信息
     *
     * @param page 分页对象
     * @param device 设备对象，用于传递查询条件
     * @return Ipage
     */
    <T> IPage<Device> findDeviceDetailPage(Page<T> page, @Param("device") Device device);

    long countDeviceDetail(@Param("device") Device device);

    /**
     * 查找设备详细信息
     *
     * @param device 设备对象，用于传递查询条件
     * @return List<Device>
     */
    List<Device> findDeviceDetail(@Param("device") Device device);

}
