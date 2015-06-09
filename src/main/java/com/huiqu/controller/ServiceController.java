package com.huiqu.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.huiqu.framework.BeanContainer;
import com.huiqu.framework.IService;
import com.huiqu.jdbc.User;
import com.huiqu.secrect.Sign;

@Controller
public class ServiceController {

	private static final Logger logger = LoggerFactory.getLogger(ServiceController.class);
	
	@RequestMapping(value = "/service")
	public @ResponseBody
	Map<String, Object> service(HttpServletRequest request)  {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			BeanContainer.I().setContext(RequestContextUtils.getWebApplicationContext(request));
			Map<String, String> req = extractRequest(request.getParameterMap());		
			if (Sign.checkSign(req)) {
				String method = req.get("m");
				logger.info("method is " + method);
				if (method != null) {
					IService service = BeanContainer.I().getBean(method);
					if (service != null) {
						result = service.exec(req);
					} else {
						setResult(result, 2, "你请求的方法不支持.");
					}
				} else {
					setResult(result, 3, "请求参数不完整.");
				}

			} else {
				setResult(result, 1, "请求签名验证失败.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			setResult(result, -1, "非法的请求.");
		}
		return result;
	}

	private Map<String, String> extractRequest(Map<String, String[]> req) {
		logger.info("extract request");
		Map<String, String> result = new HashMap<String, String>();
		for (String key : req.keySet()) {
			result.put(key, req.get(key)[0]);
			logger.info("req " + key + " = " + req.get(key)[0]);
		}
		for(Object k:req.keySet().toArray()){
			logger.info(k.toString());
		}
		return result;
	}

	private void setResult(Map<String, Object> result, int ret, String msg) {
		result.put("ret", ret);
		result.put("msg", msg);
	}
}
