package com.story.storyadmin.domain.entity.validationentity.group.order;

import com.story.storyadmin.domain.entity.validationentity.group.GroupA;
import com.story.storyadmin.domain.entity.validationentity.group.GroupB;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

/**
 * @author: 59688
 * @date: 2021/7/15
 * @description: 组序列
 *
 * 指定组的验证顺序，前面组验证不通过，后面组不验证
 */
// GroupA > GroupB > Default
//@GroupSequence({GroupA.class, GroupB.class, Default.class})
public interface GroupOrder {
}