package com.story.storyadmin.mapper.dictonecode;

import com.story.storyadmin.domain.entity.dictonecode.DictOneDO;
import com.story.storyadmin.domain.entity.sysmgr.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author hanxinghua
 * @create 2019/12/28
 * @since 1.0.0
 */
@Mapper
public interface DictOneMapper {

    int save(DictOneDO dict);


    int insertDictLists(List<DictOneDO> dictLists);


}