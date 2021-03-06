package com.story.storyadmin.config;

import com.story.storyadmin.config.shiro.ShiroFilterProperties;
import com.story.storyadmin.config.shiro.ShiroRealm;
import com.story.storyadmin.config.shiro.cache.ShiroCacheManager;
import com.story.storyadmin.config.shiro.security.JwtFilter;
import com.story.storyadmin.config.shiro.security.JwtProperties;
import com.story.storyadmin.config.shiro.security.SystemLogoutFilter;
import com.story.storyadmin.config.shiro.security.UserContextFilter;
import com.story.storyadmin.service.common.ISyncCacheService;
import com.story.storyadmin.utils.JedisUtils;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * shiro核心配置
 */
@Configuration
public class ShiroConfig {
//    将对象注入spring的方式1
//    @Autowired
//    ShiroFilterProperties shiroFilterProperties;

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public static DefaultAdvisorAutoProxyCreator getLifecycleBeanPostProcessor() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        // 强制使用cglib
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    @Bean
    public DefaultWebSecurityManager  securityManager(ShiroRealm shiroRealm, ShiroCacheManager shiroCacheManager){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        //这里的shiroRealm为自定义的AuthorizingRealm
        securityManager.setRealm(shiroRealm);

        //关闭shiro自带的session
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);

        //这里的shiroCacheManager为自定义shiro缓存，重写了CacheManager接口的方法，实现了redis作为缓存Manager
        securityManager.setCacheManager(shiroCacheManager);
        return securityManager;
    }

    /**
     * 注入shiro过滤器
     * Shiro过滤器配置
     * Shiro 加载 Filter 的顺序：
     * 1、加载加载 DefaultFilter 中的默认 Filter (也就是配置文件中配置的 anno authc等filter)
     * 2、加载自定义 Filter (也就是我们自定义的jwt,logout等filter)
     * 3、加载 FFilterChainDefinitionMap
     * 这样的好处是，在没有进行登录之前，像登录接口，和durid监控接口，swagger接口文档，我们不需要登录就可以调用，像查询用户信息这些需要进行用户登录之后的接口就需要在调用接口的时候进行过滤判断，用户是否登录成功了才能让哥调用，所以休要自定义过滤器，拦截用户的请求进行判断是否登录
     * @param securityManager
     * @param jedisUtils
     * @param jwtProp
     * @param syncCacheService
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager, JedisUtils jedisUtils, JwtProperties jwtProp, ISyncCacheService syncCacheService) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        // Shiro的核心安全接口,这个属性是必须的
        shiroFilter.setSecurityManager(securityManager);

        //1、自定义过滤器
        // 添加jwt过滤器和logout过滤器
        Map<String, Filter> filterMap = new HashMap<>();
        //这里没有使用@Bean注册JwtFilter对象到spring容器中，而是直接new对象
        filterMap.put("jwt", new JwtFilter(jwtProp,syncCacheService,jedisUtils));
        filterMap.put("logout", new SystemLogoutFilter(jedisUtils));
        shiroFilter.setFilters(filterMap);

        //2、url过滤
        //动态配置拦截器注入
        Map<String, String> filterRuleMap = new HashMap<>(16);
        //从配置文件加载用户的接口API权限
        List<Map<String, String>> perms = this.getShiroFilterProperties().getPerms();
        perms.forEach(perm -> filterRuleMap.put(perm.get("key"), perm.get("value")));

        shiroFilter.setFilterChainDefinitionMap(filterRuleMap);

        return shiroFilter;
    }

    /**
     * 将对象注入spring的方式2
     * @return
     */
    @Bean
    public ShiroFilterProperties getShiroFilterProperties(){
        return new ShiroFilterProperties();
    }

    /**
     * 注册filter
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        //注册应用上下文filter
        bean.setFilter(new UserContextFilter());
        return bean;
    }

}
