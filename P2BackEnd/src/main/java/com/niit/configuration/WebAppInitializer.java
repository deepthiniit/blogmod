package com.niit.configuration;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
//similar to web.xml
//<servlet></servlet>
@EnableWebMvc
public class WebAppInitializer extends  AbstractAnnotationConfigDispatcherServletInitializer{

public WebAppInitializer(){
	   System.out.println("WebAppInitializer is loaded and instantiated");
}
@Override
protected Class<?>[] getRootConfigClasses(){ 
	return new Class[]{DBConfiguration.class};
}
@Override
protected Class<?>[] getServletConfigClasses(){
	return new Class[]{WebAppConfig.class};
}
@Override
protected String[] getServletMappings() {
	return new String[]{"/"};//<url-pattern>/</url-pattern> Any request forwarded to dispatcher-servlet
}
	
}
