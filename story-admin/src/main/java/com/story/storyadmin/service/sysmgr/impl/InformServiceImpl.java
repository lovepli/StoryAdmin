package com.story.storyadmin.service.sysmgr.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.story.storyadmin.domain.entity.sysmgr.Inform;
import com.story.storyadmin.mapper.sysmgr.InformMapper;
import com.story.storyadmin.service.sysmgr.InformService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class InformServiceImpl extends ServiceImpl<InformMapper, Inform> implements InformService {
    @Override
    public void add(Inform inform, int creatorId) {

    }

    @Override
    public List<Inform> querySimpleList(Short status, String title, Integer creatorId, Boolean topFirst, Date startDate, Date endDate) {
        return null;
    }

    @Override
    public Inform get(int id) {
        return null;
    }

    @Override
    public void topOrNot(int id, boolean isTop) {

    }

    @Override
    public void cancel(int id, Integer staffId) {

    }

    @Override
    public void outdate(int id, Integer staffId) {

    }
}
