package com.neu.cloudfactory.system.controller;

import com.neu.cloudfactory.common.annotation.Limit;
import com.neu.cloudfactory.common.controller.BaseController;
import com.neu.cloudfactory.common.entity.FebsResponse;
import com.neu.cloudfactory.common.exception.FebsException;
import com.neu.cloudfactory.common.properties.FebsProperties;
import com.neu.cloudfactory.common.service.ValidateCodeService;
import com.neu.cloudfactory.common.utils.Md5Util;
import com.neu.cloudfactory.monitor.service.ILoginLogService;
import com.neu.cloudfactory.system.entity.User;
import com.neu.cloudfactory.system.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.util.Map;

/**
 * @author wxd
 */
@Validated
@RestController
@RequiredArgsConstructor
public class LoginController extends BaseController {

    private final IUserService userService;
    private final ValidateCodeService validateCodeService;
    private final ILoginLogService loginLogService;
    private final FebsProperties properties;

    @PostMapping("login")
    @Limit(key = "login", period = 60, count = 10, name = "登录接口", prefix = "limit")
    public FebsResponse login(
            @NotBlank(message = "{required}") String username,
            @NotBlank(message = "{required}") String password,
            @NotBlank(message = "{required}") String verifyCode,
            boolean rememberMe, HttpServletRequest request) throws FebsException {
        validateCodeService.check(request.getSession().getId(), verifyCode);
        UsernamePasswordToken token = new UsernamePasswordToken(username,
                Md5Util.encrypt(username.toLowerCase(), password), rememberMe);
        super.login(token);
        // 保存登录日志
        loginLogService.saveLoginLog(username);
        return new FebsResponse().success().data(properties.getShiro().getSuccessUrl());
    }

    @PostMapping("register")
    public FebsResponse register(
            @NotBlank(message = "{required}") String username,
            @NotBlank(message = "{required}") String password,
            @NotBlank(message = "{required}") String utype,
            @NotBlank(message = "{required}") String mobile) throws FebsException {
        User user = userService.findByName(username);
        if (user != null) {
            throw new FebsException("该用户名已存在");
        }
        userService.register(username, password,utype,mobile);
        return new FebsResponse().success();
    }

    @GetMapping("index/{username}")
    public FebsResponse index(@NotBlank(message = "{required}") @PathVariable String username) {
        // 更新登录时间
        userService.updateLoginTime(username);
        // 获取首页数据
        Map<String, Object> data = loginLogService.retrieveIndexPageData(username);
        return new FebsResponse().success().data(data);
    }

    @GetMapping("images/captcha")
    @Limit(key = "get_captcha", period = 60, count = 10, name = "获取验证码", prefix = "limit")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException, FebsException {
        validateCodeService.create(request, response);
    }
}
