package com.story.storyadmin.mapper.wind;

import com.story.storyadmin.domain.entity.wind.Organization;
import com.story.storyadmin.mapper.wind.tree.BaseTreeMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrganizationMapper extends BaseTreeMapper<Organization> {

    /**
     * @param userId
     * @return
     * @title: findListByUserId
     * @description: 通过用户查找组织机构
     * @return: List<Organization>
     */
    List<Organization> findListByUserId(String userId);
}
