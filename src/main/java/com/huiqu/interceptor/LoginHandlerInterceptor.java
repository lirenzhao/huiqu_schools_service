package com.huiqu.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class LoginHandlerInterceptor extends HandlerInterceptorAdapter {
	private List<String> excludedUrls; // 不需要登录的地址列表

	public void setExcludedUrls(List<String> excludedUrls) {
		this.excludedUrls = excludedUrls;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String requestUri = request.getRequestURI();
		System.out.println("LoginHandlerInterceptor pre handler execute. url:" + requestUri);
		
		for (String url : excludedUrls) {
			if (requestUri.endsWith(url)) {
				System.out.println("url:" + url + " is in exclude list.");
				return true;
			}
		}

		// intercept
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			System.out.println("redirect to login url,redirect url is " + requestUri);
			session.setAttribute("redirect_url", requestUri);
			throw new AuthorizationException();
		} else {
			System.out.println("user already login.");
			return true;
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		System.out.println("LoginHandlerInterceptor after handler execute.");
		super.afterCompletion(request, response, handler, ex);
	}
}
