package com.story.storyadmin.service.sysmgr.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.story.storyadmin.config.shiro.security.UserContext;
import com.story.storyadmin.config.upload.entity.CategorialFileSlot;
import com.story.storyadmin.config.upload.entity.FileSlot;
import com.story.storyadmin.constant.enumtype.ResultEnum;
import com.story.storyadmin.constant.enumtype.YNFlagStatusEnum;
import com.story.storyadmin.domain.entity.sysmgr.Att;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.mapper.sysmgr.AttMapper;
import com.story.storyadmin.service.common.StorageService;
import com.story.storyadmin.service.sysmgr.AttService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.story.storyadmin.utils.DateUtils;
import com.story.storyadmin.utils.StringUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Path;
import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 附件表 服务实现类
 * </p>
 *
 * @author sunnj
 * @since 2019-07-12
 */
@Service
public class AttServiceImpl extends ServiceImpl<AttMapper, Att> implements AttService {

    private static Logger logger = LoggerFactory.getLogger(AttServiceImpl.class);

    @Autowired
    StorageService storageService;

    @Autowired
    AttMapper attMapper;

    /**
     *
     * @param id 主键
     * @return
     */
    @Override
    public boolean removeById(Serializable id){
        Att delAtt= new Att();
        delAtt.setId((Long)id);
        delAtt.setYnFlag("0");
        if(UserContext.getCurrentUser()!=null) {
            delAtt.setEditor(UserContext.getCurrentUser().getAccount());
        }
        delAtt.setModifiedTime(Date.from(Instant.now()));
        return this.updateById(delAtt);
    }

    /**
     *
     * @param slotId 批次
     * @return
     */
    @Override
    public List<Att> findBySlotId(String slotId) {
        return this.findBySlotId(slotId, null);
    }

    /**
     *
     * @param slotId 批次
     * @param category 类别
     * @return
     */
    @Override
    public List<Att> findBySlotId(String slotId, String category) {
        QueryWrapper<Att> attWrapper= new QueryWrapper<>();
        if (StringUtils.isNotEmpty(category)) {
            attWrapper.eq("slotId",slotId);
        }
        if (StringUtils.isNotEmpty(category)) {
            attWrapper.eq("file_cate",category);
        }
        attWrapper.eq("yn_flag","1");
        List<Att> attList= this.list(attWrapper);
        return attList;
    }

    @Transactional
    @Override
    public Result export(JSONObject jsonObject, HttpServletResponse response) {
        // 生成Excel表
        ServletOutputStream outputStream = null;
        SXSSFWorkbook sxssfWorkbook =new SXSSFWorkbook();
        try {

            List<Att> attList = attMapper.selectList(null);
            if(CollectionUtils.isNotEmpty(attList)){
                SXSSFSheet sxssfSheet =sxssfWorkbook.createSheet();
                String tableName="附件导出数据";
                //第一行表头内容
                String[] headers = new String[]{"序号","源文件名"};
                SXSSFRow sxssfRow=sxssfSheet.createRow(0);
                SXSSFCell cell =null;
                int headerLength =headers.length;
                for(int i=0;i<headerLength;i++){
                    cell = sxssfRow.createCell(i);
                    cell.setCellValue(headers[i]);
                }
                // 数据内容从第二行开始
                int rowNum=1;
                // 表格数据封装
                int attLength =attList.size();
                for (int j=0;j<attLength;j++){
                    SXSSFRow contentRow = sxssfSheet.createRow(rowNum++);
                    cell=contentRow.createCell(0);
                    cell.setCellValue(attList.get(j).getId());
                    cell=contentRow.createCell(1);
                    cell.setCellValue(attList.get(j).getOriginName());
                }
                response.setContentType("application/octet-stream");
                String fileName= ""+System.currentTimeMillis();
                response.setHeader("Content-disposition","attachment;fileName="+fileName+".xlsx");
                response.setContentType("application/vnd.ms-excel;charset=UTF-8");
                response.setHeader("Pragma","no-cache");
                response.setHeader("Cache-Control","no-cache");
                response.setDateHeader("Expires",0);
                outputStream = response.getOutputStream();
                sxssfWorkbook.write(outputStream);
                return  null;
            }
            return new Result(true,"暂无数据",null, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        }catch (IOException e){
            e.printStackTrace();
            return  new Result(false,"附件列表下载异常",null, ResultEnum.EXPORT_EROOR.getCode());
        }finally {
            try {
                if (outputStream !=null){
                    outputStream.flush();
                    outputStream.close();
                }
                sxssfWorkbook.close();
            }catch (IOException e){
                e.printStackTrace();
                logger.error("附件下载异常："+e.getMessage());
            }
        }

    }

    @Override
    public Att save(String sourceUri, MultipartFile multipartFile) throws IllegalStateException, IOException {
        return this.save(sourceUri, multipartFile, FileSlot.newSlotId());
    }

    @Override
    public Att save(String sourceUri, MultipartFile multipartFile, String batchId)
            throws IllegalStateException,IOException {

        // 返回默认字符串
        final String localSlotId = StringUtils.defaultString(batchId, FileSlot.newSlotId());

        return this.save(sourceUri, multipartFile, new FileSlot() {

            @Override
            public String getSlotId() {
                return localSlotId;
            }

            @Override
            public void setSlotId(String batchId) {
                // TODO Auto-generated method stub

            }

            @Override
            public List<Att> getSlotFiles() {
                // TODO Auto-generated method stub
                return null;
            }
        });
    }

    @Override
    public Att save(String sourceUri, MultipartFile multipartFile, FileSlot fileSlot)
            throws IllegalStateException,IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        Att fileEntity = new Att();
        int extendNameIndex=originalFilename.indexOf(".");
        if(extendNameIndex>=0){
            fileEntity.setType(originalFilename.substring(extendNameIndex).toLowerCase());
        }
        fileEntity.setOriginName(originalFilename);
        fileEntity.setFileSize(multipartFile.getSize());
        fileEntity.setSlotId(fileSlot.getSlotId());
        fileEntity.setYnFlag(YNFlagStatusEnum.VALID.getCode());
        if (UserContext.getCurrentUser() != null) {
            fileEntity.setCreator(UserContext.getCurrentUser().getAccount());
            fileEntity.setEditor(fileEntity.getCreator());
        }
        fileEntity.setCreatedTime(DateUtils.currentDate());
        fileEntity.setModifiedTime(fileEntity.getCreatedTime());
        if (fileSlot instanceof CategorialFileSlot) {
            fileEntity.setFileCate(((CategorialFileSlot) fileSlot).getSlotFileCategoryOnUploading());
        }

        // 保存文件到服务器
        Path path = storageService.store(sourceUri, multipartFile);
        fileEntity.setPath(path.toString());

        // 保存数据到数据库
        this.save(fileEntity);
        return fileEntity;
    }
}
