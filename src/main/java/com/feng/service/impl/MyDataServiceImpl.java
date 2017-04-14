package com.feng.service.impl;

import com.feng.dao.MyDataMapper;
import com.feng.service.MyDataService;
import com.sojson.core.mybatis.BaseMybatisDao;
import com.sojson.core.mybatis.page.Pagination;
import freemarker.ext.beans.HashAdapter;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by songfeng on 2017/4/11.
 */
@Service
public class MyDataServiceImpl extends BaseMybatisDao<MyDataMapper> implements MyDataService{

    @Autowired
    private MyDataMapper myDataMapper;
    @Override
    public Pagination<Map<String, Object>> findPage(Map<String,Object> params, Integer pageNo, Integer pageSize) {
        return super.findPage(params, pageNo, pageSize);
    }

    @Override
    public List<Map<String, Object>> findPageByPageHelper(Map<String, Object> params) {
        return myDataMapper.findAll(params);
    }
}
