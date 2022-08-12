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
 * @date 2021-07-18 15:40:23
 */
@Data
@TableName("arrange")
public class Arrange {

    /**
     *
     */
    @TableId(value = "a_id", type = IdType.AUTO)
    private Long aId;

    /**
     *
     */
    @TableField("o_id")
    private Long oId;

    /**
     *
     */
    @TableField("d_id")
    private Long dId;

    /**
     *
     */
    @TableField("start_date")
    private String startDate;

    /**
     *
     */
    @TableField("end_date")
    private String endDate;

}
