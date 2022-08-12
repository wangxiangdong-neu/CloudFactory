package com.neu.cloudfactory.system.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Entity
 *
 * @author wxd
 * @date 2021-07-19 10:58:53
 */
@Data
@TableName("win_bid")
public class WinBid {

    /**
     *
     */
    @TableId(value = "wb_id", type = IdType.AUTO)
    private Long wbId;

    /**
     *
     */
    @TableField("o_id")
    private Long oId;

    /**
     *
     */
    @TableField("f_id")
    private Long fId;

}
