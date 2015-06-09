package com.huiqu.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerExceptionResolverComposite;

@Component
public class HuiquExceptionResolve extends HandlerExceptionResolverComposite {
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		System.out.println("HuiquExceptionResolve execute. " + ex);
		Map model = new HashMap();
		if (ex instanceof MaxUploadSizeExceededException) {
			model.put("ex", ex.getClass().getSimpleName());
			model.put("error", ex.getMessage());
			return new ModelAndView("error/maxUploadExceeded", model);
		}
		else if (ex instanceof AuthorizationException) { //配置文件处理
			return null;
		} else {
			model.put("ex", ex.getClass().getSimpleName());
			model.put("error", ex.getMessage());
			return new ModelAndView("error/error", model);
		}
	}
}
