package com.feng.service.impl;

import com.feng.dao.my.MyDataMapper;
import com.feng.dao.user.MyUserMapper;
import com.feng.service.MyDataService;
import com.sojson.core.mybatis.BaseMybatisDao;
import com.sojson.core.mybatis.page.Pagination;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by songfeng on 2017/4/11.
 */
@Service
public class MyDataServiceImpl extends BaseMybatisDao<MyDataMapper> implements MyDataService{

    @Autowired
    private MyDataMapper myDataMapper;

    @Autowired
    private MyUserMapper myUserMapper;

    protected final static Logger logger = Logger.getLogger(MyDataServiceImpl.class);

    @Override
    public Pagination<Map<String, Object>> findPage(Map<String,Object> params, Integer pageNo, Integer pageSize) {
        return super.findPage(params, pageNo, pageSize);
    }

    @Override
    public List<Map<String, Object>> findPageByPageHelper(Map<String, Object> params) {
        return myDataMapper.findAll(params);
    }

    @Override
    public Map<String, Object> selectByPk(int id) {
        return myUserMapper.selectByPk(id);
    }

    @Override
    public int addUser(List<Map<String, Object>> list) {
        list.stream().forEach(item -> {
            myUserMapper.insertOne(item);
        });
        return 1;
    }
}
