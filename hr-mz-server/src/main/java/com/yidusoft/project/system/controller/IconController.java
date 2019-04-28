package com.yidusoft.project.system.controller;

import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.service.IconService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by zcb on 2018/5/17.
 */
@RestController
@RequestMapping("/icon/")
public class IconController {
    @Resource
    private IconService iconService;

    /***
     * 获取 Icon的所有图片路劲
     * @return
     */
    @GetMapping("/getIcons")
    public Result getIcons() {
      List<Map<String,Object>> list=   iconService.getIcons();
        return ResultGenerator.genSuccessResult(list);
    }
}
