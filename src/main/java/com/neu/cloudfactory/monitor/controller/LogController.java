package com.neu.cloudfactory.monitor.controller;

import com.neu.cloudfactory.common.annotation.ControllerEndpoint;
import com.neu.cloudfactory.common.controller.BaseController;
import com.neu.cloudfactory.common.entity.FebsResponse;
import com.neu.cloudfactory.common.entity.QueryRequest;
import com.neu.cloudfactory.common.entity.Strings;
import com.neu.cloudfactory.monitor.entity.SystemLog;
import com.neu.cloudfactory.monitor.service.ILogService;
import com.wuwenze.poi.ExcelKit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;

/**
 * @author wxd
 */
@Slf4j
@RestController
@RequestMapping("log")
@RequiredArgsConstructor
public class LogController extends BaseController {

    private final ILogService logService;

    @GetMapping("list")
    @RequiresPermissions("log:view")
    public FebsResponse logList(SystemLog log, QueryRequest request) {
        return new FebsResponse().success()
                .data(getDataTable(logService.findLogs(log, request)));
    }

    @GetMapping("delete/{ids}")
    @RequiresPermissions("log:delete")
    @ControllerEndpoint(exceptionMessage = "删除日志失败")
    public FebsResponse deleteLogs(@NotBlank(message = "{required}") @PathVariable String ids) {
        logService.deleteLogs(StringUtils.split(ids, Strings.COMMA));
        return new FebsResponse().success();
    }

    @GetMapping("excel")
    @RequiresPermissions("log:export")
    @ControllerEndpoint(exceptionMessage = "导出Excel失败")
    public void export(QueryRequest request, SystemLog lg, HttpServletResponse response) {
        ExcelKit.$Export(SystemLog.class, response)
                .downXlsx(logService.findLogs(lg, request).getRecords(), false);
    }
}
