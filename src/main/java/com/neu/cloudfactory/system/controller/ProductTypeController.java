package com.neu.cloudfactory.system.controller;

import com.neu.cloudfactory.common.annotation.ControllerEndpoint;
import com.neu.cloudfactory.common.entity.Strings;
import com.neu.cloudfactory.common.exception.FebsException;
import com.neu.cloudfactory.common.controller.BaseController;
import com.neu.cloudfactory.common.entity.FebsResponse;
import com.neu.cloudfactory.common.entity.QueryRequest;
import com.neu.cloudfactory.system.entity.ProductType;
import com.neu.cloudfactory.system.service.IProductTypeService;
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
@RequestMapping("productType")
public class ProductTypeController extends BaseController {

    private final IProductTypeService productTypeService;

    /**
     * =======================================================================================================
     *
     * @return
     */
    @GetMapping("{type}")
    public ProductType getproductType(@NotBlank(message = "{required}") @PathVariable String type) {
        return productTypeService.findProductTypeDetailList(type);
    }

    @GetMapping("list")
    public FebsResponse productTypeList(QueryRequest request, ProductType productType) {
        return new FebsResponse().success()
                .data(getDataTable(productTypeService.findProductTypeDetailList(request, productType)));
    }

    @PostMapping
    @ControllerEndpoint(operation = "新增产品类型", exceptionMessage = "新增产品类型失败")
    public FebsResponse addProductType(@Valid ProductType productType) {
        productTypeService.createProductType(productType);
        return new FebsResponse().success();
    }

    @GetMapping("delete/{productTypeIds}")
    @ControllerEndpoint(operation = "删除产品类型", exceptionMessage = "删除产品类型失败")
    public FebsResponse deleteProductTypes(@NotBlank(message = "{required}") @PathVariable String productTypeIds) {
        productTypeService.deleteProductTypes(StringUtils.split(productTypeIds, Strings.COMMA));
        return new FebsResponse().success();
    }

    @PostMapping("update")
    @ControllerEndpoint(operation = "修改产品类型", exceptionMessage = "修改产品类型失败")
    public FebsResponse updateProductType(@Valid ProductType productType) {
        if (productType.getPtId() == null) {
            throw new FebsException("产品类型ID为空");
        }
        productTypeService.updateProductType(productType);
        return new FebsResponse().success();
    }

}
