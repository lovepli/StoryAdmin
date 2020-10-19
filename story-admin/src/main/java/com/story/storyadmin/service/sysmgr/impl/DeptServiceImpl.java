package com.story.storyadmin.service.sysmgr.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.story.storyadmin.config.shiro.security.UserContext;
import com.story.storyadmin.constant.Constants;
import com.story.storyadmin.constant.enumtype.ResultEnum;
import com.story.storyadmin.domain.entity.sysmgr.Dept;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.domain.vo.sysmgr.DeptNode;
import com.story.storyadmin.mapper.sysmgr.DeptMapper;
import com.story.storyadmin.service.sysmgr.DeptService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author: lipan
 * @date: 2020/7/31
 * @description:
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {

    @Override
    public List<DeptNode> findAll() {
        QueryWrapper<Dept> wrapper= new QueryWrapper<>();
        wrapper.eq("yn_flag","1");
        //排序
        wrapper.orderBy(true,true,"show_order");
        //获取菜单集
        List<Dept> resList = baseMapper.selectList(wrapper);

        //构建获取菜单树结构
        List<DeptNode> treeList = new ArrayList<>();

        DeptNode newNode;
        //遍历菜单集
        for (Dept node : resList) {
            //父ID为0
            if (node.getPid() == 0) {
                //懒加载ResourceNode菜单树节点对象
                newNode = new DeptNode(node.getId(),node.getPid(),node.getName(),node.getDeptDesc());
                newNode.setShowOrder(node.getShowOrder());
                //递归构造得到菜单树结构 TODO
                treeList.add(findChildren(newNode, resList));
            }
        }
        return treeList;
    }

    /**
     * 递归构造树结构
     * @param parentNode 菜单节点对象
     * @param list 菜单对象集合
     * @return
     */
    private DeptNode findChildren(DeptNode parentNode, List<Dept> list) {
        DeptNode newNode;
        for (Dept node : list) {
            if (node.getPid().equals(parentNode.getId())) {
                if (parentNode.getChildren() == null) {
                    parentNode.setChildren(new ArrayList<>());
                }
                newNode = new DeptNode(node.getId(),node.getPid(),node.getName(),node.getDeptDesc());
                newNode.setShowOrder(node.getShowOrder());
                //递归
                parentNode.getChildren().add(findChildren(newNode, list));
            }
        }
        return parentNode;
    }

    /**
     * 通过用户  TODO 部门与用户绑定，待开发。。。
     * @param userId
     * @return
     */
    @Override
    public List<DeptNode> findByUserId(Long userId) {
        return null;
    }

    /**
     * 保存
     * @param dept
     * @return
     */
    @Override
    public Result persist(Dept dept) {
        Date currentDate= Date.from(Instant.now());

        //修改
        if(dept.getId() != null){
            dept.setEditor(UserContext.getCurrentUser().getAccount());
            dept.setModifiedTime(currentDate);
            baseMapper.updateById(dept);
        }else{//添加
            dept.setYnFlag("1");
            dept.setEditor(UserContext.getCurrentUser().getAccount());
            dept.setCreator(UserContext.getCurrentUser().getAccount());
            dept.setCreatedTime(currentDate);
            dept.setModifiedTime(currentDate);
            baseMapper.insert(dept);
        }
        return new Result(true,null,null, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
    }

}
