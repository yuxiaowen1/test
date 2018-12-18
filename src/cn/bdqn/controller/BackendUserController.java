package cn.bdqn.controller;

import cn.bdqn.pojo.AppInfo;
import cn.bdqn.pojo.BackendUser;
import cn.bdqn.pojo.DataDictionary;
import cn.bdqn.pojo.DevUser;
import cn.bdqn.service.AppInfoService;
import cn.bdqn.service.BackendUserService;
import cn.bdqn.service.DataDictionaryService;
import cn.bdqn.utils.Constants;
import cn.bdqn.utils.JsonUtils;
import cn.bdqn.utils.PageBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 后台用户控制器
 *
 * @author 余晓文
 * @version 1.0 2018-12-18
 */
@Controller
@RequestMapping(value = "/backend")
public class BackendUserController {

    @Resource
    private BackendUserService backendUserService;

    /**
     * 跳转后台登录页面
     *
     * @return
     */
    @RequestMapping(value = "/login")
    public String login() {
        return "backendLogin";
    }

    /**
     * 后台用户登录操作
     *
     * @param userCode     后台用户登录名
     * @param userPassword 后台用户密码
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value = "/dologin.html")
    public String doLogin(String userCode, String userPassword, HttpSession session, Model model) {
        BackendUser backendUser = backendUserService.findBackendLoginUser(userCode, userPassword);
        if (backendUser != null) {
            System.out.println(JsonUtils.toJson(backendUser));
            session.setAttribute(Constants.USER_SESSION, backendUser);
            return "backend/backendMain";
        }
        model.addAttribute(Constants.SYS_MESSAGE, "用户名或者密码不正确！");
        return "backendLogin";
    }

    /**
     * 注销操作
     *
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value = "/logout.html")
    public String doL0ogOut(HttpSession session, Model model) {
        session.removeAttribute(Constants.USER_SESSION);
        model.addAttribute(Constants.SYS_MESSAGE, "注销成功！");
        return "backendLogin";
    }

    /**
     * 跳转主页面
     *
     * @return
     */
    @RequestMapping(value = "/user/main.html")
    public String toMain(){
        return "backend/backendMain";
    }

}
