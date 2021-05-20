package com.story.storyadmin.mapper.bank;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.story.storyadmin.domain.entity.bank.NewGeneration;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 待办事项 Mapper 接口
 * </p>
 *
 * @author sunnj
 * @since 2019-08-14
 */
public interface NewGenerationMapper extends BaseMapper<NewGeneration> {

    public List<NewGeneration> findByPage(Page page, @Param("jsonObject") JSONObject jsonObject);

    public List<NewGeneration> findByPage2(Page page, @Param("uploadDate") String uploadDate,@Param("accountDays") Integer accountDays,@Param("netCodeList") List<String> netCodeList);

}
