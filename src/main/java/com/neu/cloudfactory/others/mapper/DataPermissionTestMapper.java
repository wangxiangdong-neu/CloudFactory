package com.neu.cloudfactory.others.mapper;

import com.neu.cloudfactory.common.annotation.DataPermission;
import com.neu.cloudfactory.others.entity.DataPermissionTest;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author wxd
 */
@DataPermission(methods = {"selectPage"})
public interface DataPermissionTestMapper extends BaseMapper<DataPermissionTest> {

}
