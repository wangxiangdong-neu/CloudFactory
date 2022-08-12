package com.neu.cloudfactory.system.controller;

import com.neu.cloudfactory.common.annotation.ControllerEndpoint;
import com.neu.cloudfactory.common.entity.Strings;
import com.neu.cloudfactory.common.exception.FebsException;
import com.neu.cloudfactory.common.controller.BaseController;
import com.neu.cloudfactory.common.entity.FebsResponse;
import com.neu.cloudfactory.common.entity.QueryRequest;
import com.neu.cloudfactory.system.entity.Product;
import com.neu.cloudfactory.system.service.IProductService;
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
@RequestMapping("product")
public class ProductController extends BaseController {

    private final IProductService productService;

    /**
     * =======================================================================================================
     *
     * @return
     */
    @GetMapping("{name}")
    public Product getproduct(@NotBlank(message = "{required}") @PathVariable String name) {
        return productService.findProductDetailList(name);
    }

    @GetMapping("list")
    public FebsResponse productList(QueryRequest request, Product product) {
        return new FebsResponse().success()
                .data(getDataTable(productService.findProductDetailList(request, product)));
    }

    @PostMapping
    @ControllerEndpoint(operation = "新增产品", exceptionMessage = "新增产品失败")
    public FebsResponse addProduct(@Valid Product product) {
        productService.createProduct(product);
        return new FebsResponse().success();
    }

    @GetMapping("delete/{productIds}")
    @ControllerEndpoint(operation = "删除产品", exceptionMessage = "删除产品失败")
    public FebsResponse deleteProducts(@NotBlank(message = "{required}") @PathVariable String productIds) {
        productService.deleteProducts(StringUtils.split(productIds, Strings.COMMA));
        return new FebsResponse().success();
    }

    @PostMapping("update")
    @ControllerEndpoint(operation = "修改产品", exceptionMessage = "修改产品失败")
    public FebsResponse updateProduct(@Valid Product product) {
        if (product.getPId() == null) {
            throw new FebsException("产品ID为空");
        }
        productService.updateProduct(product);
        return new FebsResponse().success();
    }

}
