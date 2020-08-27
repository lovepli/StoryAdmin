package com.story.storyadmin.config.shiro.security;


import com.story.storyadmin.config.shiro.LoginUser;

/**
 * 线程上下文，与应用上下文ApplicationContextAware区别开来,可以说是替代session存储用户登录信息
 * TODO 这里的作用和从session中取出用户信息是一样的处理方式，感觉这样做复杂了很多，
 * 这是因为这里是json web token 的形式，所以服务端不存储用户session信息，所以就不用session来存储用户信息
 *
 * 在进行一次http登录过程中，登录成功后，在UserContextFilter拦截器拦截过程中，将登录用户信息存到当前连接线程的内部属性中，在整个http连接服务器的过程中，该连接都可以获取到存在ThreadLocal中都登录用户信息，随时都可以取出来
 * 应用上下文ApplicationContextAware的作用是
 */
public class UserContext implements AutoCloseable {

    //ThreadLocal线程内部属性
    static final ThreadLocal<LoginUser> current = new ThreadLocal<>();

    /**
     * 构造函数
     * @param user
     */
    public UserContext(LoginUser user) {
        //将LoginUser 设置为线程内部属性，方便其他线程获取
        current.set(user);
    }

    /**
     *  获取登录用户
     */
    public static LoginUser getCurrentUser() {
        return current.get();
    }

    /**
     * 将登录用户信息设置为线程内部属性，方便其他线程获取
     * @param user
     */
    public static void setCurrentUser(LoginUser user) {
        //将LoginUser 设置为线程内部属性
        current.set(user);
    }

    @Override
    public void close() {
        current.remove();
    }
}