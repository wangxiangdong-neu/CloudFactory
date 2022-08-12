package com.neu.cloudfactory.system.controller;

import com.neu.cloudfactory.common.controller.BaseController;
import com.neu.cloudfactory.common.entity.FebsConstant;
import com.neu.cloudfactory.common.utils.DateUtil;
import com.neu.cloudfactory.common.utils.FebsUtil;
import com.neu.cloudfactory.system.entity.*;
import com.neu.cloudfactory.system.service.*;
import com.neu.cloudfactory.system.entity.*;
import com.neu.cloudfactory.system.service.*;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.ExpiredSessionException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wxd
 */
@Controller("systemView")
@RequiredArgsConstructor
public class ViewController extends BaseController {

    private final IUserService userService;
    private final IUserDataPermissionService userDataPermissionService;

    @GetMapping("login")
    @ResponseBody
    public Object login(HttpServletRequest request) {
        if (FebsUtil.isAjaxRequest(request)) {
            throw new ExpiredSessionException();
        } else {
            ModelAndView mav = new ModelAndView();
            mav.setViewName(FebsUtil.view("login"));
            return mav;
        }
    }

    @GetMapping("unauthorized")
    public String unauthorized() {
        return FebsUtil.view("error/403");
    }


    @GetMapping("/")
    public String redirectIndex() {
        return "redirect:/index";
    }

    @GetMapping("index")
    public String index(Model model) {
        User principal = userService.findByName(getCurrentUser().getUsername());
        userService.doGetUserAuthorizationInfo(principal);
        principal.setPassword("It's a secret");
        model.addAttribute("user", principal);
        model.addAttribute("permissions", principal.getStringPermissions());
        model.addAttribute("roles", principal.getRoles());
        return "index";
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "layout")
    public String layout() {
        return FebsUtil.view("layout");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "password/update")
    public String passwordUpdate() {
        return FebsUtil.view("system/user/passwordUpdate");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "user/profile")
    public String userProfile() {
        return FebsUtil.view("system/user/userProfile");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "user/avatar")
    public String userAvatar() {
        return FebsUtil.view("system/user/avatar");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "user/profile/update")
    public String profileUpdate() {
        return FebsUtil.view("system/user/profileUpdate");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/user")
    @RequiresPermissions("user:view")
    public String systemUser() {
        return FebsUtil.view("system/user/user");
    }


    @GetMapping(FebsConstant.VIEW_PREFIX + "system/user/add")
    @RequiresPermissions("user:add")
    public String systemUserAdd() {
        return FebsUtil.view("system/user/userAdd");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/user/detail/{username}")
    @RequiresPermissions("user:view")
    public String systemUserDetail(@PathVariable String username, Model model) {
        resolveUserModel(username, model, true);
        return FebsUtil.view("system/user/userDetail");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/user/update/{username}")
    @RequiresPermissions("user:update")
    public String systemUserUpdate(@PathVariable String username, Model model) {
        resolveUserModel(username, model, false);
        return FebsUtil.view("system/user/userUpdate");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/role")
    @RequiresPermissions("role:view")
    public String systemRole() {
        return FebsUtil.view("system/role/role");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/menu")
    @RequiresPermissions("menu:view")
    public String systemMenu() {
        return FebsUtil.view("system/menu/menu");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "system/dept")
    @RequiresPermissions("dept:view")
    public String systemDept() {
        return FebsUtil.view("system/dept/dept");
    }

    @RequestMapping(FebsConstant.VIEW_PREFIX + "index")
    public String pageIndex() {
        return FebsUtil.view("index");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "404")
    public String error404() {
        return FebsUtil.view("error/404");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "403")
    public String error403() {
        return FebsUtil.view("error/403");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "500")
    public String error500() {
        return FebsUtil.view("error/500");
    }

    private void resolveUserModel(String username, Model model, Boolean transform) {
        User user = userService.findByName(username);
        String deptIds = userDataPermissionService.findByUserId(String.valueOf(user.getUserId()));
        user.setDeptIds(deptIds);
        model.addAttribute("user", user);
        if (transform) {
            String sex = user.getSex();
            switch (sex) {
                case User.SEX_MALE:
                    user.setSex("男");
                    break;
                case User.SEX_FEMALE:
                    user.setSex("女");
                    break;
                default:
                    user.setSex("保密");
                    break;
            }
        }
        if (user.getLastLoginTime() != null) {
            model.addAttribute("lastLoginTime", DateUtil.getDateFormat(user.getLastLoginTime(), DateUtil.FULL_TIME_SPLIT_PATTERN));
        }
    }

    /**
     * modified function =================================================================================
     * @return
    */

    /**
     * 产能中心 设备管理
     * @return
     */
    private final IDeviceService deviceService;

    private void resolveDeviceModel(long dId, Model model, Boolean transform) {
        Device device = deviceService.findBydId(dId);
        model.addAttribute("device", device);
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "super_manager/capacity/deviceManage")
    public String capacityDevice() {
        return FebsUtil.view("super_manager/capacity/deviceManage");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "super_manager/capacity/deviceAdd")
    public String capacityDeviceAdd() {
        return FebsUtil.view("super_manager/capacity/deviceAdd");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "super_manager/capacity/detail/{did}")
    public String systemDeviceDetail(@PathVariable long did, Model model) {
        resolveDeviceModel(did, model, true);
        return FebsUtil.view("super_manager/capacity/deviceDetail");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "super_manager/capacity/update/{did}")
    public String systemDeviceUpdate(@PathVariable long did, Model model) {
        resolveDeviceModel(did, model, false);
        return FebsUtil.view("super_manager/capacity/deviceUpdate");
    }

    /**
     * 设备类型管理
     * @return
     */

    private final IDeviceTypeService deviceTypeService;

    private void resolveDeviceTypeModel(long dtId, Model model, Boolean transform) {
        DeviceType deviceType = deviceTypeService.findBydtId(dtId);
        model.addAttribute("deviceType", deviceType);
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "super_manager/capacity/deviceType")
    public String deviceType() {
        return FebsUtil.view("super_manager/capacity/deviceType");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "super_manager/capacity/deviceTypeAdd")
    public String deviceTypeAdd() {
        return FebsUtil.view("super_manager/capacity/deviceTypeAdd");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "super_manager/capacity/deviceTypeDetail/{dtId}")
    public String systemDeviceTypeDetail(@PathVariable long dtId, Model model) {
        resolveDeviceTypeModel(dtId, model, true);
        return FebsUtil.view("super_manager/capacity/deviceTypeDetail");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "super_manager/capacity/deviceTypeUpdate/{dtId}")
    public String DeviceTypeUpdate(@PathVariable long dtId, Model model) {
        resolveDeviceTypeModel(dtId, model, false);
        return FebsUtil.view("super_manager/capacity/deviceTypeUpdate");
    }

    /**
     * 云工厂信息
     * @return
     */
    private final IFactoryService factoryService;

    private void resolveFactoryModel(long fId, Model model, Boolean transform) {
        Factory factory = factoryService.findByfId(fId);
        model.addAttribute("factory", factory);
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "super_manager/cloud_factory/factory_intro")
    public String factoryIntro() {
        return FebsUtil.view("super_manager/cloud_factory/factory_intro");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "super_manager/cloud_factory/add")
    public String factoryAdd() {
        return FebsUtil.view("super_manager/cloud_factory/factoryAdd");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "super_manager/cloud_factory/detail/{fid}")
    public String systemFactoryDetail(@PathVariable long fid, Model model) {
        resolveFactoryModel(fid, model, true);
        return FebsUtil.view("super_manager/cloud_factory/factoryDetail");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "super_manager/cloud_factory/update/{fId}")
    public String systemFactoryUpdate(@PathVariable long fId, Model model) {
        resolveFactoryModel(fId, model, false);
        return FebsUtil.view("super_manager/cloud_factory/factoryUpdate");
    }

    /**
     *产品管理 产品信息管理
     * @return
     */
    private final IProductService productService;

    private void resolveProductModel(long pId, Model model, Boolean transform) {
        Product product = productService.findBypId(pId);
        model.addAttribute("product", product);
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "super_manager/product_manage/intro_manage")
    public String introManage() {
        return FebsUtil.view("super_manager/product_manage/product");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "super_manager/product_manage/productAdd")
    public String productAdd() {
        return FebsUtil.view("super_manager/product_manage/productAdd");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "super_manager/product_manage/productDetail/{pId}")
    public String systemProductDetail(@PathVariable long pId, Model model) {
        resolveProductModel(pId, model, true);
        return FebsUtil.view("super_manager/product_manage/productDetail");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "super_manager/product_manage/productUpdate/{pId}")
    public String systemProductUpdate(@PathVariable long pId, Model model) {
        resolveProductModel(pId, model, false);
        return FebsUtil.view("super_manager/product_manage/productUpdate");
    }

    /**
     * 产品管理 产品类型管理
     * @return
     */
    private final IProductTypeService productTypeService;

    private void resolveProductTypeModel(long ptId, Model model, Boolean transform) {
        ProductType productType = productTypeService.findByptId(ptId);
        model.addAttribute("productType", productType);
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "super_manager/product_manage/productType")
    public String typeManage() {
        return FebsUtil.view("super_manager/product_manage/productType");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "super_manager/product_manage/productTypeAdd")
    public String productTypeAdd() {
        return FebsUtil.view("super_manager/product_manage/productTypeAdd");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "super_manager/product_manage/productTypeDetail/{ptId}")
    public String systemProductTypeDetail(@PathVariable long ptId, Model model) {
        resolveProductTypeModel(ptId, model, true);
        return FebsUtil.view("super_manager/product_manage/productTypeDetail");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "super_manager/product_manage/productTypeUpdate/{ptId}")
    public String systemProductTypeUpdate(@PathVariable long ptId, Model model) {
        resolveProductTypeModel(ptId, model, false);
        return FebsUtil.view("super_manager/product_manage/productTypeUpdate");
    }

    /**
     * 订单管理 订单基本信息
     * @return
     */
    private final IUserorderService userorderService;

    private void resolveUserorderModel(long oId, Model model, Boolean transform) {
        Userorder userorder = userorderService.findByoId(oId);
        model.addAttribute("userorder", userorder);
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "super_manager/order/order_manage")
    public String orderManage() {
        return FebsUtil.view("super_manager/order/order");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "super_manager/order/orderAdd")
    public String UserorderAdd() {
        return FebsUtil.view("super_manager/order/orderAdd");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "super_manager/order/orderDetail/{oId}")
    public String systemUserorderDetail(@PathVariable long oId, Model model) {
        resolveUserorderModel(oId, model, true);
        return FebsUtil.view("super_manager/order/orderDetail");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "super_manager/order/orderUpdate/{oId}")
    public String systemUserorderUpdate(@PathVariable long oId, Model model) {
        resolveUserorderModel(oId, model, false);
        return FebsUtil.view("super_manager/order/orderUpdate");
    }


    /**
     * 工厂管理 我的工厂 我的设备
     * @return
     */

    @GetMapping(FebsConstant.VIEW_PREFIX + "factory_manager/myfactory/mydevice")
    public String myDevice() {
        return FebsUtil.view("factory_manager/myfactory/mydevice");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "factory_manager/myfactory/mydeviceAdd")
    public String mydeviceDeviceAdd() {
        return FebsUtil.view("factory_manager/myfactory/mydeviceAdd");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "factory_manager/myfactory/mydeviceDetail/{did}")
    public String mydeviceDetail(@PathVariable long did, Model model) {
        resolveDeviceModel(did, model, true);
        return FebsUtil.view("factory_manager/myfactory/mydeviceDetail");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "factory_manager/myfactory/mydeviceUpdate/{did}")
    public String mydeviceUpdate(@PathVariable long did, Model model) {
        resolveDeviceModel(did, model, false);
        return FebsUtil.view("factory_manager/myfactory/mydeviceUpdate");
    }

    /**
     * 工厂管理 订单管理 订单接单
     * @return
     */

    @GetMapping(FebsConstant.VIEW_PREFIX + "factory_manager/order_manage/take_order")
    public String factoryOrderManage() {
        return FebsUtil.view("factory_manager/order_manage/order");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "factory_manager/order/orderAdd")
    public String factoryOrderAdd() {
        return FebsUtil.view("factory_manager/order_manage/orderAdd");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "factory_manager/order_manage/orderDetail/{oId}")
    public String factoryOrderDetail(@PathVariable long oId, Model model) {
        resolveUserorderModel(oId, model, true);
        return FebsUtil.view("factory_manager/order_manage/orderDetail");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "factory_manager/order_manage/orderUpdate/{oId}")
    public String factoryOrderUpdate(@PathVariable long oId, Model model) {
        resolveUserorderModel(oId, model, false);
        return FebsUtil.view("factory_manager/order_manage/orderUpdate");
    }

    /**
     * 工厂管理 订单管理 订单排产
     * @return
     */

    private final IArrangeService arrangeService;

    private void resolveArrangeModel(long aId, Model model, Boolean transform) {
        Arrange arrange = arrangeService.findByaId(aId);
        model.addAttribute("arrange", arrange);
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "factory_manager/order_manage/arrange_order")
    public String arrangeOrder() {
        return FebsUtil.view("factory_manager/order_manage/arrange");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "factory_manager/order_manage/arrangeAdd")
    public String arrangeAdd() {
        return FebsUtil.view("factory_manager/order_manage/arrangeAdd");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "factory_manager/order_manage/arrangeDetail/{aId}")
    public String arrangeDetail(@PathVariable long aId, Model model) {
        resolveArrangeModel(aId, model, true);
        return FebsUtil.view("factory_manager/order_manage/arrangeDetail");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "factory_manager/order_manage/arrangeUpdate/{aId}")
    public String arrangeUpdate(@PathVariable long aId, Model model) {
        resolveArrangeModel(aId, model, false);
        return FebsUtil.view("factory_manager/order_manage/arrangeUpdate");
    }

    /**
     * 经销商 订单管理 我的订单
     * @return
     */

    @GetMapping(FebsConstant.VIEW_PREFIX + "businessman/order_manage/myorder")
    public String myOrder() {
        return FebsUtil.view("businessman/order_manage/order");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "businessman/order_manage/orderAdd")
    public String myOrderAdd() {
        return FebsUtil.view("businessman/order_manage/orderAdd");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "businessman/order_manage/orderDetail/{oId}")
    public String myOrderDetail(@PathVariable long oId, Model model) {
        resolveUserorderModel(oId, model, true);
        return FebsUtil.view("businessman/order_manage/orderDetail");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "businessman/order_manage/orderUpdate/{oId}")
    public String myOrderUpdate(@PathVariable long oId, Model model) {
        resolveUserorderModel(oId, model, false);
        return FebsUtil.view("businessman/order_manage/orderUpdate");
    }

    /**
     * 工厂管理 订单管理 竞标订单
     */

    private final IBidService bidService;

    private void resolveBidModel(long bId, Model model, Boolean transform) {
        Bid bid = bidService.findBybId(bId);
        model.addAttribute("bid", bid);
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "factory_manager/order_manage/bid")
    public String bidOrder() {
        return FebsUtil.view("factory_manager/order_manage/bid");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "factory_manager/order_manage/bidAdd")
    public String bidAdd() {
        return FebsUtil.view("factory_manager/order_manage/bidAdd");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "factory_manager/order_manage/bidDetail/{bId}")
    public String bidDetail(@PathVariable long bId, Model model) {
        resolveBidModel(bId, model, true);
        return FebsUtil.view("factory_manager/order_manage/bidDetail");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "factory_manager/order_manage/bidUpdate/{bId}")
    public String bidUpdate(@PathVariable long bId, Model model) {
        resolveBidModel(bId, model, false);
        return FebsUtil.view("factory_manager/order_manage/bidUpdate");
    }

    /**
     * 经销商 订单管理 订单选标
     */

    private final IWinBidService winBidService;

    private void resolveWinBidModel(long wbId, Model model, Boolean transform) {
        WinBid winBid = winBidService.findBywbId(wbId);
        model.addAttribute("winBid", winBid);
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "businessman/order_manage/winBid")
    public String winBidOrder() {
        return FebsUtil.view("businessman/order_manage/winBid");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "businessman/order_manage/winBidAdd")
    public String winBidAdd() {
        return FebsUtil.view("businessman/order_manage/winBidAdd");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "businessman/order_manage/winBidDetail/{wbId}")
    public String winBidDetail(@PathVariable long wbId, Model model) {
        resolveWinBidModel(wbId, model, true);
        return FebsUtil.view("businessman/order_manage/winBidDetail");
    }

    @GetMapping(FebsConstant.VIEW_PREFIX + "businessman/order_manage/winBidUpdate/{wbId}")
    public String winBidUpdate(@PathVariable long wbId, Model model) {
        resolveWinBidModel(wbId, model, false);
        return FebsUtil.view("businessman/order_manage/winBidUpdate");
    }





    /**
     * ========================================================================================================
     * @return
     */

}


