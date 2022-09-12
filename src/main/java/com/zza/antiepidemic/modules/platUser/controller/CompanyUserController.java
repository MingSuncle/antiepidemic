package com.zza.antiepidemic.modules.platUser.controller;


import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.zza.antiepidemic.common.utils.R;
import com.zza.antiepidemic.modules.platUser.dao.CompanyUserDao;
import com.zza.antiepidemic.modules.platUser.entity.CompanyUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "企业员工用户")
@ApiSupport(order = 20)
@RestController
@RequestMapping("/CompanyUser")

public class CompanyUserController {

    @Autowired
    private CompanyUserDao companyUserDao;

    @ApiOperation("新增企业员工")
    @PostMapping("/addCompanyUser")
    public R addStaff(@RequestBody CompanyUser companyUser) {
        R r = R.ok();
        companyUser.setEnabled(1);
        companyUserDao.insert(companyUser);
        return r;
    }

    @ApiOperation("删除单个员工")
    @PostMapping("/deleteCompanyUser")
    public R deleteStaff(@RequestParam Long uid) {
        R r = R.ok();
        companyUserDao.deleteById(uid);
        return r;
    }


    @ApiOperation("修改单个员工信息")
    @PostMapping("/editStaff")
    public R editStaff(@RequestBody CompanyUser companyUser) {
        R r = R.ok();
        companyUserDao.updateById(companyUser);
        return r;
    }

    @ApiOperation("激活")
    @PostMapping("/activateUser")
    public R activateUser(@RequestParam Long uid){
        R r = R.ok();
        companyUserDao.activateUser(uid);
        return r;
    }

    @ApiOperation("冻结")
    @PostMapping("/frozeUser")
    public R findByPhone(@RequestParam Long uid){
        R r = R.ok();
        companyUserDao.frozeUser(uid);
        return r;
    }

}
