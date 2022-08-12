package com.neu.cloudfactory.system.service;

import com.neu.cloudfactory.system.entity.Factory;

import com.neu.cloudfactory.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *  Service接口
 *
 * @author wxd
 * @date 2021-07-16 21:44:37
 */
public interface IFactoryService extends IService<Factory> {

    Factory findByName(String fname);

    Factory findByfId(long fId);

    Factory findFactoryDetailList(String fname);

    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param factory factory
     * @return IPage<Factory>
     */
    IPage<Factory> findFactoryDetailList(QueryRequest request, Factory factory);


    /**
     * 新增
     *
     * @param factory factory
     */
    void createFactory(Factory factory);

    /**
     * 修改
     *
     * @param factory factory
     */
    void updateFactory(Factory factory);

    /**
     * 删除
     *
     * @param factoryIds 工厂id数组
     */
    void deleteFactorys(String[] factoryIds);


}
