package com.story.storyadmin.mapper.children;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.story.storyadmin.domain.entity.children.DDept;
import com.story.storyadmin.domain.entity.children.DDeptPOJO;
import com.story.storyadmin.domain.entity.children.dto.DDeptDTO;
import com.story.storyadmin.domain.entity.children.dto.DDeptDTO2;
import com.story.storyadmin.domain.entity.children.onetomany.DDeptBO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author: 59688
 * @date: 2021/7/9
 * @description:
 */
@Repository
public interface DDeptMapper  extends BaseMapper<DDept> {

    DDept selectDeptById(String id);

    DDeptBO selectDeptEmployeeByDeptId(String id);

    List<DDept> selectDeptByParentId(String parentId);

    DDeptDTO selectDeptById2(String id);


    public List<DDept> findByPage(Page page, @Param("jsonObject") JSONObject jsonObject);

    public DDeptDTO2 findByPage2(@Param("current") Integer current,@Param("size") Integer size);

    public List<DDeptDTO2> findByPage3(Page page);

    public List<DDeptDTO2>  findByPage4(@Param("current") Integer current,@Param("size") Integer size);

    /**
     * 获取树形结构数据
     *
     * @return 树形结构
     */
    public List<DDeptPOJO> noteTree();


}
