package com.zza.antiepidemic.modules.staffManage.service.impl;


import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.zza.antiepidemic.modules.staffManage.dao.StaffDao;
import com.zza.antiepidemic.modules.staffManage.entity.Staff;
import com.zza.antiepidemic.modules.staffManage.service.StaffService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffServiceImpl extends MppServiceImpl <StaffDao,Staff> implements StaffService {

    private StaffDao staffdao;
    @Override
    public void batchInsert(List<Staff> staffs){

    }
}
