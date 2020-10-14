package com.story.storyadmin.config.shiro;


import com.story.storyadmin.config.shiro.security.JwtToken;
import com.story.storyadmin.config.shiro.security.JwtUtil;
import com.story.storyadmin.constant.SecurityConsts;
import com.story.storyadmin.domain.entity.sysmgr.Role;
import com.story.storyadmin.domain.entity.sysmgr.User;
import com.story.storyadmin.service.sysmgr.AuthorityService;
import com.story.storyadmin.service.sysmgr.RoleService;
import com.story.storyadmin.service.sysmgr.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 自定义Realm鉴权 身份验证器
 */
@Service
public class ShiroRealm extends AuthorizingRealm {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private AuthorityService authorityService;

    /**
     * 支持自定义token
     * 用于验证realm是否支持AuthenticationToken或它的子类验证，在认证器（Authorizer）中调用该方法。
     * JwtToken实现AuthenticationToken接口
     * @param token
     * @return
     */
	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof JwtToken;
	}

	/**
	 * 身份认证
	 * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     * 获取认证信息，里面包括了校验密码，用户是否锁定，即如果正常返回了AuthenticationInfo，那么用户就是认证通过的
	 * @param auth
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth)
	        throws AuthenticationException {
	    //从JwtToken中获取token
		String token = (String)auth.getPrincipal();
		//获取token中的account用户信息
		String account  = JwtUtil.getClaim(token, SecurityConsts.ACCOUNT);

		if (account == null) {
			throw new AuthenticationException("token invalid");
		}
        //校验token是否正确
		if (JwtUtil.verify(token)) {
			// TODO 可以补充逻辑：校验成功开始踢人(清除缓存和Session这里是token)
			//  ShiroUtils.deleteCache(username,true);
			return new SimpleAuthenticationInfo(token, token, "shiroRealm");
		}
		throw new AuthenticationException("Token expired or incorrect.");
	}

	/**
	 * 授权权限
	 * 用户进行权限验证时候Shiro会去缓存中找,如果查不到数据,会执行这个方法去查权限,并放入缓存中
	 * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
	 * @param principals
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		logger.info("调用doGetAuthorizationInfo方法获取权限");

		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

		String account = JwtUtil.getClaim(principals.toString(), SecurityConsts.ACCOUNT);
		User UserInfo = userService.findUserByAccount(account);

		//获取role
		List<Role> RoleList = roleService.findRoleByUserId(UserInfo.getId());
		//获取权限 从数据库查询出用户权限信息
		List<Object> AuthorityList = authorityService.findByUserId(UserInfo.getId());
		for(Role Role : RoleList){
			authorizationInfo.addRole(Role.getName());
			for(Object auth: AuthorityList){
				authorizationInfo.addStringPermission(auth.toString());
			}
		}
		return authorizationInfo;
	}

}
