package cn.bdqn.controller;

import cn.bdqn.pojo.AppCategory;
import cn.bdqn.service.AppCategoryService;
import cn.bdqn.utils.JsonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * app分类控制器
 * @author 余晓文
 * @version 1.0 2018-12-18
 */
@Controller
@RequestMapping(value = "/appCategory")
public class AppCategoryController {

    @Resource
    private AppCategoryService appCategoryService;

    @RequestMapping(value = "/list.json")
    @ResponseBody
    public String appList(@RequestParam("pid") Integer pid){
        List<AppCategory> appCategories = appCategoryService.findAppCategoryByParentId(pid);
        return JsonUtils.toJson(appCategories);
    }
}
