package com.zza.antiepidemic.modules.Wechat.controller;


import com.github.jeffreyning.mybatisplus.conf.EnableMPP;
import com.zza.antiepidemic.common.utils.R;
import com.zza.antiepidemic.modules.Wechat.dao.WechatDao;
import com.zza.antiepidemic.modules.Wechat.entity.Record;
import com.zza.antiepidemic.modules.platUser.dao.CompanyDao;
import com.zza.antiepidemic.modules.staffManage.entity.Staff;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@Api(tags = "小程序端相关")
@EnableMPP
@RequestMapping("/Wechat")
public class WechatController {
    @Autowired
    private WechatDao wechatDao;

    @Autowired
    private CompanyDao companyDao;

    @ApiOperation("根据电话获取信息")
    @GetMapping("/getInformationByPhone")
    public R getInformationByPhone(@RequestParam(value = "phone") String phone){

        R r = R.ok();
        Staff staff = wechatDao.findStaffByPhone(phone);
        r.put("result",staff);
        return r;
    }


    @ApiOperation("上传两码一证")
    @PostMapping("/upload")
    public R heartDetect(@RequestParam(value = "phone") String phone,
                          @RequestPart("health_picture") MultipartFile healthPicture,
                          @RequestPart("travel_picture") MultipartFile travelPicture,
                          @RequestPart("report_picture") MultipartFile reportPicture){
        R r = R.ok();
//        String health_url = "D:/Picture/health/";
//        String travel_url = "D:/Picture/travel/";
//        String report_url = "D:/Picture/report/";
        String health_url = "/root/Picture/health/";
        String travel_url = "/root/Picture/travel/";
        String report_url = "/root/Picture/report/";
        // 生成一个随机的名称，避免文件名重复
        UUID uuid = UUID.randomUUID();
        // 获取原文件名称
        String originalHealthFileName = healthPicture.getOriginalFilename();
        String originalTravelFileName = travelPicture.getOriginalFilename();
        String originalReportFileName = reportPicture.getOriginalFilename();
        // 获取原文件的后缀
        String healthFileSuffix = originalHealthFileName.substring(originalHealthFileName.lastIndexOf('.'));
        String travelFileSuffix = originalTravelFileName.substring(originalTravelFileName.lastIndexOf('.'));
        String reportFileSuffix = originalReportFileName.substring(originalReportFileName.lastIndexOf('.'));
        // 保存文件
//        Object filePath;
        File healthFile = new File(health_url + uuid + healthFileSuffix);
        try {
            healthPicture.transferTo(healthFile);
        } catch (IOException e) {
            e.printStackTrace();
            return R.error();
        }
        File travelFile = new File(travel_url + uuid + travelFileSuffix);
        try {
            travelPicture.transferTo(travelFile);
        } catch (IOException e) {
            e.printStackTrace();
            return R.error();
        }
        File reportFile = new File(report_url + uuid + reportFileSuffix);
        try {
            reportPicture.transferTo(reportFile);
        } catch (IOException e) {
            e.printStackTrace();
            return R.error();
        }

//        String url = health_url+uuid+healthFileSuffix;
        Record record = new Record();
        record.setHealthCode(health_url+uuid+healthFileSuffix);
        record.setTravelCode(travel_url+uuid+travelFileSuffix);
        record.setReport(travel_url+uuid+travelFileSuffix);
        record.setPhone(phone);
        wechatDao.insert(record);
        return r;
    }
}
