package com.story.storyadmin.web.wind.twotable;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.story.storyadmin.config.shiro.security.UserContext;
import com.story.storyadmin.constant.enumtype.ResultEnum;
import com.story.storyadmin.domain.entity.wind.CarModel;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.domain.vo.wind.CarModelDto;
import com.story.storyadmin.service.wind.ICarModelService;
import com.story.storyadmin.utils.wind.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;

/**
 * All rights Reserved, Designed By www.sunseagear.com
 *
 * @version V1.0
 * @package com.sunseagear.wind.modules.sys.controller
 * @title: 消息模版控制器
 * @description: 消息模版控制器 * @date: 2018-09-03 15:10:10
 * @copyright: 2018 www.sunseagear.com Inc. All rights reserved.
 */

@RestController
@RequestMapping("/sysmgr/carmodel")
public class CarModelController{

    private static final Logger logger = LoggerFactory.getLogger(CarModelController.class);

    @Autowired
    private ICarModelService carModelService;


    /**
     * 根据页码和每页记录数，以及查询条件动态加载数据
     *
     * @param
     * @throws IOException
     */
    @RequiresPermissions("sysmgr.car.carmodel.query")
    @RequestMapping(value="/list",method = {RequestMethod.POST,RequestMethod.GET})
    public Result list(CarModelDto carModelDto,
                       @RequestParam(defaultValue = "1")int pageNo,
                       @RequestParam(defaultValue = "10")int limit) throws IOException {
        Result result = new Result();
        Page<CarModel> page = new Page(pageNo, limit);

        //加入条件
        QueryWrapper<CarModel> entityWrapper = new QueryWrapper<>();
        entityWrapper.eq("del_flag", "0");
        entityWrapper.orderByAsc( "sort");
        String keyword = carModelDto.getKeyword();
        String carId = carModelDto.getCarId();
        if (!StringUtils.isEmpty(carId) && !StringUtils.isEmpty(keyword)) {
            entityWrapper.eq("car_id", carId).and(i -> i.like("label", keyword).or().like("value", keyword));
        } else if (!StringUtils.isEmpty(carId)) {
            entityWrapper.eq("car_id", carId);
        }
        // 预处理
        IPage<CarModel> list = carModelService.page(page, entityWrapper);
        logger.info("查询出字典信息:{}",list);
        result.setData(list);
        result.setResult(true);
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }


    @RequiresPermissions("sysmgr.car.carmodel.save")
    @RequestMapping(value="/save",method = {RequestMethod.POST})
    public Result save(@RequestBody CarModel carModel){
        Result result ;
        if(carModel.getId()!= null){
            carModel.setUpdateDate(Date.from(Instant.now()));
            carModel.setUpdateBy(UserContext.getCurrentUser().getAccount());
            carModelService.updateById(carModel);
            result= new Result(true, "修改成功", carModel, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        }else{//添加
            //标志为有效
            carModel.setDelFlag("0");
            //添加时间
            carModel.setCreateDate(Date.from(Instant.now()));
            carModel.setCreateBy(UserContext.getCurrentUser().getAccount());
            carModelService.save(carModel);
            result= new Result(true, "添加成功", carModel, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        }
        return result;
    }


    @RequiresPermissions("sysmgr.car.carmodel.delete")
    @RequestMapping(value="/delete",method = {RequestMethod.POST})
    public Result dropById(@RequestBody CarModel carModel){
        Result result ;
        if(carModel.getId()!=null){
            CarModel delCarModel= new CarModel();
            delCarModel.setId(carModel.getId());
            delCarModel.setDelFlag("1");
            delCarModel.setUpdateDate(Date.from(Instant.now()));
            delCarModel.setUpdateBy(UserContext.getCurrentUser().getAccount());
            result=new Result(carModelService.updateById(delCarModel),"删除成功",null,ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        }else{
            result = new Result(false, "删除失败", null , ResultEnum.PARAMETERS_MISSING.getCode());
        }
        return result;
    }

    @RequiresPermissions("sysmgr.car.carmodel.delete")
    @PostMapping("/batch/delete")
    public Result dropByIds(@RequestParam("ids") Long[] ids){
        Result result ;
        // 删除数组集合，直接删除数据库中的数据
        // dictService.deleteBatchIds(ids);
        CarModel delCarModel= null;
        // 遍历删除
        if(ids!=null || ids.length >0){
            for (Long id:ids){
                delCarModel= new CarModel();
                delCarModel.setId(id);
                delCarModel.setDelFlag("1");
                delCarModel.setUpdateDate(Date.from(Instant.now()));
                delCarModel.setUpdateBy(UserContext.getCurrentUser().getAccount());
                carModelService.updateById(delCarModel);
            }
            result=new Result(true,"批量删除成功",null,ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        }else{
            result = new Result(false, "批量删除失败", null ,ResultEnum.PARAMETERS_MISSING.getCode());
        }
        return result;
    }

}
