package com.story.storyadmin.mapper.bank;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.story.storyadmin.domain.entity.bank.NewGeneration;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 待办事项 Mapper 接口
 * </p>
 *
 * @author sunnj
 * @since 2019-08-14
 */
@Repository
public interface NewGenerationMapper extends BaseMapper<NewGeneration> {

    public List<NewGeneration> findByPage(Page page, @Param("jsonObject") JSONObject jsonObject);

    public List<NewGeneration> findByPage2(Page page, @Param("uploadDate") String uploadDate,@Param("accountDays") Integer accountDays,@Param("netCodeList") List<String> netCodeList);

    public List<NewGeneration> findByPage3(Page page, @Param("uploadDate") Date uploadDate, @Param("accountDays") Integer accountDays, @Param("netCodeList") List<String> netCodeList);

    // 批量插入
    void idsertNewGenerations(@Param("list") List<NewGeneration> list,@Param("creator") String creator);

    //批量删除
    public void deleteNewGenerations(@Param("ids") List<String> ids);
    // 批量修改 逻辑删除
    public void deleteNewGenerations2(@Param("ids") List<String> ids,@Param("deletor") String deletor);
   // 根据日期删除
    void deleteNewGenerationByUploadDate(@Param("uploadDate") Date uploadDate);
}
