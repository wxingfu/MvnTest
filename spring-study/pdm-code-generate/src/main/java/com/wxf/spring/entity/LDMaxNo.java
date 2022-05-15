package com.wxf.spring.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author weixf
 * @since 2022-03-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("LDMAXNO")
public class LDMaxNo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "NOTYPE", type = IdType.NONE)
    private String noType;

    @TableField("NOLIMIT")
    private String noLimit;

    @TableField("MAXNO")
    private BigDecimal maxNo;


}
