package com.neu.cloudfactory.system.controller;

import com.neu.cloudfactory.common.annotation.ControllerEndpoint;
import com.neu.cloudfactory.common.entity.Strings;
import com.neu.cloudfactory.common.exception.FebsException;
import com.neu.cloudfactory.common.controller.BaseController;
import com.neu.cloudfactory.common.entity.FebsResponse;
import com.neu.cloudfactory.common.entity.QueryRequest;
import com.neu.cloudfactory.system.entity.Arrange;
import com.neu.cloudfactory.system.service.IArrangeService;
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
@RequestMapping("arrange")
public class ArrangeController extends BaseController {

    private final IArrangeService arrangeService;

    /**
     * =======================================================================================================
     *
     * @return
     */
    @GetMapping("{name}")
    public Arrange getarrange(@NotBlank(message = "{required}") @PathVariable String name) {
        return arrangeService.findArrangeDetailList(name);
    }

    @GetMapping("list")
    public FebsResponse arrangeList(QueryRequest request, Arrange arrange) {
        return new FebsResponse().success()
                .data(getDataTable(arrangeService.findArrangeDetailList(request, arrange)));
    }

    @PostMapping
    @ControllerEndpoint(operation = "新增排产", exceptionMessage = "新增排产失败")
    public FebsResponse addArrange(@Valid Arrange arrange) {
        arrangeService.createArrange(arrange);
        return new FebsResponse().success();
    }

    @GetMapping("delete/{arrangeIds}")
    @ControllerEndpoint(operation = "删除排产", exceptionMessage = "删除排产失败")
    public FebsResponse deleteArranges(@NotBlank(message = "{required}") @PathVariable String arrangeIds) {
        arrangeService.deleteArranges(StringUtils.split(arrangeIds, Strings.COMMA));
        return new FebsResponse().success();
    }

    @PostMapping("update")
    @ControllerEndpoint(operation = "修改排产", exceptionMessage = "修改排产失败")
    public FebsResponse updateArrange(@Valid Arrange arrange) {
        if (arrange.getAId() == null) {
            throw new FebsException("排产ID为空");
        }
        arrangeService.updateArrange(arrange);
        return new FebsResponse().success();
    }

}
