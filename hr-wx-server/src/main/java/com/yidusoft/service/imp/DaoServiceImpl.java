package com.yidusoft.service.imp;

import com.alibaba.fastjson.JSONObject;
import com.yidusoft.dao.DaoMapper;
import com.yidusoft.service.DaoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Name:陈文杰
 * Date: 2018/8/29
 */
@Service
@Transactional
public class DaoServiceImpl implements DaoService {
    @Resource
    private DaoMapper daoMapper;

    @Override
    public List<Map<String, Object>> getDivList(String sql) {
        return daoMapper.getDivList(sql);
    }

    @Override
    public Map<String, Object> getDivMap(String sql) {
        return daoMapper.getDivMap(sql + " limit 1");
    }

    @Override
    public <T> T getObject(String sql, Class<T> clazz) {
        sql = sql + " limit 1";
        if (daoMapper.getDivMap(sql) != null) {
            return JSONObject.parseObject(JSONObject.toJSONString(daoMapper.getDivMap(sql)), clazz);
        } else {
            return null;
        }
    }

    @Override
    public <T> T getObject(Map map, Class<T> clazz) {
        if (map != null) {
            return JSONObject.parseObject(JSONObject.toJSONString(map), clazz);
        } else {
            return null;
        }
    }

    @Override
    public <T> List<T> getbyList(String sql, Class<T> clazz) {
        List<Map<String, Object>> list = daoMapper.getDivList(sql);
        if (list.size() == 0) {
            return null;
        } else {
            List<T> lists = new ArrayList<>();
            for (Map map : list) {
                lists.add(JSONObject.parseObject(JSONObject.toJSONString(map), clazz));
            }
            return lists;
        }

    }
}
