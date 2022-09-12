package com.zza.antiepidemic.modules.Wechat.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.conf.EnableAutoFill;
import lombok.Data;

@Data
@TableName("record")
@EnableAutoFill
public class Record {
    private static final long serialVersionUID  = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    @TableField(value = "phone")
    private String phone;

    @TableField(value = "health_code")
    private String healthCode;

    @TableField(value = "travel_code")
    private String travelCode;

    @TableField(value = "report")
    private String report;



}
