package cn.bdqn.dao;

import cn.bdqn.pojo.BackendUser;
import org.apache.ibatis.annotations.Param;

/**
 * 对数据库后台用户表操作的规范
 * @author 余晓文
 * @version 1.0 2018-12-17
 */
public interface BackendUserMapper {
    /**
     * 根据后台用户帐号和后台用户密码获取后台用户
     * @param userCode 后台用户帐号
     * @param userPassword 后台用户密码
     * @return 后台用户
     */
    BackendUser getBackendLoginUser(@Param("userCode") String userCode, @Param("userPassword") String userPassword);
}
