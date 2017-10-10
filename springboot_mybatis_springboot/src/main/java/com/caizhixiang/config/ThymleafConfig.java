package com.caizhixiang.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@Configuration
public class ThymleafConfig {
	
	@Value("${spring.thymeleaf.prefix}")
	private String prefix;
	
	@Value("${spring.thymeleaf.suffix}")
	private String suffix;
	
	@Value("{spring.thymeleaf.encoding}")
	private String characterEncoding;
	
	@Value("{spring.thymeleaf.mode}")
	private String templateMode;
	
	@Value("{spring.thymeleaf.false}")
	private String cacheable;
	
	@Value("${spring.thymeleaf.check-template-location}")
	private String checkTemplateLocation;
	
	@Value("${spring.thymeleaf.content-type}")
	private String contentType;

	@Bean
	public ServletContextTemplateResolver servletContextTemplateResolver(){
		ServletContextTemplateResolver s=new ServletContextTemplateResolver();
		s.setCacheable(Boolean.getBoolean(cacheable));
		s.setCharacterEncoding(characterEncoding);
		s.setPrefix(prefix);
		s.setSuffix(suffix);
		s.setTemplateMode(templateMode);
		return s;
		
		
	}
}
