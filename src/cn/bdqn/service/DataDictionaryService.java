package cn.bdqn.service;

import cn.bdqn.pojo.DataDictionary;

import java.util.List;

/**
 * 对数据字典业务操作的规范
 * @author 余晓文
 * @version 1.0 2018-12-17
 */
public interface DataDictionaryService {

    /**
     * 通过字典类型编码和类型值ID查询数据字典集合
     * @param typeCode 字典类型编码
     * @param valueId 类型值ID
     * @return 数据字典集合
     */
    List<DataDictionary> findDataDictionaryList(String typeCode,Integer valueId);
}
