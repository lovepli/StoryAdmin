package com.story.storyadmin.web.sysmgr.ruoyi;
import com.story.storyadmin.config.shiro.security.UserContext;
import com.story.storyadmin.constant.sysmgr.ruoyi.UserConstants;
import com.story.storyadmin.constant.sysmgr.ruoyi.page.TableDataInfo;
import com.story.storyadmin.ruoyidomain.AjaxResult;
import com.story.storyadmin.ruoyidomain.entity.SysPost;
import com.story.storyadmin.service.sysmgr.ruoyi.ISysPostService;
import com.story.storyadmin.web.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 岗位信息操作处理
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/sysmgr/post")
public class SysPostController extends BaseController {
    @Autowired
    private ISysPostService postService;

    /**
     * 获取岗位列表 分页显示
     */
    @RequiresPermissions("sysmgr.post.list")
    @GetMapping("/list")
    public TableDataInfo list(SysPost post) {
        startPage();
        List<SysPost> list = postService.selectPostList(post);
        return getDataTable(list);
    }

//    @RequiresPermissions("sysmgr.post.export")
//    @GetMapping("/export")
//    public AjaxResult export(SysPost post)
//    {
//        List<SysPost> list = postService.selectPostList(post);
//        ExcelUtil<SysPost> util = new ExcelUtil<SysPost>(SysPost.class);
//        return util.exportExcel(list, "岗位数据");
//    }

    /**
     * 根据岗位编号获取详细信息
     */
    @RequiresPermissions("sysmgr.post.query")
    @GetMapping(value = "/{postId}")
    public AjaxResult getInfo(@PathVariable Long postId) {
        return AjaxResult.success(postService.selectPostById(postId));
    }



    /**
     * 新增岗位
     */
    @RequiresPermissions("sysmgr.post.add")
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysPost post) {
        if (UserConstants.NOT_UNIQUE.equals(postService.checkPostNameUnique(post))) {
            return AjaxResult.error("新增岗位'" + post.getPostName() + "'失败，岗位名称已存在");
        } else if (UserConstants.NOT_UNIQUE.equals(postService.checkPostCodeUnique(post))) {
            return AjaxResult.error("新增岗位'" + post.getPostName() + "'失败，岗位编码已存在");
        }
        post.setCreateBy(UserContext.getCurrentUser().getAccount());
        return toAjax(postService.insertPost(post));
    }

    /**
     * 修改岗位
     */
    @RequiresPermissions("sysmgr.post.edit")
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysPost post) {
        if (UserConstants.NOT_UNIQUE.equals(postService.checkPostNameUnique(post))) {
            return AjaxResult.error("修改岗位'" + post.getPostName() + "'失败，岗位名称已存在");
        } else if (UserConstants.NOT_UNIQUE.equals(postService.checkPostCodeUnique(post))) {
            return AjaxResult.error("修改岗位'" + post.getPostName() + "'失败，岗位编码已存在");
        }
        post.setUpdateBy(UserContext.getCurrentUser().getAccount());
        return toAjax(postService.updatePost(post));
    }

    /**
     * 删除岗位
     */
    @RequiresPermissions("sysmgr.post.remove")
    @DeleteMapping("/{postIds}")
    public AjaxResult remove(@PathVariable Long[] postIds) {
        return toAjax(postService.deletePostByIds(postIds));
    }

    /**
     * 获取岗位选择框列表
     */
    @GetMapping("/optionselect")
    @RequiresPermissions("sysmgr.post.list")
    public AjaxResult optionselect() {
        List<SysPost> posts = postService.selectPostAll();
        return AjaxResult.success(posts);
    }
}
