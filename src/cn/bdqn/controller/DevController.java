package cn.bdqn.controller;

import cn.bdqn.pojo.DevUser;
import cn.bdqn.service.DevUserService;
import cn.bdqn.utils.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/dev")
public class DevController {

    @Resource
    private DevUserService devUserService;

    @RequestMapping(value = "/login")
    public String login(){
        return "dev/devLogin";
    }

    @RequestMapping(value = "/dologin.html")
    public String doLogin(String devCode, String devPassword, HttpSession session, Model model){
        DevUser devUser = devUserService.findDevLoginUser(devCode, devPassword);
        if (devUser!=null){
            session.setAttribute(Constants.DEV_USER_SESSION,devUser);
            return "dev/devMain";
        }
        model.addAttribute(Constants.SYS_MESSAGE,"用户名或者密码不正确！");
        return "dev/devLogin";
    }

}
