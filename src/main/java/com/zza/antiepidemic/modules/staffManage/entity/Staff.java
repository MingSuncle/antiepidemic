package com.zza.antiepidemic.modules.staffManage.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;


@Data
@TableName("staff")
public class Staff {
    @Value(value="uid")
    @TableId(value = "uid",type = IdType.AUTO)
    private Long uid;

    @Value(value = "姓名")
    @TableField(value = "name")
    private String name;

    @Value(value = "电话")
    @TableField(value = "phone")
    private String phone;

    @Value(value = "部门")
    @TableField(value = "department")
    private String department;

    @Value(value = "职位")
    @TableField(value = "position")
    private String position;

    @Value(value = "地址")
    @TableField(value = "address")
    private String address;

    @TableField(value = "cid")
    private String cid;

    @TableField(exist = false)
    private String company;
}
