package com.zza.antiepidemic.modules.staffManage.controller;


import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.zza.antiepidemic.common.utils.ExcelUtils;
import com.zza.antiepidemic.common.utils.R;
import com.zza.antiepidemic.modules.staffManage.entity.Staff;
import com.zza.antiepidemic.modules.staffManage.service.impl.StaffServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.zza.antiepidemic.modules.staffManage.dao.StaffDao;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Api(tags = "excel")
@ApiSupport(order = 20)
@RestController
@RequestMapping("/Excel")
public class ExcelController {

    @Autowired
    private StaffServiceImpl staffService;

    @Autowired
    private StaffDao staffDao;

    /**
     * @param response
     * @功能描述 下载文件:
     */
    @ApiOperation("下载模板")
    @GetMapping("/download")
    public String download(HttpServletResponse response) {

        try {
            String path = "/root/antipidemic/Excel/template/staffTemplate.xlsx";
//            String path = "D:/学习笔记/研究生/antiepidemic/src/excelTemplate/staffTemplate.xlsx";
            // path是指想要下载的文件的路径
            File file = new File(path);
            System.out.println(file.getPath());
            // 获取文件名
            String filename = file.getName();
            // 获取文件后缀名
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
            System.out.println("文件后缀名：" + ext);

            // 将文件写入输入流
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStream fis = new BufferedInputStream(fileInputStream);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();

            // 清空response
            response.reset();
            // 设置response的Header
            response.setCharacterEncoding("UTF-8");
            //Content-Disposition的作用：告知浏览器以何种方式显示响应返回的文件，用浏览器打开还是以附件的形式下载到本地保存
            //attachment表示以附件方式下载 inline表示在线打开 "Content-Disposition: inline; filename=文件名.mp3"
            // filename表示文件的默认名称，因为网络传输只支持URL编码的相关支付，因此需要将文件名URL编码后进行传输,前端收到后需要反编码才能获取到真正的名称
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
            // 告知浏览器文件的大小
            response.addHeader("Content-Length", "" + file.length());
            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            outputStream.write(buffer);
            outputStream.flush();
            return "sucess";
        } catch (IOException ex) {
            ex.printStackTrace();
            return "fail";
        }
    }

    @ApiOperation("导入")
    @PostMapping("/import")
    @ApiOperationSupport(order = 20)
    public R uploadFile( @RequestParam MultipartFile file,
                         @RequestParam Long cid
                ) throws Exception {
        R r = R.ok();
        System.out.println(cid);
        try {
            // 上传文件路径
            String filePath = "/root/antipidemic/Excel";
//            String filePath = "D:/Zhaozian/datatest/";
            // 为了防止文件名冲突，获取当前时间+文件原名生成新的文件名
            String fileName = System.currentTimeMillis() + file.getOriginalFilename();

            // 上传文件
            File f = new File(filePath + fileName);
            file.transferTo(f);
            fileName = filePath + fileName;

            //输入流，获取刚刚上传文件转成输入流
            FileInputStream fileInputStream = new FileInputStream(new File(fileName));
            //定义一个list变量，模拟excel结构
            List<List<Object>> list = ExcelUtils.getListByExcel(fileInputStream, fileName);
            //定义firstRows变量，用来获取第一行，就是标题，每列名字
            List<Object> firstRows = null;
            //定义staffList变量，用来存储文件内容(员工信息)
            List<Staff> staffList = new ArrayList<>();
            //如果 list 不为空，大小大于0则获取第一行存到firstRows 里面
            if (list != null && list.size() > 0) {
                firstRows = list.get(0);
                System.out.println(firstRows);
            } else {
                //否则返回 failure
                r.put("result", "fail");
                return r;
            }
            //对list进行遍历，因为第一行是标题，不用存到数据库，所以从第二行开始遍历
            for (int i = 1; i < list.size(); i++) {
                //获取第i行数据
                List<Object> rows = list.get(i);
                //定义staff遍历，存储第i行数据
                Staff staff = new Staff();
                //对第i行数据进行遍历，
                for (int j = 0; j < rows.size(); j++) {
                    //获取第i行第j列数据，存到cellVal 变量里面
                    String cellVal = (String) rows.get(j);
                    //调用setFileValueByFieldName函数，把数据存到student对应的属性里面
                    ExcelUtils.setFileValueByFieldName(staff, firstRows.get(j).toString().trim(), cellVal);

                }
                staff.setCid("1");
                //把student变量加到studentList
                staffList.add(staff);
            }
            //调用批量插入方法，把数据存到数据库
            this.staffDao.batchInsert(staffList);
            return r;
        } catch (Exception e) {
            r.put("result", "fail");
            return r;
        }
    }
}
