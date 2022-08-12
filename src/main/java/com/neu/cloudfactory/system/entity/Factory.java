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
 * @date 2021-07-16 21:44:37
 */
@Data
@TableName("factory")
public class Factory {

    /**
     *
     */
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long fId;

    /**
     *
     */
    @TableField("introduction")
    private String introduction;

    /**
     *
     */
    @TableField("fname")
    private String fname;

    /**
     *
     */
    @TableField("status")
    private String status;

    /**
     *
     */
    @TableField("u_id")
    private Long uId;

}
