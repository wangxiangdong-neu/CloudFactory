package com.neu.cloudfactory.system.controller;

import com.neu.cloudfactory.common.annotation.ControllerEndpoint;
import com.neu.cloudfactory.common.controller.BaseController;
import com.neu.cloudfactory.common.entity.FebsResponse;
import com.neu.cloudfactory.common.entity.QueryRequest;
import com.neu.cloudfactory.common.entity.Strings;
import com.neu.cloudfactory.common.exception.FebsException;
import com.neu.cloudfactory.system.entity.Device;
import com.neu.cloudfactory.system.service.IDeviceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @author wxd
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("device")
public class DeviceController extends BaseController {

    private final IDeviceService deviceService;

    @GetMapping("{name}")
    public Device getdevice(@NotBlank(message = "{required}") @PathVariable String name) {
        return deviceService.findDeviceDetailList(name);
    }

    @GetMapping("list")
    public FebsResponse deviceList(Device device, QueryRequest request) {
        return new FebsResponse().success()
                .data(getDataTable(deviceService.findDeviceDetailList(device, request)));
    }

    @GetMapping("flist")
    public FebsResponse factoryDeviceList(Device device, QueryRequest request) {
        device.setFId(1L);
        return new FebsResponse().success()
                .data(getDataTable(deviceService.findDeviceDetailList(device, request)));
    }

    @PostMapping
    @ControllerEndpoint(operation = "新增设备", exceptionMessage = "新增设备失败")
    public FebsResponse adddevice(@Valid Device device) {
        deviceService.createDevice(device);
        return new FebsResponse().success();
    }

    @GetMapping("delete/{deviceIds}")
    @ControllerEndpoint(operation = "删除设备", exceptionMessage = "删除设备失败")
    public FebsResponse deleteDevices(@NotBlank(message = "{required}") @PathVariable String deviceIds) {
        deviceService.deleteDevices(StringUtils.split(deviceIds, Strings.COMMA));
        return new FebsResponse().success();
    }

    @PostMapping("update")
    @ControllerEndpoint(operation = "修改设备", exceptionMessage = "修改设备失败")
    public FebsResponse updateDevice(@Valid Device device) {
        if (device.getDId() == null) {
            throw new FebsException("设备ID为空");
        }
        deviceService.updateDevice(device);
        return new FebsResponse().success();
    }
}
