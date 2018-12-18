package cn.bdqn.service.impl;

import cn.bdqn.dao.DevUserMapper;
import cn.bdqn.pojo.DevUser;
import cn.bdqn.service.DevUserService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 开发者用户业务操作实现类
 * @author 余晓文
 * @version 1.0 2018-12-17
 */
@Service
public class DevUserServiceImpl implements DevUserService {

    @Resource
    private DevUserMapper devUserMapper;

    @Override
    public DevUser findDevLoginUser(String devCode, String devPassword) {
        return devUserMapper.getDevLoginUser(devCode,devPassword);
    }
}
