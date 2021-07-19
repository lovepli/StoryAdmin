package com.story.storyadmin.service.baseinfo.impl;

import com.story.storyadmin.config.shiro.security.UserContext;
import com.story.storyadmin.constant.enumtype.YNFlagStatusEnum;
import com.story.storyadmin.domain.entity.baseinfo.Dict;
import com.story.storyadmin.mapper.baseinfo.DictMapper;
import com.story.storyadmin.service.baseinfo.DictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author sunnj
 * @since 2019-07-12
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    /**
     * 批量保存
     * 批量操作或者操作多张表，添加事务回滚
     * @param dictList
     * @return
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean batchSave(List<Dict> dictList) {
        //遍历字典集
        for(Dict dict: dictList){
            if(dict.getId()!=null){
                //修改
                this.updateById(dict);
            }else{//添加
                dict.setYnFlag(YNFlagStatusEnum.VALID.getCode());
                dict.setCreatedTime(Date.from(Instant.now()));
                dict.setEditor(UserContext.getCurrentUser().getAccount());
                dict.setCreator(UserContext.getCurrentUser().getAccount());
                //遍历添加
                this.save(dict);
            }
        }
        return true;
    }
}
