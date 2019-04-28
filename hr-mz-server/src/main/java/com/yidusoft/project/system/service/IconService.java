package com.yidusoft.project.system.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by zcb on 2018/5/17.
 */
public interface IconService {

    /**
     *获取 Icon的所有图片路劲
     * @return
     */
    List<Map<String,Object>> getIcons();

}
