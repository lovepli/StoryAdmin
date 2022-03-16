package com.story.storyadmin.设计模式.原型模式;


/**
 * @author: lipan
 * @date: 2021/8/23
 * @description: 此时，从数据库里查出来的UserEntity需要转换成UserVO，然后再返回给前端（或者调用方）。
 * 从这个util类中，我们可以看出，如果一个类的属性有几十个，上百个的，这代码量是不是有点恐怖？
 *
 * 于是，我们通常都会使用一些工具类来处理，比如常见有以下：
 *
 * BeanUtils.copy();
 * JSON.parseObject()
 * Guava工具类
 * .....
 * 这些工具类就用到了原型模式。
 *
 * 通过一个对象，创建一个新的对象。
 *
 * 也把原型模式称之为对象的拷贝、克隆。
 *
 * 其实对象的克隆分浅克隆和深克隆，下面我们就来聊聊浅克隆和深克隆。
 *
 * 浅克隆：创建一个新对象，新对象的属性和原来对象完全相同，对于非基本类型属性，仍指向原来对象的属性所指向的对象的内存地址。
 * 深克隆：创建一个新对象，属性中引用的其他对象也会被克隆，不再指向原有对象地址。
 * 我们先来聊聊浅克隆，都喜欢由浅入深。
 */

public class ObjectConvertUtil {

    public static UserVO convertUserEntityToUserVO(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        UserVO userVo = new UserVO();

        userVo.setId(userEntity.getId());
        userVo.setName(userEntity.getName());
        userVo.setAge(userEntity.getAge());
        //如果还有更多属性呢？
        return userVo;
    }
}
