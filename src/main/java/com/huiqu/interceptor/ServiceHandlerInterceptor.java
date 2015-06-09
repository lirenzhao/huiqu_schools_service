package com.huiqu.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class ServiceHandlerInterceptor extends HandlerInterceptorAdapter {
	private List<String> excludedUrls; // 不需要登录的地址列表

	public void setExcludedUrls(List<String> excludedUrls) {
		this.excludedUrls = excludedUrls;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String requestUri = request.getRequestURI();
		System.out.println("ServiceHandlerInterceptor pre handler execute. url:" + requestUri);
		System.out.println("check service request, sign , timestamp");
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		System.out.println("ServiceHandlerInterceptor after handler execute.");
		super.afterCompletion(request, response, handler, ex);
	}
}
