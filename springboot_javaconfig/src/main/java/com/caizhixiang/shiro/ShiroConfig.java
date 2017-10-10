package com.caizhixiang.shiro;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.filter.DelegatingFilterProxy;
 

@Configuration
public class ShiroConfig {
	/**
	 * 注册shiro的filter FilterRegistrationBean
	 * @return
	 */
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
//		  FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//		   DelegatingFilterProxy httpBasicFilter = new DelegatingFilterProxy();
//		   registrationBean.setFilter(httpBasicFilter);
//		   Map<String,String> m = new HashMap<String,String>();
//		   m.put("targetBeanName","shiroFilter");
//		   m.put("targetFilterLifecycle","true");
//		   registrationBean.setInitParameters(m);
//		   List<String> urlPatterns = new ArrayList<String>();
//		   urlPatterns.add("/*");
//		   registrationBean.setUrlPatterns(urlPatterns);
//		   return registrationBean;
		FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
		
		filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
		filterRegistration.addUrlPatterns("/*");
		
		filterRegistration.setEnabled(true);
		filterRegistration.setDispatcherTypes(DispatcherType.REQUEST);
		return filterRegistration;
	}
	
	/**
	 * shiro的核心是通过过滤器来实现的
	 * @param securityManager 安全管理器
	 * @return
	 */
	@Bean(name="shiroFilter")
	public ShiroFilterFactoryBean shiroFilter(){
		ShiroFilterFactoryBean factoryBean =new ShiroFilterFactoryBean();
		factoryBean.setSecurityManager(securityManager());
		
		Map<String, Filter> filters =new HashMap<>();
		filters.put("perms", URLPermissionsFilter());
		filters.put("anon", new AnonymousFilter());
		factoryBean.setFilters(filters);
		// 要求登录时的链接
		factoryBean.setLoginUrl("/");
		// 未鉴权的时候跳转的链接
		factoryBean.setUnauthorizedUrl("/unauthor");

		//拦截器
		//配置不会被拦截的链接

		Map<String, String> chains = new HashMap();
		chains.put("/login", "anon");
//		chains.put("/user/toLogin", "anon");
		chains.put("/unauthor", "anon");
		chains.put("/logout", "logout");
		chains.put("/static/**", "anon");
		chains.put("/user/**", "perms");
		factoryBean.setFilterChainDefinitionMap(chains);
		return factoryBean;
	}
	@Bean
	public URLPermissionsFilter URLPermissionsFilter() {
		// TODO Auto-generated method stub
		return new URLPermissionsFilter();
	}

	@Bean
	@DependsOn(value="lifecycleBeanPostProcessor")
	public MyShiroRealm myShiroRealm(){
		MyShiroRealm realm=new MyShiroRealm();
//		realm.setCacheManager(cacheManager());
		return realm;
	}
	
	/**
	 * shiro的生命周期
	 * @return
	 */
	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}
	@Bean
	public SecurityManager securityManager(){
		DefaultWebSecurityManager manager=new DefaultWebSecurityManager();
		manager.setRealm(myShiroRealm ());
//		manager.setCacheManager(cacheManager());
		return manager;
	}
	
	@Bean
	public EhCacheManager cacheManager() {
		EhCacheManager cacheManager = new EhCacheManager();
		cacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
		return cacheManager;
	}
	
	
}
