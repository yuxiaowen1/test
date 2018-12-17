package cn.bdqn.utils;

import com.alibaba.fastjson.JSONObject;

public class JsonUtils {

    public static String toJson(Object obj,String... patten){
        String patten1 = "yyyy-MM-dd";
        if (patten!=null && patten.length>0){
            patten1 = patten[0];
        }
        return JSONObject.toJSONStringWithDateFormat(obj,patten1);
    }
}
