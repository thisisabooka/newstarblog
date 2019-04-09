package com.fuyao.newstarblog.controller.system;

import com.fuyao.newstarblog.beans.system.Admin;
import com.fuyao.newstarblog.service.system.AdminService;
import com.fuyao.newstarblog.utils.KaptchaUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * 主页控制器
 */
@Controller
public class IndexController {

    @Autowired
    private AdminService adminService;

    //    来到登录页面
    @RequestMapping(value = {"/", "tologinpage"})
    public String toLoginPage() {
        return "system/login";
    }


    //    来到注册页面
    @RequestMapping(value = "toregisterpage")
    public String toRegisterPage() {
        return "system/register";
    }


    //    注册
    @PostMapping(value = "/register")
    public ModelAndView register(@Valid /*@Validated*/ Admin admin, BindingResult bindingResult) {

//        获取校验错误信息
        if(bindingResult.hasErrors()){
//            输出错误信息
//            List<ObjectError> allErrors = bindingResult.getAllErrors();
//            for(Object objectError : allErrors){
//                输出错误信息
//                System.out.println(objectError.getDefaultMessage());
//            }
//            将错误信息传到页面
            ModelAndView mv = new ModelAndView();
//            mv.addObject("allErrors",allErrors);
            mv.addObject("msg", "小星提示：注册失败，请按照要求进行填写，重新尝试一下吧！");
            mv.addObject("url", "/toregisterpage");
            mv.setViewName("common/error");
            return mv;
        }

        Admin a = adminService.register(admin);
        if (a == null) {
            ModelAndView mv = new ModelAndView();
            mv.addObject("msg", "注册失败，重新尝试一下吧");
            mv.addObject("url", "/toregisterpage");
            mv.setViewName("common/error");
            return mv;
        } else {
            ModelAndView mv = new ModelAndView();
            mv.addObject("msg", "注册成功，快去登录吧");
            mv.addObject("url", "/tologinpage");
            mv.setViewName("common/success");
            return mv;
        }
    }


    //    登录认证
    @PostMapping("/login")
    public ModelAndView login(@RequestParam("nickname") String nickname,
                              @RequestParam("password") String password,
                              HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();

        if (!KaptchaUtil.checkVerifyCode(request)) {
            mv.addObject("msg","验证码错误！");
            mv.addObject("url","/tologinpage");
            mv.setViewName("common/error");
            return mv;
        }

        Subject currentUser = SecurityUtils.getSubject();

        if (!currentUser.isAuthenticated()) {
//            把用户名和密码封装为UsernamePasswordToken对象
            UsernamePasswordToken token = new UsernamePasswordToken(nickname, password);
//            RememberMe
//            token.setRememberMe(true);
            try {
//				执行登录
//                System.out.println(token.hashCode());
                currentUser.login(token);
            } catch (AuthenticationException ae) {
//            System.out.println("认证失败，"+ae.getMessage());
                mv.addObject("msg", ae.getMessage());
                mv.addObject("url", "/tologinpage");
                mv.setViewName("common/error");
                return mv;
            }

        }

        request.getSession().setAttribute("nickname",nickname);

        mv.setViewName("redirect:/toindexpage");
        return mv;
    }


//    来到主页
    @RequestMapping(value = "/toindexpage")
    public String toIndexPage(){
        return "system/index";
    }


}

