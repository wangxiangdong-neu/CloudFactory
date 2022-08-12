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
 * @date 2021-07-17 10:17:23
 */
@Data
@TableName("device_type")
public class DeviceType {

    /**
     *
     */
    @TableId(value = "dt_id", type = IdType.AUTO)
    private Long dtId;

    /**
     *
     */
    @TableField("type")
    private String type;

}
