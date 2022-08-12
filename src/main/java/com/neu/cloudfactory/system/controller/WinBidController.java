package com.neu.cloudfactory.system.controller;

import com.neu.cloudfactory.common.annotation.ControllerEndpoint;
import com.neu.cloudfactory.common.entity.Strings;
import com.neu.cloudfactory.common.exception.FebsException;
import com.neu.cloudfactory.common.controller.BaseController;
import com.neu.cloudfactory.common.entity.FebsResponse;
import com.neu.cloudfactory.common.entity.QueryRequest;
import com.neu.cloudfactory.system.entity.WinBid;
import com.neu.cloudfactory.system.service.IWinBidService;
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
@RequestMapping("winBid")
public class WinBidController extends BaseController {

    private final IWinBidService winBidService;

    /**
     * =======================================================================================================
     *
     * @return
     */
    @GetMapping("{name}")
    public WinBid getwinBid(@NotBlank(message = "{required}") @PathVariable long wbId) {
        return winBidService.findWinBidDetailList(wbId);
    }

    @GetMapping("list")
    public FebsResponse winBidList(QueryRequest request, WinBid winBid) {
        return new FebsResponse().success()
                .data(getDataTable(winBidService.findWinBidDetailList(request, winBid)));
    }

    @PostMapping
    @ControllerEndpoint(operation = "新增选标", exceptionMessage = "新增选标失败")
    public FebsResponse addWinBid(@Valid WinBid winBid) {
        winBidService.createWinBid(winBid);
        return new FebsResponse().success();
    }

    @GetMapping("delete/{winBidIds}")
    @ControllerEndpoint(operation = "删除选标", exceptionMessage = "删除选标失败")
    public FebsResponse deleteWinBids(@NotBlank(message = "{required}") @PathVariable String winBidIds) {
        winBidService.deleteWinBids(StringUtils.split(winBidIds, Strings.COMMA));
        return new FebsResponse().success();
    }

    @PostMapping("update")
    @ControllerEndpoint(operation = "修改选标", exceptionMessage = "修改选标失败")
    public FebsResponse updateWinBid(@Valid WinBid winBid) {
        if (winBid.getWbId() == null) {
            throw new FebsException("选标ID为空");
        }
        winBidService.updateWinBid(winBid);
        return new FebsResponse().success();
    }

}
