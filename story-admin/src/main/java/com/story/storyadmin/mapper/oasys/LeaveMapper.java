package com.story.storyadmin.mapper.oasys;

import com.story.storyadmin.domain.entity.oasys.Leave;
import com.story.storyadmin.domain.vo.oasys.LeaveVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LeaveMapper {
    void askLeave(Leave leave);

    void checkLeave(Leave leave);

    List<LeaveVo> selectLeaves();
}
