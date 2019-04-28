package com.yidusoft.project.system.controller;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.domain.NoticeAdministration;
import com.yidusoft.project.system.service.NoticeAdministrationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2018/11/13.
*/
@RestController
@RequestMapping("/notice/administration")
public class NoticeAdministrationController {
    @Resource
    private NoticeAdministrationService noticeAdministrationService;

    @PostMapping("/add")
    public Result add(NoticeAdministration noticeAdministration) {
        noticeAdministrationService.save(noticeAdministration);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam String id) {
        noticeAdministrationService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(NoticeAdministration noticeAdministration) {
        noticeAdministrationService.update(noticeAdministration);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam String id) {
        NoticeAdministration noticeAdministration = noticeAdministrationService.findById(id);
        return ResultGenerator.genSuccessResult(noticeAdministration);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<NoticeAdministration> list = noticeAdministrationService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
