package cn.bdqn.dao;

import cn.bdqn.pojo.AppInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 对数据库应用信息表操作的规范
 *
 * @author 余晓文
 * @version 1.0 2018-12-17
 */
public interface AppInfoMapper {

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
     * @param startRow       起始行
     * @param pageSize       页面容量
     * @return 应用信息列表
     */
    List<AppInfo> getAppInfoByPage(@Param("devId") Integer userId,
                                   @Param("softwareName") String softwareName,
                                   @Param("status") Integer status,
                                   @Param("platformId") Integer platformId,
                                   @Param("categoryLevel1") Integer categoryLevel1,
                                   @Param("categoryLevel2") Integer categoryLevel2,
                                   @Param("categoryLevel3") Integer categoryLevel3,
                                   @Param("startRow") Integer startRow,
                                   @Param("pageSize") Integer pageSize);

    /**
     * 查询总数
     *
     * @param userId         创建人
     * @param softwareName   软件名称
     * @param status         状态（来源于：data_dictionary，1 待审核 2 审核通过 3 审核不通过 4 已上架 5 已下架）
     * @param platformId     所属平台（来源于：data_dictionary，1 手机 2 平板 3 通用）
     * @param categoryLevel1 一类id
     * @param categoryLevel2 二类id
     * @param categoryLevel3 三类id
     * @return
     */
    int getCount(@Param("devId") Integer userId,
                 @Param("softwareName") String softwareName,
                 @Param("status") Integer status,
                 @Param("platformId") Integer platformId,
                 @Param("categoryLevel1") Integer categoryLevel1,
                 @Param("categoryLevel2") Integer categoryLevel2,
                 @Param("categoryLevel3") Integer categoryLevel3);

    /**
     * 通过APKName查询应用信息实体
     *
     * @param userId  所属用户id
     * @param APKName APKName
     * @return 应用信息实体
     */
    AppInfo getByAPKName(@Param("devId") Integer userId, @Param("APKName") String APKName);

}
