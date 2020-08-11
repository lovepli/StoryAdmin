package com.story.storyadmin.mapper.sysmgr;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.story.storyadmin.domain.entity.sysmgr.SystemSequence;

import java.util.List;

public interface SystemSequenceMapper extends BaseMapper<SystemSequence> {

    int insert(SystemSequence record);

    List<SystemSequence> selectByExample(SystemSequence example);

    SystemSequence selectByPrimaryKey(SystemSequence key);


    int updateByPrimaryKey(SystemSequence record);
}