package com.yidusoft.project.system.controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.domain.SysAccessory;
import com.yidusoft.project.system.service.DaoService;
import com.yidusoft.project.system.service.SysAccessoryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
* Created by CodeGenerator on 2018/11/13.
*/
@RestController
@RequestMapping("/sys/accessory")
public class SysAccessoryController {
    @Resource
    private SysAccessoryService sysAccessoryService;
    @Resource
    private DaoService daoService;

    @PostMapping("/file")
    public String file(MultipartFile file) {
        if (null != file) {
            //我要获取当前的日期
            Date date = new Date();
            //设置要获取到什么样的时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            SimpleDateFormat sdfs = new SimpleDateFormat("yyyyMMdd");
            //获取String类型的时间
            String createdate = sdf.format(date);
            String myFileName = file.getOriginalFilename();// 文件原名称
            String fileName = createdate+
                    Integer.toHexString(new Random().nextInt()) +"."+ myFileName.
                    substring(myFileName.lastIndexOf(".") + 1);
            String filePath=System.getProperty("user.dir")+"/upload/image/"+sdfs.format(date); //目录路径
            String fpt=System.getProperty("user.dir")+"/upload/image/"+sdfs.format(date)+"/"; //文件路径

            File fileDir=new File(filePath);
            if (!fileDir.exists()) { //如果不存在 则创建
                fileDir.mkdirs();
            }
            String path=fpt+fileName;
            File localFile = new File(path);
            try {
                file.transferTo(localFile);
                JSONObject json=new JSONObject();
                json.put("myFileName",myFileName);
                json.put("createTime",date.getTime());
                json.put("path","/upload/image/"+sdfs.format(date)+"/"+fileName);
                return "{" +
                        "  \"code\": 0" +
                        "  ,\"msg\": \"\"" +
                        "  ,\"data\": " +json+
                        "}   ";
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }

    @PostMapping("/add")
    public Result add(String str,String ids) {
        SysAccessory sysAccessory= JSON.parseObject(str,SysAccessory.class);
        sysAccessory.setId(UUID.randomUUID().toString());
        sysAccessory.setReId(ids);
        sysAccessory.setYw(",,,");
        sysAccessory.setZl("");
        sysAccessory.setRemark("");
        sysAccessoryService.save(sysAccessory);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(String ids) {
        daoService.getDivList("delete from sys_accessory where re_id='"+ids+"'");
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/deleteOne")
    public Result deleteOne(String id) {
        daoService.getDivList("delete from sys_accessory where id_='"+id+"'");
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SysAccessory sysAccessory) {
        sysAccessoryService.update(sysAccessory);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam String id) {
        List<SysAccessory> list = daoService.getbyList("select * from sys_accessory s where s.re_id='"+id+"' order by create_time desc",SysAccessory.class);
        return ResultGenerator.genSuccessResult(list);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SysAccessory> list = sysAccessoryService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }


    @PostMapping("/imgUpdate")
    public Result imgUpdate(String obj) {
        SysAccessory sysAccessory = JSON.parseObject(obj, SysAccessory.class);
        sysAccessoryService.update(sysAccessory);
        return ResultGenerator.genSuccessResult();
    }
}
