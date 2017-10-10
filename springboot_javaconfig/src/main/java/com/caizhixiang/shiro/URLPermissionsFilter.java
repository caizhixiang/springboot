package com.caizhixiang.shiro;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.caizhixiang.biz.UserBiz;
import com.caizhixiang.model.User;


/**
 * 权限拦截器
 *
 */
@Component("urlPermissionsFilter")
public class URLPermissionsFilter extends PermissionsAuthorizationFilter {

	@Autowired
	private UserBiz userBiz;
	
	private static final String INDEX_URL = "/user/index";

	@Override
	public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws IOException {
		String curUrl = getRequestUrl(request);
		if (StringUtils.endsWithAny(curUrl, ".js", ".json", ".css")
				|| StringUtils.endsWithAny(curUrl, ".jpg", ".png", ".gif", ".jpeg")
				|| StringUtils.equals(curUrl, "/unauthor")) {
			return true;
		}
		if (!isAuthenticated())
			return false;
		Set<String> urls = getPermissionList();
		String contextPath = request.getServletContext().getContextPath();
		// 首页不拦截
		String indexUrl = contextPath + INDEX_URL;
		if (curUrl.startsWith(indexUrl))
			return true;
		return urls.contains(curUrl.replace(contextPath, ""));
	}

	private boolean isAuthenticated() {
		Subject subject = SecurityUtils.getSubject();
		return subject.isAuthenticated();
	}

	/**
	 * 获取当前URL+Parameter
	 * 
	 * @author lance
	 * @since 2014年12月18日 下午3:09:26
	 * @param request
	 *            拦截请求request
	 * @return 返回完整URL
	 */
	private String getRequestUrl(ServletRequest request) {
		HttpServletRequest req = (HttpServletRequest) request;
		return req.getRequestURI();
	}

	@SuppressWarnings("unchecked")
	public Set<String> getPermissionList() {
		Set<String> pList = null;
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			User user = null;
			Object obj = subject.getPrincipal();
			if (obj instanceof User)
				user = (User) obj;

			String loginName = user.getName();
			Object objList = subject.getSession().getAttribute(loginName);
			try {
				if (null != objList) {
					pList = (HashSet<String>) objList;
				}
			} catch (Exception e) {
			}
			if (pList == null) {
				pList = userBiz.findFunctionsByUserName(loginName);
			}

		}
		return pList;
	}

}