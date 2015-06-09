package com.huiqu.framework;

import org.springframework.web.context.WebApplicationContext;

public class BeanContainer {
	private BeanContainer(){}
	private static BeanContainer container = null;
//	private static String basePackage = "com.huiqu";
	public WebApplicationContext context = null;
	public static BeanContainer I(){
		if(container == null){
			container = new BeanContainer();
		}
		return container;
	}
	public IService getBean(String method) {
		try{
		Object s = context.getBean(method);
			if(s != null)
				if(s instanceof IService)
					return (IService)s;
				else
					return null;
			else
				return null;
		}catch(Exception e){
			return null;
		}
	}
	public void setContext(WebApplicationContext ctx) {
		container.context = ctx;
	}
	
}
