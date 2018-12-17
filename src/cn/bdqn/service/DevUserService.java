package cn.bdqn.service;

import cn.bdqn.pojo.DevUser;

/**
 * 对开发者用户业务操作的规范
 * @author 余晓文
 * @version 1.0 2018-12-17
 */
public interface DevUserService {

    /**
     * 根据开发者帐号和开发者密码获取开发者用户
     * @param devCode 开发者帐号
     * @param devPassword 开发者密码
     * @return 开发者用户
     */
    DevUser findDevLoginUser(String devCode,String devPassword);
}
