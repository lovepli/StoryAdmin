package com.story.storyadmin.service.wind.tree.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;

import com.story.storyadmin.domain.entity.wind.tree.TreeNode;
import com.story.storyadmin.mapper.wind.tree.BaseTreeMapper;
import com.story.storyadmin.service.wind.base.impl.CommonServiceImpl;
import com.story.storyadmin.service.wind.tree.ITreeCommonService;
import com.story.storyadmin.utils.wind.ObjectUtils;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Transactional
public class TreeCommonServiceImpl<M extends BaseTreeMapper<T>, T extends Serializable & TreeNode<ID>, ID extends Serializable>
        extends CommonServiceImpl<M, T> implements ITreeCommonService<T, ID> {

    @Override
    public T selectById(Serializable id) {
        return baseMapper.selectByTreeId(id);
    }

    @Override
    public List<T> selectTreeList(Wrapper<T> wrapper) {
        List<T> content = baseMapper.selectTreeList(wrapper);
        return content;
    }


    @Override
    public boolean insert(T entity) {
        if (isDemo){
            return true;
        }

        if (!ObjectUtils.isNullOrEmpty(entity.getParentId())) {
            T parent = selectById(entity.getParentId());
            entity.setParentId(parent.getId());
            entity.setParentIds(parent.makeSelfAsNewParentIds());
        } else {
            entity.setParentId(null);
        }
        return super.save(entity);
    }

    @Override
    public boolean insertOrUpdate(T entity) {
        if (isDemo){
            return true;
        }

        if (!ObjectUtils.isNullOrEmpty(entity.getParentId())) {
            T parent = selectById(entity.getParentId());
            updateSelftAndChild(entity, parent.getId(), parent.makeSelfAsNewParentIds());
        } else {
            entity.setParentId(null);
            updateSelftAndChild(entity, null, null);
        }
        return true;
    }


    private void updateSelftAndChild(T entity, ID newParentId, String newParentIds) {
        if (isDemo){
            return;
        }

        T oldEntity = selectById(entity.getId());
        String oldChildrenParentIds = oldEntity.makeSelfAsNewParentIds();
        entity.setParentId(newParentId);
        entity.setParentIds(newParentIds);
        super.insertOrUpdate(entity);
        String newChildrenParentIds = entity.makeSelfAsNewParentIds();
        baseMapper.updateSunTreeParentIds(newChildrenParentIds, oldChildrenParentIds);
    }

}

