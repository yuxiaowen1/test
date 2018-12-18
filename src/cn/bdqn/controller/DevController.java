package cn.bdqn.controller;

import cn.bdqn.pojo.AppInfo;
import cn.bdqn.pojo.DevUser;
import cn.bdqn.service.AppInfoService;
import cn.bdqn.service.DevUserService;
import cn.bdqn.utils.Constants;
import cn.bdqn.utils.PageBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * 开发者用户控制器
 * @author 余晓文
 * @version 1.0 2018-12-18
 */
@Controller
@RequestMapping(value = "/dev")
public class DevController {

    @Resource
    private DevUserService devUserService;

    @Resource
    private AppInfoService appInfoService;

    /**
     * 跳转开发者登录页面
     * @return
     */
    @RequestMapping(value = "/login")
    public String login(){
        return "devLogin";
    }

    /**
     * 开发者登录操作
     * @param devCode 开发者登录名
     * @param devPassword 开发者密码
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value = "/dologin.html")
    public String doLogin(String devCode, String devPassword, HttpSession session, Model model){
        DevUser devUser = devUserService.findDevLoginUser(devCode, devPassword);
        if (devUser!=null){
            session.setAttribute(Constants.DEV_USER_SESSION,devUser);
            return "dev/devMain";
        }
        model.addAttribute(Constants.SYS_MESSAGE,"用户名或者密码不正确！");
        return "devLogin";
    }

    /**
     * 注销操作
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value = "/logout.html")
    public String doL0ogOut(HttpSession session, Model model){
        session.removeAttribute(Constants.DEV_USER_SESSION);
        model.addAttribute(Constants.SYS_MESSAGE,"注销成功！");
        return "devLogin";
    }

    /**
     * 跳转主页面
     * @return
     */
    @RequestMapping(value = "/user/main.html")
    public String toMain(){
        return "dev/devMain";
    }

    /**
     * 跳转APP维护
     * @return
     */
    @RequestMapping(value = "/user/applist.html")
    public String toAppList(HttpSession session,Model model,
                            @RequestParam(value = "softwareName", required = false) String softwareName,
                            @RequestParam(value = "status", required = false) Integer status,
                            @RequestParam(value = "platformId", required = false) Integer platformId,
                            @RequestParam(value = "categoryLevel1", required = false) Integer categoryLevel1,
                            @RequestParam(value = "categoryLevel2", required = false) Integer categoryLevel2,
                            @RequestParam(value = "categoryLevel3", required = false) Integer categoryLevel3,
                            @RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
                            @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        DevUser devUser = (DevUser) session.getAttribute(Constants.DEV_USER_SESSION);
        PageBean<AppInfo> beans = appInfoService.findAppInfoByPage(devUser.getId(), softwareName, status, platformId, categoryLevel1, categoryLevel2, categoryLevel3, pageIndex, pageSize);
        model.addAttribute("pages",beans);
        return "dev/appinfolist";
    }

}
