package com.feng.service;

import com.sojson.core.mybatis.page.Pagination;

import java.util.List;
import java.util.Map;

/**
 * Created by songfeng on 2017/4/11.
 */
public interface MyDataService {
    Pagination<Map<String,Object>> findPage(Map<String,Object> params, Integer pageNo, Integer pageSize);

    List<Map<String,Object>> findPageByPageHelper(Map<String, Object> params);
}
