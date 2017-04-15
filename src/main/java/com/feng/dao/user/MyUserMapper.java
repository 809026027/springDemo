package com.feng.dao.user;

import java.util.Map;

/**
 * Created by songfeng on 2017/4/11.
 */
public interface MyUserMapper {
    int insertOne(Map<String,Object> params);

    Map<String,Object> selectByPk(int id);
}
