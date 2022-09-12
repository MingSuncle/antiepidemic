package com.zza.antiepidemic.modules.platUser.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("company_user")
public class CompanyUser {
    @TableId(value = "uid", type = IdType.AUTO)
    private Long uid;

    @TableField(value = "u_name")
    private String name;

    @TableField(value = "password")
    private String password;

    @TableField(value = "cid")
    private Integer companyId;


    @TableField(value = "enabled")
    private Integer enabled;
}

