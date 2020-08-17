package com.story.storyadmin.dao;

import com.story.storyadmin.domain.entity.sysmgr.SysLog;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * <p>
 * 附件表 Mapper 接口 继承MongoRepository 实现基本的增删该查操作
 * </p>
 *
 * @author sunnj
 * @since 2019-07-12
 */
public interface SysLogDao extends MongoRepository<SysLog, String> {

    //可以自定义方法

}
