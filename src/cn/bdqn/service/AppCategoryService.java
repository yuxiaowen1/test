package cn.bdqn.service;

import cn.bdqn.pojo.AppCategory;

import java.util.List;

/**
 * 对app分类业务操作的规范
 * @author 余晓文
 * @version 1.0 2018-12-17
 */
public interface AppCategoryService {

    /**
     * 通过父级id查询分类集合
     * @param parentId 父级id
     * @return 分类集合
     */
    List<AppCategory> findAppCategoryByParentId(Integer parentId);
}
