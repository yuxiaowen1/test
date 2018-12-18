package cn.bdqn.controller;

import cn.bdqn.pojo.DataDictionary;
import cn.bdqn.service.DataDictionaryService;
import cn.bdqn.utils.JsonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 数据字典控制器
 * @author 余晓文
 * @version 1.0 2018-12-18
 */
@Controller
@RequestMapping(value = "/dataDictionary")
public class DataDictionaryController {

    @Resource
    private DataDictionaryService dataDictionaryService;

    @RequestMapping("/list.json")
    @ResponseBody
    public String getDataDictionaryByTypeCode(@RequestParam(value = "tcode",required = false) String typeCode,
                                              @RequestParam(value = "valueId",required = false) Integer valueId){
        List<DataDictionary> dictionaryList = dataDictionaryService.findDataDictionaryList(typeCode, valueId);
        return JsonUtils.toJson(dictionaryList);
    }
}
