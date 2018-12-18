package cn.bdqn.service.impl;

import cn.bdqn.dao.DataDictionaryMapper;
import cn.bdqn.pojo.DataDictionary;
import cn.bdqn.service.DataDictionaryService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 数据字典业务操作实现类
 * @author 余晓文
 * @version 1.0 2018-12-17
 */
@Service
public class DataDictionaryServiceImpl implements DataDictionaryService {

    @Resource
    private DataDictionaryMapper dataDictionaryMapper;

    @Override
    public List<DataDictionary> findDataDictionaryList(String typeCode, Integer valueId) {
        return dataDictionaryMapper.getDataDictionaryList(typeCode,valueId);
    }
}
