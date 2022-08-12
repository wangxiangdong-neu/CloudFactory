package com.neu.cloudfactory.system.controller;

import com.neu.cloudfactory.common.annotation.ControllerEndpoint;
import com.neu.cloudfactory.common.entity.Strings;
import com.neu.cloudfactory.common.exception.FebsException;
import com.neu.cloudfactory.common.controller.BaseController;
import com.neu.cloudfactory.common.entity.FebsResponse;
import com.neu.cloudfactory.common.entity.QueryRequest;
import com.neu.cloudfactory.system.entity.Userorder;
import com.neu.cloudfactory.system.service.IUserorderService;
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
@RequestMapping("userorder")
public class UserorderController extends BaseController {

    private final IUserorderService userorderService;

    /**
     * =======================================================================================================
     *
     * @return
     */
    @GetMapping("{name}")
    public Userorder getuserorder(@NotBlank(message = "{required}") @PathVariable String name) {
        return userorderService.findUserorderDetailList(name);
    }

    @GetMapping("list")
    public FebsResponse userorderList(QueryRequest request, Userorder userorder) {
        return new FebsResponse().success()
                .data(getDataTable(userorderService.findUserorderDetailList(request, userorder)));
    }

    @PostMapping
    @ControllerEndpoint(operation = "新增订单", exceptionMessage = "新增订单失败")
    public FebsResponse addUserorder(@Valid Userorder userorder) {
        userorderService.createUserorder(userorder);
        return new FebsResponse().success();
    }

    @GetMapping("delete/{userorderIds}")
    @ControllerEndpoint(operation = "删除订单", exceptionMessage = "删除订单失败")
    public FebsResponse deleteUserorders(@NotBlank(message = "{required}") @PathVariable String userorderIds) {
        userorderService.deleteUserorders(StringUtils.split(userorderIds, Strings.COMMA));
        return new FebsResponse().success();
    }

    @PostMapping("update")
    @ControllerEndpoint(operation = "修改订单", exceptionMessage = "修改订单失败")
    public FebsResponse updateUserorder(@Valid Userorder userorder) {
        if (userorder.getOId() == null) {
            throw new FebsException("订单ID为空");
        }
        userorderService.updateUserorder(userorder);
        return new FebsResponse().success();
    }

}
