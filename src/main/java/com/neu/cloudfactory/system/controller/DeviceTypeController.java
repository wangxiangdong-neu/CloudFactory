package com.neu.cloudfactory.system.controller;

import com.neu.cloudfactory.common.annotation.ControllerEndpoint;
import com.neu.cloudfactory.common.entity.Strings;
import com.neu.cloudfactory.common.exception.FebsException;
import com.neu.cloudfactory.common.controller.BaseController;
import com.neu.cloudfactory.common.entity.FebsResponse;
import com.neu.cloudfactory.common.entity.QueryRequest;
import com.neu.cloudfactory.system.entity.DeviceType;
import com.neu.cloudfactory.system.service.IDeviceTypeService;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 *  Controller
 *
 * @author wxd
 * @date 2021-07-16 21:44:37
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("deviceType")
public class DeviceTypeController extends BaseController {

    private final IDeviceTypeService deviceTypeService;

    /**
     * =======================================================================================================
     *
     * @return
     */
    @GetMapping("{name}")
    public DeviceType getdeviceType(@NotBlank(message = "{required}") @PathVariable String name) {
        return deviceTypeService.findDeviceTypeDetailList(name);
    }

    @GetMapping("list")
    public FebsResponse deviceTypeList(QueryRequest request, DeviceType deviceType) {
        return new FebsResponse().success()
                .data(getDataTable(deviceTypeService.findDeviceTypeDetailList(request, deviceType)));
    }

    @PostMapping
    @ControllerEndpoint(operation = "新增设备类型", exceptionMessage = "新增设备类型失败")
    public FebsResponse addDeviceType(@Valid DeviceType deviceType) {
        deviceTypeService.createDeviceType(deviceType);
        return new FebsResponse().success();
    }

    @GetMapping("delete/{deviceTypeIds}")
    @ControllerEndpoint(operation = "删除设备类型", exceptionMessage = "删除设备类型失败")
    public FebsResponse deleteDeviceTypes(@NotBlank(message = "{required}") @PathVariable String deviceTypeIds) {
        deviceTypeService.deleteDeviceTypes(StringUtils.split(deviceTypeIds, Strings.COMMA));
        return new FebsResponse().success();
    }

    @PostMapping("update")
    @ControllerEndpoint(operation = "修改设备类型", exceptionMessage = "修改设备类型失败")
    public FebsResponse updateDeviceType(@Valid DeviceType deviceType) {
        if (deviceType.getDtId() == null) {
            throw new FebsException("设备类型ID为空");
        }
        deviceTypeService.updateDeviceType(deviceType);
        return new FebsResponse().success();
    }

}
