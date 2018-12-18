package cn.bdqn.service;

import cn.bdqn.pojo.BackendUser;

/**
 * 对后台用户业务操作的规范
 * @author 余晓文
 * @version 1.0 2018-12-17
 */
public interface BackendUserService {

    /**
     * 根据后台用户帐号和后台用户密码获取后台用户
     * @param userCode 后台用户帐号
     * @param userPassword 后台用户密码
     * @return 后台用户
     */
    BackendUser findBackendLoginUser(String userCode, String userPassword);
}
