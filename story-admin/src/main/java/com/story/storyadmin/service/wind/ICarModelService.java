package com.story.storyadmin.service.wind;

import com.baomidou.mybatisplus.extension.service.IService;
import com.story.storyadmin.domain.entity.wind.CarModel;

import java.util.List;

/**
 * @author jwcg
 * @version V1.0
 * @Title:
 * @Description:
 * @date 2017-02-09 09:05:29
 */
public interface ICarModelService extends IService<CarModel> {
    List<CarModel> selectDictList();
}
