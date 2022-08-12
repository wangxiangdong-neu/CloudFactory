package com.neu.cloudfactory.system.controller;

import com.neu.cloudfactory.common.annotation.ControllerEndpoint;
import com.neu.cloudfactory.common.entity.Strings;
import com.neu.cloudfactory.common.exception.FebsException;
import com.neu.cloudfactory.common.controller.BaseController;
import com.neu.cloudfactory.common.entity.FebsResponse;
import com.neu.cloudfactory.common.entity.QueryRequest;
import com.neu.cloudfactory.system.entity.Factory;
import com.neu.cloudfactory.system.service.IFactoryService;
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
@RequestMapping("factory")
public class FactoryController extends BaseController {

    private final IFactoryService factoryService;

    /**
     * =======================================================================================================
     *
     * @return
     */
    @GetMapping("{fname}")
    public Factory getfactory(@NotBlank(message = "{required}") @PathVariable String fname) {
        return factoryService.findFactoryDetailList(fname);
    }

    @GetMapping("list")
    public FebsResponse factoryList(QueryRequest request, Factory factory) {
        return new FebsResponse().success()
                .data(getDataTable(factoryService.findFactoryDetailList(request, factory)));
    }

    @PostMapping
    @ControllerEndpoint(operation = "新增云工厂", exceptionMessage = "新增云工厂失败")
    public FebsResponse addFactory(@Valid Factory factory) {
        factoryService.createFactory(factory);
        return new FebsResponse().success();
    }

    @GetMapping("delete/{factoryIds}")
    @ControllerEndpoint(operation = "删除云工厂", exceptionMessage = "删除云工厂失败")
    public FebsResponse deleteFactorys(@NotBlank(message = "{required}") @PathVariable String factoryIds) {
        factoryService.deleteFactorys(StringUtils.split(factoryIds, Strings.COMMA));
        return new FebsResponse().success();
    }

    @PostMapping("update")
    @ControllerEndpoint(operation = "修改云工厂", exceptionMessage = "修改云工厂失败")
    public FebsResponse updateFactory(@Valid Factory factory) {
        if (factory.getFId() == null) {
            throw new FebsException("云工厂ID为空");
        }
        factoryService.updateFactory(factory);
        return new FebsResponse().success();
    }

}
