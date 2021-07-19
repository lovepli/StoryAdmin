package com.story.storyadmin.web.tool;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.story.storyadmin.config.mongo.SysLogAnnotation;
import com.story.storyadmin.config.shiro.security.UserContext;
import com.story.storyadmin.constant.enumtype.ResultEnum;
import com.story.storyadmin.constant.enumtype.YNFlagStatusEnum;
import com.story.storyadmin.domain.entity.tool.Todo;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.service.tool.TodoService;
import com.story.storyadmin.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 待办事项 前端控制器
 * </p>
 *
 * @author sunnj
 * @since 2019-08-14
 */
@Api(description = "待办事项")
@RestController
@RequestMapping("/tool/todo")
public class TodoController extends BaseController {

    @Autowired
    TodoService todoService;

    /**
     * 不分页查询
     * @param todo
     * @return
     */
    @ApiOperation(value = "待办事项" ,  notes="查询待办事项列表")
    @RequiresPermissions("tool.todo.query")
    @RequestMapping(value="/calendar_list",method = {RequestMethod.POST,RequestMethod.GET})
    public Result list(Todo todo){
        Date[] date= this.getMonthInterval(null);

        Result result = new Result();
        QueryWrapper<Todo> eWrapper = new QueryWrapper();
        eWrapper.eq("yn_flag","1");
        if(todo.getStart()==null){
            eWrapper.lt("start",date[1]);
            eWrapper.ge("end",date[0]);
        }else{
            eWrapper.lt("start",todo.getEnd());
            eWrapper.ge("end",todo.getStart());
        }
        List<Todo> list = todoService.list(eWrapper);
        result.setData(list);
        result.setResult(true);
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }

    /**
     * 根据Id查询
     * @param todo
     * @return
     */
    @ApiOperation(value = "待办事项" ,  notes="根据Id查询待办事项")
    @RequiresPermissions("tool.todo.query")
    @RequestMapping(value="/find",method = {RequestMethod.POST})
    public Result findById(@RequestBody Todo todo){
        Todo todoBean= todoService.getById(todo.getId());

        Result result = new Result();
        result.setData(todoBean);
        result.setResult(true);
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }

    /**
     * 保存
     * @param todo
     * @return
     */
    @SysLogAnnotation
    @ApiOperation(value = "待办事项" ,  notes="保存待办事项")
    @RequiresPermissions("tool.todo.save")
    @RequestMapping(value="/save",method = {RequestMethod.POST})
    public Result save(@RequestBody Todo todo){
        Result result = new Result();
        todo.setEditor(UserContext.getCurrentUser().getAccount());
        todo.setModifiedTime(Date.from(Instant.now()));
        if(todo.getId()!=null) {
            todoService.updateById(todo);
        }else{
            todo.setYnFlag(YNFlagStatusEnum.VALID.getCode());
            todoService.save(todo);
        }
        result.setResult(true);
        result.setMessage("保存成功");
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }

    /**
     * 删除
     * @param todo
     * @return
     */
    @SysLogAnnotation
    @ApiOperation(value = "待办事项" ,  notes="删除待办事项")
    @RequiresPermissions("tool.todo.delete")
    @RequestMapping(value="/delete",method = {RequestMethod.POST})
    public Result dropById(@RequestBody Todo todo){
        Result result ;
        if(todo.getId()!=null){
            Todo delTodo= new Todo();
            delTodo.setId(todo.getId());
            delTodo.setYnFlag("0");
            delTodo.setEditor(UserContext.getCurrentUser().getAccount());
            delTodo.setModifiedTime(Date.from(Instant.now()));
            result=new Result(todoService.updateById(delTodo),"删除成功",null,ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        }else{
            result = new Result(false, "删除失败", null , ResultEnum.PARAMETERS_MISSING.getCode());
        }
        return result;
    }

    private Date[] getMonthInterval(Date date){
        //生成日期区间，以自然月汇总
        Calendar calendar = Calendar.getInstance();
        if(date!=null){
            calendar.setTime(date);
        }
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.clear(Calendar.MINUTE);
        calendar.clear(Calendar.SECOND);
        calendar.clear(Calendar.MILLISECOND);

        final Date fromDate = calendar.getTime();

        calendar.add(Calendar.MONTH, 1);
        final Date toDate = calendar.getTime();
        return new Date[]{fromDate,toDate};
    }
}
