package com.i4unetworks.weys.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.i4unetworks.weys.common.UserVO;
import com.i4unetworks.weys.common.Utils;

public class Interceptor extends HandlerInterceptorAdapter {

	protected static Logger logger = LoggerFactory.getLogger(Interceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		// /api/** 경로를 통해 들어오는 모든 action 에 대해서 사용자 로그인 검사. 로그인 안했을 경우 로그인 페이지로
		// 강제이동
		UserVO userVO = (UserVO) request.getSession().getAttribute("login");

		if (userVO != null) {
			String uri = request.getRequestURI();
			
			if(uri.contains("api/cp")){
				return true;
			}
			
			String due = userVO.getPwdDue();
			long diff = Utils.diffTwoDate(due, Utils.getTodayDate());
			if (diff <= 0) {
				String context = request.getContextPath();
				response.sendRedirect(context + "/api/cp?tover=true");
				return false;
			}

			return true;
		}
		String context = request.getContextPath();
		response.sendRedirect(context + "/");
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}

}
