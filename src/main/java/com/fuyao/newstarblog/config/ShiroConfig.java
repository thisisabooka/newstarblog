package com.fuyao.newstarblog.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.fuyao.newstarblog.realms.ShiroRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;

/**
 * Shiro配置类；Shiro是Java的一个安全权限框架
 */
@Configuration    //说明这是一个配置类
public class ShiroConfig {


//    配置ShiroFilter过滤器
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

//        必须设置SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        shiroFilterFactoryBean.setLoginUrl("/tologinpage");    //未认证
        shiroFilterFactoryBean.setSuccessUrl("/toindexpage");    //认证成功
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized.html");    //未授权

//        过滤器
        LinkedHashMap<String,String> map = new LinkedHashMap<String,String>();
        map.put("/static/**","anon");

//        map.put("/toeditphoto", "authc");    //必须认证通过（登录）才可能访问
//        map.put("/submitphoto", "authc");
//        map.put("/toeditpwd", "authc");
//        map.put("/updatepwd", "authc");
//        map.put("/categor*/**", "authc");
//        map.put("/toindexpage", "authc");

//        配置退出过滤器；其中具体的退出代码Shiro已经替我们实现好了
        map.put("/logout","logout");

        map.put("/**","anon");    //anon，可以被匿名访问；其余所有的资源都可以被匿名访问

        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        return shiroFilterFactoryBean;
    }


//    配置SecurityManager安全管理器
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        设置Realm
        securityManager.setRealm(shiroRealm());
//        设置缓存管理器CacheManager
//        设置记住我RememberMe
        return securityManager;
    }


//    自定义Realm（真正控制登录认证和授权访问）
    @Bean
    public ShiroRealm shiroRealm(){
        ShiroRealm shiroRealm = new ShiroRealm();
//        MD5盐值加密
        shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return shiroRealm;
    }


//    生命周期BeanPost处理器，LifecycleBeanPostProcessor
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }


//    启用Shiro注解，但必须在配置了LifecycleBeanPostProcessor之后才可以使用
    @Bean
    @DependsOn({ "lifecycleBeanPostProcessor" })
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }


    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }


//    MD5盐值加密
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
//        使用MD5算法进行加密
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
//        加密的次数
        hashedCredentialsMatcher.setHashIterations(1024);
        return hashedCredentialsMatcher;
    }


    @Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }



}
