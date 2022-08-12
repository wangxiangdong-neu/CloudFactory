package com.neu.cloudfactory.system.service;

import com.neu.cloudfactory.system.entity.Arrange;

import com.neu.cloudfactory.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *  Service接口
 *
 * @author wxd
 * @date 2021-07-18 15:40:23
 */
public interface IArrangeService extends IService<Arrange> {

    Arrange findByName(String name);

    Arrange findByaId(long aId);

    Arrange findArrangeDetailList(String name);

    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param arrange arrange
     * @return IPage<Arrange>
     */
    IPage<Arrange> findArrangeDetailList(QueryRequest request, Arrange arrange);


    /**
     * 新增
     *
     * @param arrange arrange
     */
    void createArrange(Arrange arrange);

    /**
     * 修改
     *
     * @param arrange arrange
     */
    void updateArrange(Arrange arrange);

    /**
     * 删除
     *
     * @param arrangeIds 工厂id数组
     */
    void deleteArranges(String[] arrangeIds);
}
