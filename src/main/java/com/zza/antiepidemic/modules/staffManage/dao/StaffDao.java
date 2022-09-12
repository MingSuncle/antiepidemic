package com.zza.antiepidemic.modules.staffManage.dao;


import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import com.github.jeffreyning.mybatisplus.conf.EnableMPP;
import com.zza.antiepidemic.modules.staffManage.entity.Staff;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@EnableMPP
@Mapper
public interface StaffDao  extends MppBaseMapper<Staff> {
    public void batchInsert(@Param("staffs") List<Staff> staffs);

    public Staff findByPhone(String phone);

    public List<Staff> staffList(Long cid,Integer currentPage,Integer pageSize);

    public Integer count(Long cid);
}
