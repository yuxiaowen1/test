package cn.bdqn.dao;

import cn.bdqn.pojo.DevUser;
import org.apache.ibatis.annotations.Param;

/**
 * 对数据库开发者用户表操作的规范
 * @author 余晓文
 * @version 1.0 2018-12-17
 */
public interface DevUserMapper {
    /**
     * 根据开发者帐号和开发者密码获取开发者用户
     * @param devCode 开发者帐号
     * @param devPassword 开发者密码
     * @return 开发者用户
     */
    DevUser getDevLoginUser(@Param("devCode") String devCode,@Param("devPassword") String devPassword);
}
