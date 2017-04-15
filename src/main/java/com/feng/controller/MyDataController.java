package com.feng.controller;

import com.feng.service.MyDataService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.sojson.common.controller.BaseController;
import com.sojson.core.mybatis.page.Pagination;
import freemarker.ext.beans.HashAdapter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by songfeng on 2017/4/11.
 */
@Controller
@RequestMapping("/mydata")
public class MyDataController extends BaseController {

    @Autowired
    private MyDataService myDataService;

    protected final static Logger logger = Logger.getLogger(MyDataController.class);

    @RequestMapping("/list.do")
    @ResponseBody
    public Pagination<Map<String,Object>> getList(HttpServletRequest request)throws Exception
    {
        logger.info("请求我的数据");
        Map<String,Object> params = prepareParams(request);
        return myDataService.findPage(params, pageNo, pageSize);
    }

    @RequestMapping("/list1.do")
    @ResponseBody
    public Pagination<Map<String,Object>> getListByPageHelper(HttpServletRequest request)throws Exception
    {
        logger.info("请求我的数据");
        Map<String,Object> params = prepareParams(request);
        PageHelper.startPage(pageNo, pageSize);
        List<Map<String,Object>> list = myDataService.findPageByPageHelper(params);
        PageInfo pageInfo = new PageInfo(list);
        Pagination<Map<String,Object>> page = new Pagination<>(pageInfo.getPageNum(),pageInfo.getPageSize(),(int)pageInfo.getTotal());
        page.setList(list);
        return page;
    }

    /**
     * list跳转
     * @return ModelAndView
     */
    @RequestMapping(value="/toListPage.do")
    public ModelAndView tolistPage(ModelMap map){
        Pagination<Map<String,Object>> pagination = myDataService.findPage(new HashMap<String,Object>(),1,10);
        map.put("page",pagination);
        return new ModelAndView("my/list");
    }

    /**
     * user跳转
     * @return ModelAndView
     */
    @RequestMapping(value="/toUserPage.do")
    public ModelAndView toUserPage(){
        return new ModelAndView("my/user");
    }

    /**
     * 新增用户
     * @return Map<String,Object>
     */
    @RequestMapping(value="/addUser.do")
    @ResponseBody
    public Map<String,Object> addUser(ModelMap map){
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        for(int i = 1; i <= 5; i++)
        {
            Map<String,Object> params = Maps.newHashMap();
            params.put("id",i);
            params.put("name","song"+i);
            params.put("age",i);
            list.add(params);
        }
        Map<String,Object> result = Maps.newHashMap();
        result.put("code",myDataService.addUser(list));
        return result;
    }

    /**
     * 查询用户
     * @return Map<String,Object>
     */
    @RequestMapping(value="/queryUser.do")
    @ResponseBody
    public Map<String,Object> queryUser(int id){
        return myDataService.selectByPk(id);
    }
}
