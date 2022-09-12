package com.zza.antiepidemic.modules.dataManage.dao;


import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import com.zza.antiepidemic.modules.staffManage.entity.Staff;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DataManageDao extends MppBaseMapper<Staff> {




    public List<Staff> currentUnfinished();
}
