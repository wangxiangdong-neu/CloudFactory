package com.neu.cloudfactory.monitor.controller;

import com.neu.cloudfactory.common.annotation.ControllerEndpoint;
import com.neu.cloudfactory.common.controller.BaseController;
import com.neu.cloudfactory.common.entity.FebsResponse;
import com.neu.cloudfactory.common.utils.DateUtil;
import com.neu.cloudfactory.monitor.endpoint.FebsHttpTraceEndpoint;
import com.neu.cloudfactory.monitor.entity.FebsHttpTrace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wxd
 */
@Slf4j
@RestController
@RequestMapping("febs/actuator")
@RequiredArgsConstructor
public class FebsActuatorController extends BaseController {

    private final FebsHttpTraceEndpoint httpTraceEndpoint;

    @GetMapping("httptrace")
    @RequiresPermissions("httptrace:view")
    @ControllerEndpoint(exceptionMessage = "请求追踪失败")
    public FebsResponse httpTraces(String method, String url) {
        List<HttpTrace> httpTraceList = httpTraceEndpoint.traces().getTraces();
        List<FebsHttpTrace> febsHttpTraces = new ArrayList<>();
        httpTraceList.forEach(t -> {
            FebsHttpTrace febsHttpTrace = new FebsHttpTrace();
            febsHttpTrace.setRequestTime(DateUtil.formatInstant(t.getTimestamp(), DateUtil.FULL_TIME_SPLIT_PATTERN));
            febsHttpTrace.setMethod(t.getRequest().getMethod());
            febsHttpTrace.setUrl(t.getRequest().getUri());
            febsHttpTrace.setStatus(t.getResponse().getStatus());
            febsHttpTrace.setTimeTaken(t.getTimeTaken());
            if (StringUtils.isNotBlank(method) && StringUtils.isNotBlank(url)) {
                if (StringUtils.equalsIgnoreCase(method, febsHttpTrace.getMethod())
                        && StringUtils.containsIgnoreCase(febsHttpTrace.getUrl().toString(), url)) {
                    febsHttpTraces.add(febsHttpTrace);
                }
            } else if (StringUtils.isNotBlank(method)) {
                if (StringUtils.equalsIgnoreCase(method, febsHttpTrace.getMethod())) {
                    febsHttpTraces.add(febsHttpTrace);
                }
            } else if (StringUtils.isNotBlank(url)) {
                if (StringUtils.containsIgnoreCase(febsHttpTrace.getUrl().toString(), url)) {
                    febsHttpTraces.add(febsHttpTrace);
                }
            } else {
                febsHttpTraces.add(febsHttpTrace);
            }
        });
        return new FebsResponse().success()
                .data(getDataTable(febsHttpTraces, febsHttpTraces.size()));
    }
}
