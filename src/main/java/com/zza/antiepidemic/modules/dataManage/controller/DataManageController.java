package com.zza.antiepidemic.modules.dataManage.controller;


import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.zza.antiepidemic.common.utils.R;
import com.zza.antiepidemic.modules.dataManage.dao.DataManageDao;
import com.zza.antiepidemic.modules.staffManage.entity.Staff;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "数据平台")
@ApiSupport(order = 20)
@RestController
@RequestMapping("/DataManage")

public class DataManageController {

    @Autowired
    private DataManageDao dataManageDao;

    @ApiOperation("当日未打卡员工名单")
    @GetMapping("/currentUnfinished")
    public R currentUnfinished() {
        R r = R.ok();
        List<Staff> result = dataManageDao.currentUnfinished();
        r.put("result",result);
        return r;
    }


}
