package cn.bdqn.dao;

import cn.bdqn.pojo.DataDictionary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 对数据库数据字典表操作的规范
 * @author 余晓文
 * @version 1.0 2018-12-17
 */
public interface DataDictionaryMapper {

    /**
     * 通过字典类型编码和类型值ID查询数据字典集合
     * @param typeCode 字典类型编码
     * @param valueId 类型值ID
     * @return 数据字典集合
     */
    List<DataDictionary> getDataDictionaryList(@Param("typeCode")String typeCode,@Param("valueId")Integer valueId);
}
