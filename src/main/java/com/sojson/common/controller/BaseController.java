package com.sojson.common.controller;

import com.sojson.common.utils.StringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

public class BaseController {

	protected int pageNo =1;
	public static  int pageSize = 10;
	protected final static Logger logger = Logger.getLogger(BaseController.class);
	protected Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
	public static String URL404 =  "/404.html";

	protected final static String PARAM_PAGE_NO = "pageNo";

	protected final static String PARAM_PAGE_SIZE = "pageSize";
	
	/**
	 * 往Request里带值
	 * @param request
	 * @param key
	 * @param value
	 */
	protected static void setValue2Request(HttpServletRequest request,String key,Object value){
		request.setAttribute(key, value);
	}
	
	/**
	 * [获取session]
	 * @param request
	 * @return
	 */
	public static HttpSession getSession(HttpServletRequest request){
		return request.getSession();
	}

	public ModelAndView redirect(String redirectUrl,Map<String,Object>...parament){
		ModelAndView view = new ModelAndView(new RedirectView(redirectUrl));
		if(null != parament && parament.length > 0){
			view.addAllObjects(parament[0]);
		}
		return view;
	}
	public ModelAndView redirect404(){
		return new ModelAndView(new RedirectView(URL404));
	}

	protected Map<String, Object> prepareParams(HttpServletRequest request) throws Exception {
		return prepareParams(null, request);
	}

	protected Map<String, Object> prepareParams(Object obj, HttpServletRequest request) throws Exception {
		setPage(request);
        Map<String, Object> params = getParameterMap(request);
		if(obj != null) {
			// 回填值项
			BeanUtils.populate(obj, params);
        }
		return params;
	}

	protected void setPage(HttpServletRequest request)
	{
		if (request != null) {
			String pageNoStr = request.getParameter(PARAM_PAGE_NO);
			String pageSizeStr = request.getParameter(PARAM_PAGE_SIZE);
			if (StringUtils.isNotBlank(pageNoStr)) {
				pageNo = Integer.parseInt(pageNoStr);
			}
			if (StringUtils.isNotBlank(pageSizeStr)) {
				pageSize = Integer.parseInt(pageSizeStr);
			}
		}
	}

	/**
	 * 从request中获得参数Map，并返回可读的Map
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map getParameterMap(HttpServletRequest request) {
		// 参数Map
		Map properties = request.getParameterMap();
		// 返回值Map
		Map returnMap = new HashMap();
		Iterator entries = properties.entrySet().iterator();
		Map.Entry entry;
		String name = "";
		String value = "";
		while (entries.hasNext()) {
			entry = (Map.Entry) entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();
			if(null == valueObj){
				value = "";
			}else if(valueObj instanceof String[]){
				String[] values = (String[])valueObj;
				for(int i=0;i<values.length;i++){
					value = values[i] + ",";
				}
				value = value.substring(0, value.length()-1);
			}else{
				value = valueObj.toString();
			}
			returnMap.put(name, value);
		}
		return returnMap;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
