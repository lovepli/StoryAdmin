package com.story.storyadmin.service.wind.tree;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.story.storyadmin.domain.entity.wind.tree.TreeNode;
import com.story.storyadmin.service.wind.base.ICommonService;


import java.io.Serializable;
import java.util.List;

public interface ITreeCommonService<T extends Serializable & TreeNode<ID>, ID extends Serializable>
        extends ICommonService<T> {
    public List<T> selectTreeList(Wrapper<T> wrapper);

}
