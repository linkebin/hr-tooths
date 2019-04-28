package com.yidusoft.project.system.service.impl;

import com.yidusoft.project.system.service.IconService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zcb on 2018/5/17.
 */
@Service
public class IconServiceImpl implements IconService {

    /**
     *获取 Icon的所有图片路劲
     * @return
     */
    @Override
    public List<Map<String, Object>> getIcons() {
        String realPath = System.getProperty("user.dir");
        File file=new File(realPath+"\\upload\\icon");
        String fileName[];
        fileName=file.list();
        List<Map<String, Object>> list=new ArrayList<>();
        for(int i=0;i<fileName.length;i++)
        {
            Map<String, Object> map=new HashMap<>();
            map.put("url","/upload/icon/"+fileName[i]);
            list.add(map);
        }
        return list;
    }
}
