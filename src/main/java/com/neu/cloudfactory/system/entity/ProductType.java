package com.neu.cloudfactory.system.entity;


import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * Entity
 *
 * @author wxd
 * @date 2021-07-17 22:10:03
 */
@Data
@TableName("product_type")
public class ProductType {

    /**
     *
     */
    @TableId(value = "pt_id", type = IdType.AUTO)
    private Long ptId;

    /**
     *
     */
    @TableField("type")
    private String type;

}
