package com.program.interceptor;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


public class Interceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		String token = (String) session.getAttribute("token");
		//简单判断，有token，放行           没有token，给前端返回信息，不放行
		if (token == null || token.equals("")) {
			
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json; charset=utf-8");
			
			//这里有点问题  postman出现的格式不正确
			//{info=请登录，code=400}
			Map<String,Object> map = new HashMap<String, Object>();
			PrintWriter writer = response.getWriter();
			
			map.put("info", "请登录");
			map.put("code", 400);
			
			writer.append(map.toString());
//			看到也有用write方法的
//			writer.write(map.toString());
//			writer.flush();
//			writer.close();		
			return false;
		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

}
