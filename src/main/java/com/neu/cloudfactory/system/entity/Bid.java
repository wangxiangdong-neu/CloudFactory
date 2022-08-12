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
 * @date 2021-07-19 10:11:19
 */
@Data
@TableName("bid")
public class Bid {

    /**
     *
     */
    @TableId(value = "b_id", type = IdType.AUTO)
    private Long bId;

    /**
     *
     */
    @TableField("f_id")
    private Long fId;

    /**
     *
     */
    @TableField("o_id")
    private Long oId;

    /**
     *
     */
    @TableField("price")
    private Long price;

}
