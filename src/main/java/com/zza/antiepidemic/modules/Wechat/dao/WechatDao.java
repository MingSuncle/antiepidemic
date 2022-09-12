package com.zza.antiepidemic.modules.Wechat.dao;

import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import com.zza.antiepidemic.modules.Wechat.entity.Record;
import com.zza.antiepidemic.modules.staffManage.entity.Staff;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WechatDao extends MppBaseMapper<Record> {

        //根据电话号码获取员工信息
        public Staff findStaffByPhone(String phone);
}

