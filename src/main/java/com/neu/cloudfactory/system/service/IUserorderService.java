package com.neu.cloudfactory.system.service;

import com.neu.cloudfactory.common.entity.QueryRequest;
import com.neu.cloudfactory.system.entity.Userorder;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *  Service接口
 *
 * @author wxd
 * @date 2021-07-18 10:13:43
 */
public interface IUserorderService extends IService<Userorder> {

    Userorder findByName(String ordercode);

    Userorder findByoId(long oId);

    Userorder findUserorderDetailList(String ordercode);

    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param userorder userorder
     * @return IPage<Userorder>
     */
    IPage<Userorder> findUserorderDetailList(QueryRequest request, Userorder userorder);


    /**
     * 新增
     *
     * @param userorder userorder
     */
    void createUserorder(Userorder userorder);

    /**
     * 修改
     *
     * @param userorder userorder
     */
    void updateUserorder(Userorder userorder);

    /**
     * 删除
     *
     * @param userorderIds id数组
     */
    void deleteUserorders(String[] userorderIds);
}
