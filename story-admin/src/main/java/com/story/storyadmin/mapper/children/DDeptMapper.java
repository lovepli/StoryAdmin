package com.story.storyadmin.mapper.children;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.story.storyadmin.domain.entity.children.DDept;
import com.story.storyadmin.domain.entity.children.dto.DDeptDTO;
import com.story.storyadmin.domain.entity.children.onetomany.DDeptBO;
import org.springframework.stereotype.Repository;

import java.util.List;

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
}
