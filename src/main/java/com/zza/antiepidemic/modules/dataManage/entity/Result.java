package com.zza.antiepidemic.modules.dataManage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName("result")
public class Result {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "r_id")
    String recordId;

    @TableField(value = "health_color")
    Integer healthColor;

    @TableField(value = "health_time")
    Date healthTime;

    @TableField(value = "health_name")
    String healthName;

    @TableField(value = "travel_color")
    Integer travelColor;

    @TableField(value = "travel_time")
    Date travelTime;

    @TableField(value = "travel_phone")
    String travelPhone;

    @TableField(value = "travel_place")
    String travelPlace;

    @TableField(value = "detect_time")
    Date detectTime;

    @TableField(value = "detect_name")
    String detectNamem;

    @TableField(value = "detect_result")
    String detectResult;

    @TableField(value = "result")
    String result;



}
