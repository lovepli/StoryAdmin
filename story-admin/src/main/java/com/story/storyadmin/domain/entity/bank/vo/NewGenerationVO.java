package com.story.storyadmin.domain.entity.bank.vo;

import com.story.storyadmin.domain.entity.bank.ExcelInfo;
import com.story.storyadmin.domain.entity.bank.NewGeneration;
import lombok.Data;

import java.util.List;

/**
 * @author: 59688
 * @date: 2021/5/24
 * @description: 新建VO对象，增加新的属性excelInfos
 * 根据关联查询，返回NewGenerationVO对象，每条数据包含多条附件信息（一对多关联关系）
 */
@Data
public class NewGenerationVO extends NewGeneration {

    //附件集合
    private List<ExcelInfo> excelInfos;
}
