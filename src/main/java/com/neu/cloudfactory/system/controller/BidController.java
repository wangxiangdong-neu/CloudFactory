package com.neu.cloudfactory.system.controller;

import com.neu.cloudfactory.common.annotation.ControllerEndpoint;
import com.neu.cloudfactory.common.entity.Strings;
import com.neu.cloudfactory.common.exception.FebsException;
import com.neu.cloudfactory.common.controller.BaseController;
import com.neu.cloudfactory.common.entity.FebsResponse;
import com.neu.cloudfactory.common.entity.QueryRequest;
import com.neu.cloudfactory.system.entity.Bid;
import com.neu.cloudfactory.system.service.IBidService;
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
@RequestMapping("bid")
public class BidController extends BaseController {

    private final IBidService bidService;

    /**
     * =======================================================================================================
     *
     * @return
     */
    @GetMapping("{name}")
    public Bid getbid(@NotBlank(message = "{required}") @PathVariable long bId) {
        return bidService.findBidDetailList(bId);
    }

    @GetMapping("list")
    public FebsResponse bidList(QueryRequest request, Bid bid) {
        return new FebsResponse().success()
                .data(getDataTable(bidService.findBidDetailList(request, bid)));
    }

    @PostMapping
    @ControllerEndpoint(operation = "新增竞标订单", exceptionMessage = "新增竞标订单失败")
    public FebsResponse addBid(@Valid Bid bid) {
        bidService.createBid(bid);
        return new FebsResponse().success();
    }

    @GetMapping("delete/{bidIds}")
    @ControllerEndpoint(operation = "删除竞标订单", exceptionMessage = "删除竞标订单失败")
    public FebsResponse deleteBids(@NotBlank(message = "{required}") @PathVariable String bidIds) {
        bidService.deleteBids(StringUtils.split(bidIds, Strings.COMMA));
        return new FebsResponse().success();
    }

    @PostMapping("update")
    @ControllerEndpoint(operation = "修改竞标订单", exceptionMessage = "修改竞标订单失败")
    public FebsResponse updateBid(@Valid Bid bid) {
        if (bid.getBId() == null) {
            throw new FebsException("竞标订单ID为空");
        }
        bidService.updateBid(bid);
        return new FebsResponse().success();
    }

}
