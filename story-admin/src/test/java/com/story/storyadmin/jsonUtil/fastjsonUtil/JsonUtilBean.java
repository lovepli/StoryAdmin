package com.story.storyadmin.jsonUtil.fastjsonUtil;

import cn.hutool.json.JSONUtil;


import lombok.Data;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: lipan
 * @date: 2020/5/19
 * @description:
 */
public class JsonUtilBean {

    @Data
    static class Menu implements Serializable {
        private static final long serialVersionUID = 5561561457068906366L;

        private Integer menuId;

        private Integer parentId;

        private String menuName;

        private String url;

        private List<Menu> children;

        public Menu(){}

        public Menu(Integer menuId, Integer parentId, String menuName) {
            this.menuId = menuId;
            this.parentId = parentId;
            this.menuName = menuName;
        }
    }

    public  static  void fun(){
        List<Menu> menuList = new ArrayList<>();
        menuList.add(new Menu(1, null, "节点1"));
        menuList.add(new Menu(2, null, "节点2"));
        menuList.add(new Menu(3, 1, "节点1.1"));
        menuList.add(new Menu(4, 1, "节点1.2"));
        menuList.add(new Menu(5, 3, "节点1.1.1"));
        //菜单集合为：
        //这里使用了工具类将对象转换为json字符串
        System.out.println(JSONUtil.toJsonStr(menuList));
//        //这里的JSONutil.toJsonStr();是用的github上的一个工具类，也可以用fastjson工具类
//        JSONObject returnValue = new JSONObject();
//        returnValue.put("menuList", "hello world");
//        System.out.println(JSON.toJSONString(returnValue));
    }

  public static void main(String[] args) {
    fun();
  }
}
