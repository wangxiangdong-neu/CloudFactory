package com.neu.cloudfactory.system.controller;

import com.neu.cloudfactory.common.annotation.ControllerEndpoint;
import com.neu.cloudfactory.common.utils.FebsUtil;
import com.neu.cloudfactory.common.entity.FebsConstant;
import com.neu.cloudfactory.common.controller.BaseController;
import com.neu.cloudfactory.common.entity.FebsResponse;
import com.neu.cloudfactory.common.entity.QueryRequest;
import com.neu.cloudfactory.system.entity.DeviceRental;
import com.neu.cloudfactory.system.service.IDeviceRentalService;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 *  Controller
 *
 * @author wxd
 * @date 2021-07-18 22:34:55
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class DeviceRentalController extends BaseController {

    private final IDeviceRentalService deviceRentalService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "deviceRental")
    public String deviceRentalIndex(){
        return FebsUtil.view("deviceRental/deviceRental");
    }

    @GetMapping("deviceRental")
    @ResponseBody
    @RequiresPermissions("deviceRental:list")
    public FebsResponse getAllDeviceRentals(DeviceRental deviceRental) {
        return new FebsResponse().success().data(deviceRentalService.findDeviceRentals(deviceRental));
    }

    @GetMapping("deviceRental/list")
    @ResponseBody
    @RequiresPermissions("deviceRental:list")
    public FebsResponse deviceRentalList(QueryRequest request, DeviceRental deviceRental) {
        Map<String, Object> dataTable = getDataTable(this.deviceRentalService.findDeviceRentals(request, deviceRental));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增DeviceRental", exceptionMessage = "新增DeviceRental失败")
    @PostMapping("deviceRental")
    @ResponseBody
    @RequiresPermissions("deviceRental:add")
    public FebsResponse addDeviceRental(@Valid DeviceRental deviceRental) {
        this.deviceRentalService.createDeviceRental(deviceRental);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除DeviceRental", exceptionMessage = "删除DeviceRental失败")
    @GetMapping("deviceRental/delete")
    @ResponseBody
    @RequiresPermissions("deviceRental:delete")
    public FebsResponse deleteDeviceRental(DeviceRental deviceRental) {
        this.deviceRentalService.deleteDeviceRental(deviceRental);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改DeviceRental", exceptionMessage = "修改DeviceRental失败")
    @PostMapping("deviceRental/update")
    @ResponseBody
    @RequiresPermissions("deviceRental:update")
    public FebsResponse updateDeviceRental(DeviceRental deviceRental) {
        this.deviceRentalService.updateDeviceRental(deviceRental);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改DeviceRental", exceptionMessage = "导出Excel失败")
    @PostMapping("deviceRental/excel")
    @ResponseBody
    @RequiresPermissions("deviceRental:export")
    public void export(QueryRequest queryRequest, DeviceRental deviceRental, HttpServletResponse response) {
        List<DeviceRental> deviceRentals = this.deviceRentalService.findDeviceRentals(queryRequest, deviceRental).getRecords();
        ExcelKit.$Export(DeviceRental.class, response).downXlsx(deviceRentals, false);
    }
}
