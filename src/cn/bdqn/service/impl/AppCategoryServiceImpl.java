package cn.bdqn.service.impl;

import cn.bdqn.dao.AppCategoryMapper;
import cn.bdqn.pojo.AppCategory;
import cn.bdqn.service.AppCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * app分类业务操作实现类
 *
 * @author 余晓文
 * @version 1.0 2018-12-17
 */
@Service
public class AppCategoryServiceImpl implements AppCategoryService {

    @Resource
    private AppCategoryMapper appCategoryMapper;

    @Override
    public List<AppCategory> findAppCategoryByParentId(Integer parentId) {
        return appCategoryMapper.getAppCategoryByParentId(parentId);
    }
}
