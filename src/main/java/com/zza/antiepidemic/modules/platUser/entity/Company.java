package com.zza.antiepidemic.modules.platUser.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("company")
public class Company {
    @TableId(value = "c_id", type = IdType.AUTO)
    private Long cid;

    @TableField(value = "c_name")
    private String name;

    @TableField(value = "expire_date")
    private Date expireDate;


    @TableField(value = "enabled")
    private Integer enabled;
}
