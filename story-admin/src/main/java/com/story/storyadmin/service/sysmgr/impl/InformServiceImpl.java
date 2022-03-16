package com.story.storyadmin.service.sysmgr.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.story.storyadmin.common.exception.CustomException;
import com.story.storyadmin.constant.enumtype.ResultEnum;
import com.story.storyadmin.common.cache.CacheKeySeed;
import com.story.storyadmin.common.cache.KeySeedManager;
import com.story.storyadmin.common.cache.SimpleKvCache;
import com.story.storyadmin.config.shiro.security.UserContext;
import com.story.storyadmin.domain.entity.sysmgr.Attachment;
import com.story.storyadmin.domain.entity.sysmgr.Inform;
import com.story.storyadmin.domain.entity.sysmgr.User;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.domain.vo.sysmgr.InformVo;
import com.story.storyadmin.mapper.sysmgr.InformMapper;
import com.story.storyadmin.service.sysmgr.AttachmentService;
import com.story.storyadmin.service.sysmgr.InformService;
import com.story.storyadmin.service.sysmgr.UserService;
import com.story.storyadmin.utils.MethodUtil;
import com.story.storyadmin.utils.ObjectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.logging.log4j.util.Strings.trimToNull;

@Service
public class InformServiceImpl extends ServiceImpl<InformMapper, Inform> implements InformService {

    private static final Logger logger = LoggerFactory.getLogger(InformServiceImpl.class);

    // 引入自定义缓存
    private final CacheKeySeed MEM_FLAG_INFORM_INFO = KeySeedManager.get("INFORM_INFO_");

    @Autowired(required=true)
    private SimpleKvCache kvCache;

    @Autowired
    AttachmentService attachmentService;

    @Autowired
    UserService UserService;

    @Override
    public Result persist(Inform inform) {
        //当前系统日期
      //  Date currentDate = Date.from(Instant.now());
        inform.setCreateDate(new Date());
        System.out.println("############创建者ID为："+UserContext.getCurrentUser().getUserId());
        inform.setCreatorId(UserContext.getCurrentUser().getUserId());
        inform.setStatus(NORMAL);
        //新增用户
        baseMapper.insert(inform);
        return new Result(true, "新增公告", null, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
    }

    @Override
    public List<Inform> querySimpleList(Short status, String title, Integer creatorId, Boolean topFirst, Date startDate, Date endDate) {
        return null;
    }

    /**
     * 按主键获取详情信息
     * @param id ID
     * @return
     */
    @Override
    public Inform get(Long id) {
        return kvCache.getOrDefault(
                MEM_FLAG_INFORM_INFO.key(id),
                () -> {
                    Inform inform = baseMapper.selectById(id);
                    if (inform == null) {
                        throw new CustomException(ResultEnum.FAILE_Id_EXCEPTION, MethodUtil.getLineInfo());
                    }
                    // 对象克隆的方法
                    InformVo result = ObjectUtil.generateSubclass(inform, InformVo.class);
                    String attachment = inform.getAttchmentList();
                    if ((attachment = trimToNull(attachment)) != null) {
                        // 获取附件ID
                        String[] ids = attachment.split(",");
                        // 根据ID获取所有附件名称信息
                        List<String> idList = Arrays.stream(ids).collect(Collectors.toList());
                        logger.info("从缓存中查询出的附件id为:{}"+idList);
                        // 根据ids
                        List<Attachment> attachments = attachmentService.selectNamesByIds(idList);
                        result.setAttachmentsToShow(attachments);
                    }
                    // 通过操作人的id查询出操作人名称
                    User userBean = UserService.selectUserById(UserContext.getCurrentUser().getUserId());
                   // result.setCreatorName(userBean.getName());
                    result.setCreatorName("张三"); // TODO 这里要inform表与user表的id进行关联查询
                    return result;
                }
        );
    }

    @Override
    public Result topOrNot(Long id, boolean isTop) {

        Inform informBean= assertExistAndNormal(id);
        informBean.setTop(isTop);
        baseMapper.updateById(informBean);
        return new Result(true, "公告的置顶状态改变", null, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
    }

    @Override
    public Result cancel(Long id) {
        //当前系统日期
        Date currentDate = Date.from(Instant.now());

        Inform informBean = assertExistAndNormal(id);
        informBean.setStatus(CANCELED);
        // 操作人ID
        informBean.setCanceler(UserContext.getCurrentUser().getUserId());
        informBean.setCancelDate(currentDate);
        informBean.setTop(false);
        baseMapper.updateById(informBean);
        return new Result(true, "撤销公告", null, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
    }

    @Override
    public Result outdate(Long id) {
        //当前系统日期
        Date currentDate = Date.from(Instant.now());

        Inform informBean = assertExistAndNormal(id);
        informBean.setStatus(OUTDATED);
        // 操作人ID
        informBean.setOutdateOperator(UserContext.getCurrentUser().getUserId());
        informBean.setOutdateDate(currentDate);
        informBean.setTop(false);
        baseMapper.updateById(informBean);
        return new Result(true, "公告过期", null, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
    }

    /**
     * 校验公告存在且状态为正常
     *
     * @param id 公告ID
     * @return 公告
     */
    private Inform assertExistAndNormal(Long id) {
        Inform toCheck = baseMapper.selectById(id);
        if (toCheck == null) {
            throw new CustomException(ResultEnum.FAILE_Id_EXCEPTION,MethodUtil.getLineInfo());
        }
        if (toCheck.getStatus() != NORMAL) {
            throw new CustomException(ResultEnum.STATUS_ERROR_EXCEPTION,  MethodUtil.getLineInfo());
        }
        return toCheck;
    }
}
