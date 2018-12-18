package cn.bdqn.service.impl;

import cn.bdqn.dao.BackendUserMapper;
import cn.bdqn.pojo.BackendUser;
import cn.bdqn.service.BackendUserService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 后台用户业务操作实现类
 * @author 余晓文
 * @version 1.0 2018-12-17
 */
@Service
public class BackendUserServiceImpl implements BackendUserService {

    @Resource
    private BackendUserMapper backendUserMapper;

    @Override
    public BackendUser findBackendLoginUser(String userCode, String userPassword) {
        return backendUserMapper.getBackendLoginUser(userCode,userPassword);
    }
}
