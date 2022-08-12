package com.neu.cloudfactory.system.entity;

import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 *  Entity
 *
 * @author wxd
 * @date 2021-07-18 22:34:55
 */
@Data
@TableName("device_rental")
public class DeviceRental {

    /**
     * 
     */
    @TableId(value = "dr_id", type = IdType.AUTO)
    private Long drId;

    /**
     * 
     */
    @TableField("f_id")
    private Long fId;

    /**
     * 
     */
    @TableField("d_id")
    private Long dId;

    /**
     * 
     */
    @TableField("start_date")
    private Date startDate;

    /**
     * 
     */
    @TableField("rental_date")
    private Date rentalDate;

}
