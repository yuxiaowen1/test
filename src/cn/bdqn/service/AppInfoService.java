package cn.bdqn.service;

import cn.bdqn.pojo.AppInfo;
import cn.bdqn.utils.PageBean;

/**
 * 对应用信息业务操作的规范
 *
 * @author 余晓文
 * @version 1.0 2018-12-17
 */
public interface AppInfoService {

    /**
     * 按条件分页查询应用信息列表
     *
     * @param userId         创建人
     * @param softwareName   软件名称
     * @param status         状态（来源于：data_dictionary，1 待审核 2 审核通过 3 审核不通过 4 已上架 5 已下架）
     * @param platformId     所属平台（来源于：data_dictionary，1 手机 2 平板 3 通用）
     * @param categoryLevel1 一类id
     * @param categoryLevel2 二类id
     * @param categoryLevel3 三类id
     * @param pageIndex      页码
     * @param pageSize       页面容量
     * @return 应用信息列表
     */
    PageBean<AppInfo> findAppInfoByPage(Integer userId,
                                        String softwareName,
                                        Integer status,
                                        Integer platformId,
                                        Integer categoryLevel1,
                                        Integer categoryLevel2,
                                        Integer categoryLevel3,
                                        Integer pageIndex,
                                        Integer pageSize);

    /**
     * 通过APKName查询应用信息实体
     *
     * @param userId  所属用户id
     * @param APKName APKName
     * @return 应用信息实体
     */
    AppInfo findByAPKName(Integer userId,String APKName);

    /**
     * 添加应用信息实体
     *
     * @param appInfo 应用信息
     * @return 影响数据行数
     */
    int addAppInfo(AppInfo appInfo);

    /**
     * 根据id查询应用信息实体
     *
     * @param id 应用信息实体id
     * @return 应用信息实体
     */
    AppInfo findById(Integer id);
}
