package com.story.storyadmin.validation.exampledemo2.service;


import com.story.storyadmin.validation.exampledemo2.dto.UserDTO;
import com.story.storyadmin.validation.exampledemo2.pojo.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @项目名称：springboot @包名：com.zhliang.springboot.validator.service @类描述： @创建人：colin @创建时间：2019/12/24
 * 20:22
 *
 * @version：V1.0
 */
//@Service
public class UserService {

  // TODO 这个是个容器，模拟数据库
  private static final Map<Long, User> data = new HashMap<Long, User>();

  private Long num = 0L;

  public void updateById(UserDTO userDTO) {
    Long userId = userDTO.getUserId();
    User user = selectById(userId);
    BeanUtils.copyProperties(userDTO, user);
    data.put(userId, user);
  }

  public User selectById(Long userId) {
      //从内存中查出来
    return data.get(userId);
  }

  public void save(UserDTO userDTO) {
      //模拟id自增长
    Long userId = ++num;
    User user = new User();
    // 对象拷贝
    BeanUtils.copyProperties(userDTO, user);
    user.setUserId(userId);
    //存入内存"数据库"
    data.put(userId, user);
  }
}
