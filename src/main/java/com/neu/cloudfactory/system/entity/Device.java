package com.neu.cloudfactory.system.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Entity
 *
 * @author WXD
 * @date 2021-07-14 19:05:14
 */
@Data
@TableName("device")
public class Device {

    /**
     *
     */
    @TableField("code")
    private String code;

    /**
     *
     */
    @TableId(value = "d_id", type = IdType.AUTO)
    private Long dId;

    /**
     *
     */
    @TableField("d_resource")
    private String dResource;

    /**
     *
     */
    @TableField("device_status")
    private String deviceStatus;

    /**
     *
     */
    @TableField("dt_id")
    private Long dtId;

    /**
     *
     */
    @TableField("f_id")
    private Long fId;

    /**
     *
     */
    @TableField("format")
    private String format;

    /**
     *
     */
    @TableField("introduction")
    private String introduction;

    /**
     *
     */
    @TableField("name")
    private String name;

    /**
     *
     */
    @TableField("rental_status")
    private String rentalStatus;

    /**
     *
     */
    @TableField("superior_id")
    private Long superiorId;


}
