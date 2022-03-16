> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [mp.weixin.qq.com](https://mp.weixin.qq.com/s/0e5BJxfaDo1_fyCN67AWWw)

#### 背景

实际开发过程中经常需要查询节点树, 根据指定节点获取子节点列表, 以下记录了获取节点树的操作, 以备不时之需。

#### 使用场景

可以用于系统部门组织机构、商品分类、城市关系等带有层级关系的数据结构；

#### 设计思路

##### 递归模型

即根节点、枝干节点、叶子节点，数据模型如下：

<table><thead><tr><th>id</th><th>code</th><th>name</th><th>parent_code</th></tr></thead><tbody><tr><td>1</td><td>10000</td><td>电脑</td><td>0</td></tr><tr><td>2</td><td>20000</td><td>手机</td><td>0</td></tr><tr><td>3</td><td>10001</td><td>联想笔记本</td><td>10000</td></tr><tr><td>4</td><td>10002</td><td>惠普笔记本</td><td>10000</td></tr><tr><td>5</td><td>1000101</td><td>联想拯救者</td><td>10001</td></tr><tr><td>6</td><td>1000102</td><td>联想小新系列</td><td>10001</td></tr></tbody></table>

#### 实现代码

##### 表结构

```
CREATE TABLE `tree_table` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `code` varchar(10) NOT NULL COMMENT '编码',
  `name` varchar(20) NOT NULL COMMENT '名称',
  `parent_code` varchar(10)  NOT NULL COMMENT '父级编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='树形结构测试表';
复制代码
```

##### 表数据

```
INSERT INTO `tree_table`(`code`, `name`, `parent_code`) VALUES ('10000', '电脑', '0');
INSERT INTO `tree_table`(`code`, `name`, `parent_code`) VALUES ('10001', '联想笔记本', '10000');
INSERT INTO `tree_table`(`code`, `name`, `parent_code`) VALUES ('10002', '惠普笔记本', '10000');
INSERT INTO `tree_table`(`code`, `name`, `parent_code`) VALUES ('1000101', '联想拯救者', '10001');
INSERT INTO `tree_table`(`code`, `name`, `parent_code`) VALUES ('1000102', '联想小新系列', '10001');
复制代码
```

##### 实体

```
@Data
@TableName("tree_table")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TreeTable {

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 编码
     */
    private String code;
    /**
     * 名称
     */
    private String name;
    /**
     * 父级编码
     */
    private String parentCode;

    /**
     * 子节点
     */
    @TableField(exist = false)
    private List<TreeTable> childNode;
}
复制代码
```

##### mybatis

###### mapper

```
public interface TreeTableMapper extends BaseMapper<TreeTable> {
    /**
     * 获取树形结构数据
     *
     * @return 树形结构
     */
    public List<TreeTable> noteTree();
}
复制代码
```

###### xml

```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.springboot.example.mysqltree.mapper.TreeTableMapper">
    <resultMap type="com.springboot.example.mysqltree.model.entity.TreeTable">
        <result column="id" property="id"/>
        <result column="code" property="code"/>
        <result column="name" property="name"/>
        <result column="parent_code" property="parentCode"/>
    </resultMap>
    <resultMap type="com.springboot.example.mysqltree.model.entity.TreeTable"
               extends="BaseResultMap">
        <collection property="childNode" column="code" ofType="com.springboot.example.mysqltree.model.entity.TreeTable"
                    javaType="java.util.ArrayList" select="nextNoteTree">

        </collection>
    </resultMap>

    <sql>
                id,
                code,
                `name`,
                parent_code
    </sql>
   
    <select resultMap="NodeTreeResult">
        select
        <include refid="Base_Column_List"/>
        from tree_table
        where parent_code=#{code}
    </select>
    <select resultMap="NodeTreeResult">
        select
        <include refid="Base_Column_List"/>
        from tree_table
        where parent_code='0'
    </select>
</mapper>
复制代码
```

> noteTree ：获取所有父级节点数据；
>
> nextNoteTree：循环获取子节点数据，知道叶子节点结束；
>
> column：关联表的列名；
>
> ofType：返回类型

##### 启动类

```
@Slf4j
@Component
public class TreeTableCommandLineRunner implements CommandLineRunner {
    @Resource
    private TreeTableMapper treeTableMapper;

    @Override
    public void run(String... args) throws Exception {
        log.info(JSONUtil.toJsonPrettyStr(treeTableMapper.noteTree()));
    }
}
复制代码
```

#### 最终效果

```
[
    {
        "code": "10000",
        "childNode": [
            {
                "code": "10001",
                "childNode": [
                    {
                        "code": "1000101",
                        "childNode": [
                        ],
                        "parentCode": "10001",
                        "name": "联想拯救者",
                        "id": 5
                    },
                    {
                        "code": "1000102",
                        "childNode": [
                        ],
                        "parentCode": "10001",
                        "name": "联想小新系列",
                        "id": 6
                    }
                ],
                "parentCode": "10000",
                "name": "联想笔记本",
                "id": 3
            },
            {
                "code": "10002",
                "childNode": [
                ],
                "parentCode": "10000",
                "name": "惠普笔记本",
                "id": 4
            }
        ],
        "parentCode": "0",
        "name": "电脑",
        "id": 1
    }
]
复制代码
```

#### 注意事项

使用`mybatis`时如加载不到`mapper xml`需在`pom.xml`添加以下配置：

```
<resources>
    <resource>
        <directory>src/main/resources</directory>
        <filtering>true</filtering>
    </resource>
    <resource>
        <directory>src/main/java</directory>
        <includes>
            <include>**/*.xml</include>
        </includes>
    </resource>
</resources>
复制代码
```

#### 总结

使用递归方式是比较常见的方式，优点是实现简单，直观的体现层级关系，但是数据量大的情况下效率会略低；欢迎使用其他方式的小伙伴分享自己的实现思路。

文章分类

> 作者：Leaf1993  
> 链接：https://juejin.cn/post/6987283625511223304  
> 来源：掘金  
> 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。