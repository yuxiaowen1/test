package cn.bdqn.dao;

import cn.bdqn.pojo.AppCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 对数据库app分类表操作的规范
 * @author 余晓文
 * @version 1.0 2018-12-17
 */
public interface AppCategoryMapper {

    /**
     * 通过父级id查询分类集合
     * @param parentId 父级id
     * @return 分类集合
     */
    List<AppCategory> getAppCategoryByParentId(@Param("parentId") Integer parentId);
}
