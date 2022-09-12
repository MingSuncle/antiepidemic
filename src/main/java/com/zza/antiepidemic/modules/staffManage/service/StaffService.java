package com.zza.antiepidemic.modules.staffManage.service;


import com.github.jeffreyning.mybatisplus.service.IMppService;
import com.zza.antiepidemic.modules.staffManage.entity.Staff;

import java.util.List;

public interface StaffService extends IMppService<Staff> {
    public void batchInsert(List<Staff> staffs);
}
