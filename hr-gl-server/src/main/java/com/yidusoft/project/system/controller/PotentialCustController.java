package com.yidusoft.project.system.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.poi.ExcelData;
import com.yidusoft.poi.ExcelUtils;
import com.yidusoft.project.system.domain.PotentialCust;
import com.yidusoft.project.system.domain.SecUser;
import com.yidusoft.project.system.service.PotentialCustService;
import com.yidusoft.utils.Security;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
* Created by CodeGenerator on 2018/11/13.
*/
@RestController
@RequestMapping("/potential/cust")
public class PotentialCustController {
    @Resource
    private PotentialCustService potentialCustService;

    @PostMapping("/add")
    public Result add(String json) {
		SecUser secUser = Security.getUser();
		PotentialCust potentialCust= JSON.parseObject(json,PotentialCust.class);
		potentialCust.setPotentialId(UUID.randomUUID().toString());
		potentialCust.setClinic("122");
		potentialCustService.save(potentialCust);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam String ids) {
		String[] delIds = null;
		if(StringUtils.isNotBlank(ids)){
			delIds = ids.split(",");
		}
		if(delIds!=null&&delIds.length>0){
			for (int i = 0; i <delIds.length ; i++) {
				if(StringUtils.isNotBlank(delIds[i])){
					potentialCustService.deleteById(delIds[i]);
				}
			}
		}
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(String json) {
    	try{
			PotentialCust potentialCust= JSON.parseObject(json,PotentialCust.class);
			potentialCustService.update(potentialCust);
			return ResultGenerator.genSuccessResult();
		}catch (Exception e){
			return ResultGenerator.genFailResult("");
		}
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam String id) {
        PotentialCust potentialCust = potentialCustService.findById(id);
        return ResultGenerator.genSuccessResult(potentialCust);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<PotentialCust> list = potentialCustService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @GetMapping("/find")
    public Result findPotentialCust(String clinic,String source, String starTime, String endTime, String customer, int size, int page) {
    	try {
			List<Map<String,Object>> potentialCustList = potentialCustService.findPotentialCust(source,clinic,starTime,endTime,customer,size,(page - 1)*size);
			int count = potentialCustService.findPotentialCustCount(source,clinic,starTime,endTime,customer);
			return ResultGenerator.genSuccessResult(potentialCustList).setTotal(count);
		}catch (Exception e){
    		return ResultGenerator.genFailResult("");
		}
    }

	@GetMapping("/exportCust")
	public void excelCust(HttpServletResponse response) throws Exception {
		ExcelData data = new ExcelData();
		data.setName("潜在客户列表");
		List<String> titles = new ArrayList<>();
		titles.add("姓名");
		titles.add("联系方式");
		titles.add("来源渠道");
		titles.add("消费金额");
		titles.add("录入人");
		titles.add("录入时间");
		titles.add("备注");
		data.setTitles(titles);
		List<Map<String,Object>> list = potentialCustService.findPotentialCustAll("","","","","");
		List<List<Object>> rows = new ArrayList<>();
		list.forEach(t->{
			List<Object> row = new ArrayList<>();
			row.add(t.get("potential_name"));
			row.add(t.get("potential_tel"));
			row.add(t.get("source"));
			row.add(t.get("money"));
			row.add(t.get("creator"));
			row.add(t.get("create_time"));
			row.add(t.get("remark"));
			rows.add(row);
		});

		data.setRows(rows);

		String fileName="潜在客户列表.xls";
		ExcelUtils.exportExcel(response,fileName,data);
	}
}
