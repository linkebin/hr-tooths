package com.yidusoft.project.system.controller;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.domain.SecMenuMember;
import com.yidusoft.project.system.service.SecMenuMemberService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2018/04/20.
*/
@RestController
@RequestMapping("/sec/menu/member")
public class SecMenuMemberController {
    @Resource
    private SecMenuMemberService secMenuMemberService;


    /***
     * 用户添加菜单
     * @param userId
     * @param menuIds  多个菜单id 用逗号隔开
     */
    @PostMapping("/addMenuMember")
    public Result addMenuMember(String userId, String menuIds){
        try {
            secMenuMemberService.addMenuMember(userId,menuIds);
        }catch (Exception ex){
            ex.printStackTrace();
            return ResultGenerator.genFailResult(ex.getMessage());
        }

        return ResultGenerator.genSuccessResult();
    }

    /***
     * 用户删除菜单
     * @param userId
     * @param menuIds  多个菜单id 用逗号隔开
     */
    @PostMapping("/delMemberMapper")
    public Result delMemberMapper(String userId, String menuIds){
        try {
            secMenuMemberService.delMemberMapper(userId,menuIds);
        }catch (Exception ex){
            ex.printStackTrace();
            return ResultGenerator.genFailResult(ex.getMessage());
        }

        return ResultGenerator.genSuccessResult();
    }


    @PostMapping("/add")
    public Result add(SecMenuMember secMenuMember) {
        secMenuMemberService.save(secMenuMember);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam String id) {
        secMenuMemberService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SecMenuMember secMenuMember) {
        secMenuMemberService.update(secMenuMember);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam String id) {
        SecMenuMember secMenuMember = secMenuMemberService.findById(id);
        return ResultGenerator.genSuccessResult(secMenuMember);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SecMenuMember> list = secMenuMemberService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
