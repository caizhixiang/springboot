package com.caizhixiang.shiro;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.caizhixiang.biz.UserBiz;
import com.caizhixiang.model.User;

import lombok.extern.slf4j.Slf4j;

@Component("myShiroRealm")
@Slf4j
public class MyShiroRealm extends AuthorizingRealm{
	@Autowired
	@Qualifier(value="userBiz")
	private UserBiz userBiz;
	
	/* (non-Javadoc)
	 * 授权
	 * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		log.info(">>>>>>授权中");
		Object primaryPrincipal = principals.getPrimaryPrincipal();
		if (primaryPrincipal==null) {
			return null;
		}
		User user=(User)primaryPrincipal;
		String name=user.getName();
		if(StringUtils.isEmpty(name))
			return null;
		SimpleAuthorizationInfo info =new SimpleAuthorizationInfo();
		info.setStringPermissions(userBiz.findFunctionsByUserName(name));
		return info;
	}

	/* (non-Javadoc)
	 * 认证
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		log.info("-----认证是否登录");
		UsernamePasswordToken token =(UsernamePasswordToken)authenticationToken;
		String username = token.getUsername();
		char[] password = token.getPassword();
		if (password==null) {
			return null;
		}
		User user=new User();
		user.setName(username);
		user.setPassword(String.valueOf(password));
		User condition=userBiz.findCondition(user);
		if (condition==null) {
			return null;
		}
		//将用户的信息存进session
		Subject subject=SecurityUtils.getSubject();
		subject.getSession().setAttribute(username, condition);
		AuthenticationInfo info=new SimpleAuthenticationInfo(condition,condition.getPassword(),getName());
		return info;
	}


}
