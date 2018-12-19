package cn.bdqn.controller;

import cn.bdqn.pojo.AppInfo;
import cn.bdqn.pojo.DevUser;
import cn.bdqn.service.AppInfoService;
import cn.bdqn.service.DevUserService;
import cn.bdqn.utils.Constants;
import cn.bdqn.utils.JsonUtils;
import cn.bdqn.utils.PageBean;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 开发者用户控制器
 *
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
     *
     * @return 逻辑视图名
     */
    @RequestMapping(value = "/login")
    public String login() {
        return "devLogin";
    }

    /**
     * 开发者登录操作
     *
     * @param devCode     开发者登录名
     * @param devPassword 开发者密码
     * @param session     session对象
     * @param model       model对象
     * @return 逻辑视图名
     */
    @RequestMapping(value = "/dologin.html")
    public String doLogin(String devCode, String devPassword, HttpSession session, Model model) {
        DevUser devUser = devUserService.findDevLoginUser(devCode, devPassword);
        if (devUser != null) {
            session.setAttribute(Constants.DEV_USER_SESSION, devUser);
            return "dev/devMain";
        }
        model.addAttribute(Constants.SYS_MESSAGE, "用户名或者密码不正确！");
        return "devLogin";
    }

    /**
     * 注销操作
     *
     * @param session session对象
     * @param model   model对象
     * @return 逻辑视图名
     */
    @RequestMapping(value = "/logout.html")
    public String doL0ogOut(HttpSession session, Model model) {
        session.removeAttribute(Constants.DEV_USER_SESSION);
        model.addAttribute(Constants.SYS_MESSAGE, "注销成功！");
        return "devLogin";
    }

    /**
     * 跳转主页面
     *
     * @return 逻辑视图名
     */
    @RequestMapping(value = "/user/main.html")
    public String toMain() {
        return "dev/devMain";
    }

    /**
     * 跳转APP维护页面
     *
     * @param session        session对象
     * @param model          model对象
     * @param softwareName   软件名称
     * @param status         软件状态
     * @param platformId     所属平台编号
     * @param categoryLevel1 一类id
     * @param categoryLevel2 二类id
     * @param categoryLevel3 三类id
     * @param pageIndex      页码
     * @param pageSize       页面容量
     * @return 逻辑视图名
     */
    @RequestMapping(value = "/user/applist.html")
    public String toAppList(HttpSession session, Model model,
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
        model.addAttribute("pages", beans);
        return "dev/appinfolist";
    }

    /**
     * 跳转添加应用信息页面
     *
     * @return 逻辑视图名
     */
    @RequestMapping("/user/addappinfo.html")
    public String toAddAppInfo() {
        return "dev/appinfoadd";
    }

    /**
     * 判断APKName是否可用
     *
     * @param session session对象
     * @param APKName APKName
     * @return 是否可用
     */
    @RequestMapping("/user/apkexist.json")
    @ResponseBody
    public String getAppInfoByAPKName(HttpSession session,
                                      @RequestParam("APKName") String APKName) {
        DevUser devUser = (DevUser) session.getAttribute(Constants.DEV_USER_SESSION);
        AppInfo appInfo = appInfoService.findByAPKName(devUser.getId(), APKName);
        Map<String, Object> map = new HashMap<String, Object>();
        if (APKName == null || "".equals(APKName)) {
            map.put("APKName", "empty");
            return JsonUtils.toJson(map);
        } else if (appInfo != null) {
            map.put("APKName", "exist");
            return JsonUtils.toJson(map);
        } else if (appInfo == null) {
            map.put("APKName", "noexist");
            return JsonUtils.toJson(map);
        }
        map.put("APKName", "error");
        return JsonUtils.toJson(map);
    }

    /**
     * 实现添加 appinfo 应用实体操作
     *
     * @param appInfo       应用实体
     * @param session       session对象
     * @param model         model对象
     * @param request       request对象
     * @param multipartFile 上传文件对象
     * @return 逻辑视图名
     */
    @RequestMapping("/user/doaddappinfo.html")
    public String toAddAppInfo(AppInfo appInfo, HttpSession session, Model model, HttpServletRequest request,
                               @RequestParam(value = "a_logoPicPath") MultipartFile multipartFile) {
        if (!multipartFile.isEmpty()) {
            String path = session.getServletContext().getRealPath("/statics/uploadfiles/");
            String oldFileName = multipartFile.getOriginalFilename();
            String suffix = FilenameUtils.getExtension(oldFileName);
            if ("jpeg".equalsIgnoreCase(suffix)
                    && "peng".equalsIgnoreCase(suffix)
                    && "jpg".equalsIgnoreCase(suffix)
                    && "png".equalsIgnoreCase(suffix)
                    ) {
                model.addAttribute(Constants.SYS_MESSAGE, "文件格式错误！");
                System.out.println("文件格式错误！");
                return "dev/appinfoadd";
            }
            if (multipartFile.getSize() > 500000) {
                model.addAttribute("msg", "文件太大！");
                System.out.println("文件太大！");
                return "dev/appinfoadd";
            }
            String uploadFileName = appInfo.getAPKName() + "." + suffix;
            File file = new File(path, uploadFileName);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
            }
            try {
                multipartFile.transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute(Constants.SYS_MESSAGE, "上传失败！");
                return "dev/appinfoadd";
            }
            appInfo.setLogoPicPath(request.getContextPath() + "/statics/uploadfiles/" + uploadFileName);
            appInfo.setLogoLocPath(path + uploadFileName);
            appInfo.setDevId(((DevUser) session.getAttribute(Constants.DEV_USER_SESSION)).getId());
            appInfo.setCreatedBy(((DevUser) session.getAttribute(Constants.DEV_USER_SESSION)).getId());
            appInfo.setCreationDate(new Date());
            int count = appInfoService.addAppInfo(appInfo);
            if (count > 0) {
                return "redirect:/dev/user/applist.html";
            }
        }
        model.addAttribute(Constants.SYS_MESSAGE, "增加失败！");
        return "dev/appinfoadd";
    }

}
