package cn.bdqn.service.impl;

import cn.bdqn.dao.AppInfoMapper;
import cn.bdqn.pojo.AppInfo;
import cn.bdqn.service.AppInfoService;
import cn.bdqn.utils.PageBean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 应用信息业务操作实现类
 *
 * @author 余晓文
 * @version 1.0 2018-12-17
 */
@Service
public class AppInfoServiceImpl implements AppInfoService {

    @Resource
    private AppInfoMapper appInfoMapper;

    @Override
    public AppInfo findByAPKName(Integer userId, String APKName) {
        return appInfoMapper.getByAPKName(userId,APKName);
    }

    @Override
    public PageBean<AppInfo> findAppInfoByPage(Integer userId, String softwareName, Integer status, Integer platformId, Integer categoryLevel1, Integer categoryLevel2, Integer categoryLevel3, Integer pageIndex, Integer pageSize) {
        PageBean<AppInfo> pageBean = new PageBean<AppInfo>();;
        int count = appInfoMapper.getCount(userId, softwareName, status, platformId, categoryLevel1, categoryLevel2, categoryLevel3);
        pageBean.setTotalCount(count);
        pageBean.setPageSize(pageSize);
        pageBean.setPageIndex(pageIndex);
        if (count > 0) {
            List<AppInfo> appInfoList = appInfoMapper.getAppInfoByPage(userId, softwareName, status, platformId, categoryLevel1, categoryLevel2, categoryLevel3, pageBean.getStartRow(), pageSize);
            pageBean.setList(appInfoList);
        }
        return pageBean;
    }
}
