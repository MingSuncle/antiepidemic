package com.zza.antiepidemic.modules.staffManage.controller;


import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.zza.antiepidemic.common.utils.R;
import com.zza.antiepidemic.modules.staffManage.dao.StaffDao;
import com.zza.antiepidemic.modules.staffManage.entity.Staff;
import com.zza.antiepidemic.modules.staffManage.service.impl.StaffServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "小规模员工操作")
@ApiSupport(order = 20)
@RestController
@RequestMapping("/Staff")

public class StaffController {
    @Autowired
    private StaffServiceImpl staffService;

    @Autowired
    private StaffDao staffDao;

    @ApiOperation("新增或修改员工")
    @PostMapping("/addOrUpdateStaff")
    public R addStaff(@RequestBody Staff staff) {
        R r = R.ok();
        staffService.saveOrUpdate(staff);

        return r;
    }

    @ApiOperation("删除单个员工")
    @RequestMapping("/deleteStaff")
    public R deleteStaff(@RequestParam Long uid) {
        R r = R.ok();
        staffDao.deleteById(uid);
        return r;
    }




    @ApiOperation("根据电话查找员工信息")
    @PostMapping("/findByPhone")
    public R findByPhone(@RequestParam String phone){
        R r = R.ok();
        Staff staff = staffDao.findByPhone(phone);
        r.put("result",staff);
        return r;
    }

    @ApiOperation("根据企业ID获取员工信息列表")
    @GetMapping("/staffList")
    public R staffList(@RequestParam(value = "cid") Long cid,
                       @RequestParam(value = "current_page") Integer currentPage,
                       @RequestParam(value = "pageSize") Integer pageSize
    ){
        R r = R.ok();
        System.out.println(cid);
        System.out.println(currentPage);
        System.out.println(pageSize);
        List<Staff> result = staffDao.staffList(cid,(currentPage - 1) * pageSize, pageSize);
        Integer total = staffDao.count(cid);
        r.put("total",total);
        r.put("result",result);
        return r;
    }
}
