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
 * @date 2021-07-17 15:05:15
 */
@Data
@TableName("product")
public class Product {

    /**
     *
     */
    @TableField("code")
    private String code;

    /**
     *
     */
    @TableField("description")
    private String description;

    /**
     *
     */
    @TableField("format")
    private String format;

    /**
     *
     */
    @TableField("name")
    private String name;

    /**
     *
     */
    @TableId(value = "p_id", type = IdType.AUTO)
    private Long pId;

    /**
     *
     */
    @TableField("pt_id")
    private Long ptId;

    /**
     *
     */
    @TableField("superior_id")
    private Long superiorId;

}
