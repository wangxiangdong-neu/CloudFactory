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
 * @date 2021-07-18 10:13:43
 */
@Data
@TableName("userorder")
public class Userorder {

    /**
     *
     */
    @TableId(value = "o_id", type = IdType.AUTO)
    private Long oId;

    /**
     *
     */
    @TableField("ordercode")
    private String ordercode;

    /**
     *
     */
    @TableField("product_name")
    private String productName;

    /**
     *
     */
    @TableField("purchase_quantity")
    private Long purchaseQuantity;

    /**
     *
     */
    @TableField("delivery_date")
    private String deliveryDate;

    /**
     *
     */
    @TableField("bid_deadline")
    private String bidDeadline;

    /**
     *
     */
    @TableField("receiver")
    private String receiver;

    /**
     *
     */
    @TableField("rec_contact")
    private String recContact;

    /**
     *
     */
    @TableField("rec_address")
    private String recAddress;

    /**
     *
     */
    @TableField("status")
    private String status;

}
