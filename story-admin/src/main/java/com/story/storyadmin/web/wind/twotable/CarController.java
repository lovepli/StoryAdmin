package com.story.storyadmin.web.wind.twotable;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.story.storyadmin.config.shiro.security.UserContext;
import com.story.storyadmin.constant.enumtype.ResultEnum;
import com.story.storyadmin.domain.entity.wind.Car;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.domain.vo.wind.CarDto;
import com.story.storyadmin.service.wind.ICarService;
import com.story.storyadmin.utils.wind.StringUtils;
import com.story.storyadmin.web.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;

@RestController
@RequestMapping("/test/car")
public class CarController extends BaseController {


    @Autowired
    private ICarService carService;


    /**
     * 根据页码和每页记录数，以及查询条件动态加载数据
     *
     * @param
     * @throws IOException
     */
    @RequiresPermissions("test.car.query")
    @RequestMapping(value="/list",method = {RequestMethod.POST,RequestMethod.GET})
    public Result list(CarDto carDto,
                       @RequestParam(defaultValue = "1")int pageNo,
                       @RequestParam(defaultValue = "10")int limit) throws IOException {
        //加入条件
        Result result = new Result();
        Page<Car> page = new Page(pageNo, limit);
        //加入条件
        QueryWrapper<Car> entityWrapper = new QueryWrapper<>();
        entityWrapper.eq("del_flag", "0");
        entityWrapper.orderByDesc( "create_date");
        String keyword = carDto.getKeyword();
        if (!StringUtils.isEmpty(keyword)) {
            entityWrapper.like("name", keyword).or().like("code", keyword);
        }
        // 预处理
        IPage<Car> list = carService.page(page, entityWrapper);
        logger.info("查询出字典信息:{}",list);
        result.setData(list);
        result.setResult(true);
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }
    @RequiresPermissions("test.car.save")
    @RequestMapping(value="/save",method = {RequestMethod.POST})
    public Result save(@RequestBody Car car){
        Result result ;
        if(car.getId()!= null){
            car.setUpdateDate(Date.from(Instant.now()));
            car.setUpdateBy(UserContext.getCurrentUser().getAccount());
            carService.updateById(car);
            result= new Result(true, "修改成功", car, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        }else{//添加
            //标志为有效
            car.setDelFlag("0");
            //添加时间
            car.setCreateDate(Date.from(Instant.now()));
            car.setCreateBy(UserContext.getCurrentUser().getAccount());
            carService.save(car);
            result= new Result(true, "添加成功", car, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        }
        return result;
    }

    @RequiresPermissions("test.car.query")
    @RequestMapping(value="/find",method = {RequestMethod.POST})
    public Result findById(@RequestBody Car car){ //RequestParam LONG id
        Car treeAndTable2 = carService.getById(car.getId());
        Result result = new Result();
        result.setData(treeAndTable2);
        result.setResult(true);
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }


    @RequiresPermissions("test.car.delete")
    @RequestMapping(value="/delete",method = {RequestMethod.POST})
    public Result dropById(@RequestBody Car car){
        Result result ;
        if(car.getId()!=null){
            Car delCar= new Car();
            delCar.setId(car.getId());
            delCar.setDelFlag("1");
            delCar.setUpdateDate(Date.from(Instant.now()));
            delCar.setUpdateBy(UserContext.getCurrentUser().getAccount());
            result=new Result(carService.updateById(delCar),"删除成功",null,ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        }else{
            result = new Result(false, "删除失败", null , ResultEnum.PARAMETERS_MISSING.getCode());
        }
        return result;
    }
}
