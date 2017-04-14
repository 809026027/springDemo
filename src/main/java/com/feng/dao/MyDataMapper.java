package com.feng.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by songfeng on 2017/4/11.
 */
public interface MyDataMapper {
    List<Map<String,Object>> findAll(Map<String, Object> params);
}
