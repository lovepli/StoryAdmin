package com.story.storyadmin.service.sysmgr.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.story.storyadmin.common.exception.CustomException;
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


import com.story.storyadmin.utils.DateMethordUtil.DateUtils;
import com.story.storyadmin.utils.MethodUtil;
import com.story.storyadmin.utils.stringMethordUtil.StringUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Path;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
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

    @Value("${file.multipart.baseDir}")
    private String baseDir;

    @Autowired
    StorageService storageService;

    @Autowired
    AttMapper attMapper;

    /**
     * @param id 主键
     * @return
     */
    @Override
    public boolean removeById(Serializable id) {
        Att delAtt = new Att();
        delAtt.setId((Long) id);
        delAtt.setYnFlag("0");
        if (UserContext.getCurrentUser() != null) {
            delAtt.setEditor(UserContext.getCurrentUser().getAccount());
        }
        delAtt.setModifiedTime(Date.from(Instant.now()));
        return this.updateById(delAtt);
    }

    /**
     * @param slotId 批次
     * @return
     */
    @Override
    public List<Att> findBySlotId(String slotId) {
        return this.findBySlotId(slotId, null);
    }

    /**
     * @param slotId   批次
     * @param category 类别
     * @return
     */
    @Override
    public List<Att> findBySlotId(String slotId, String category) {
        QueryWrapper<Att> attWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(category)) {
            attWrapper.eq("slotId", slotId);
        }
        if (StringUtils.isNotEmpty(category)) {
            attWrapper.eq("file_cate", category);
        }
        attWrapper.eq("yn_flag", "1");
        List<Att> attList = this.list(attWrapper);
        return attList;
    }

    @Override
    public void downloadFile(HttpServletResponse response, Long fileId) {
        Att attachment = attMapper.selectById(fileId);
        if (attachment == null) {
            logger.info("404文件找不到！");
            response.setStatus(404);
            return;
        }
        String filePath = baseDir + attachment.getPath();
        String fileName = attachment.getName();
        logger.info("文件路径:{}", filePath);
        logger.info("文件名:{}", fileName);
        File file = new File(filePath);
        if (file.exists()) {
            // 下载逻辑:
            // 强制设置下载而不是打开
            response.setContentType("application/force-download");
            // 设置文件名，fileName是下载的文件名
            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
            byte[] buffer = new byte[1024];
            try (
                    FileInputStream fis = new FileInputStream(file);
                    BufferedInputStream bis = new BufferedInputStream(fis)
            ) {
                OutputStream outputStream = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    outputStream.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                response.setStatus(404);
                // 业务异常
                //  throw new CustomException("下载失败");
                throw new CustomException(ResultEnum.UNKNOWN_EXCEPTION.getCode(), "下载失败", MethodUtil.getLineInfo());
            }
        } else {
            response.setStatus(404);
        }
    }

    @Transactional
    @Override
    public Result export(JSONObject jsonObject, HttpServletResponse response) {
        // 生成Excel表
        ServletOutputStream outputStream = null;
        SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook();
        try {

            List<Att> attList = attMapper.selectList(null);
            if (CollectionUtils.isNotEmpty(attList)) {
                SXSSFSheet sxssfSheet = sxssfWorkbook.createSheet();
                String tableName = "附件导出数据";
                //第一行表头内容
                String[] headers = new String[]{"序号", "源文件名"};
                SXSSFRow sxssfRow = sxssfSheet.createRow(0);
                SXSSFCell cell = null;
                int headerLength = headers.length;
                for (int i = 0; i < headerLength; i++) {
                    cell = sxssfRow.createCell(i);
                    cell.setCellValue(headers[i]);
                }
                // 数据内容从第二行开始
                int rowNum = 1;
                // 表格数据封装
                int attLength = attList.size();
                for (int j = 0; j < attLength; j++) {
                    SXSSFRow contentRow = sxssfSheet.createRow(rowNum++);
                    cell = contentRow.createCell(0);
                    cell.setCellValue(attList.get(j).getId());
                    cell = contentRow.createCell(1);
                    cell.setCellValue(attList.get(j).getOriginName());
                }
                response.setContentType("application/octet-stream");
                String fileName = "" + System.currentTimeMillis();
                response.setHeader("Content-Disposition", "attachment;fileName=" + fileName + ".xlsx");
                response.setContentType("application/vnd.ms-excel;charset=UTF-8");
                response.setHeader("Pragma", "no-cache");
                response.setHeader("Cache-Control", "no-cache");
                response.setDateHeader("Expires", 0);
                outputStream = response.getOutputStream();
                sxssfWorkbook.write(outputStream);
                return null;
            }
            return new Result(true, "暂无数据", null, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, "附件列表下载异常", null, ResultEnum.EXPORT_EROOR.getCode());
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
                sxssfWorkbook.close();
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("附件下载异常：" + e.getMessage());
            }
        }

    }

    @Override
    public int uploadFile(MultipartFile file) {
        // 根据文件入参获取文件备份到配置好的文件路径
        String fileName = file.getOriginalFilename();
        // 为上传文件路径添加日期子文件（日期为系统日期，便于阅览） TODO 文件目录格式必须为：D:/upload/sysmgr/att/upload/2021/07/20210702100846395_.pdf
        //String timeStr = DateUtils.formatDate(DateUtils.currentDate());
        String timeStr = DateUtils.getDate();
        // 构建上传文件存放的路径
        String targetPath = baseDir + "/" + timeStr;
        // 如果文件不为空，写入上传路径，进行文件上传
        if (!file.isEmpty()) {
            // 获取上传的文件名称，并结合存放的路径，构建新的文件名称
            File filePath = new File(new File(targetPath).getAbsolutePath() + '/', fileName);
            // 判断路径是否存在，不存在则新建一个
            if (!filePath.getParentFile().exists()) {
                filePath.getParentFile().mkdirs();
            }
            // 重新上传前删除旧文件
            //if(filePath.exists()){
            //    filePath.delete();
            //}
            try {
                // 将上传文件保存到目标文件目录
                file.transferTo(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        File uploadFile = new File(targetPath + "/" + fileName);

        String filePathStr = uploadFile.getAbsolutePath();
        int index = filePathStr.lastIndexOf(".");
        String format = filePathStr.substring(index);

        Att att = new Att();
        att.setName(fileName);
        att.setOriginName(fileName);
        att.setPath(filePathStr);
        att.setType(format);
        att.setFileSize(file.getSize());
        att.setYnFlag(YNFlagStatusEnum.VALID.getCode());
        if (UserContext.getCurrentUser() != null) {
            att.setCreator(UserContext.getCurrentUser().getAccount());
        }
        att.setCreatedTime(DateUtils.currentDate());
        int i = attMapper.insert(att);
        return i;

        //ExcelInfo excelInfo = new ExcelInfo();
        //excelInfo.setId(IdUtils.getRandomId());
        //excelInfo.setFileName(fileName);
        //excelInfo.setFilePath(filePathStr);
        //excelInfo.setFileFormat(format);
        //
        //excelInfo.setBusinessId(id); // 业务id
        //excelInfo.setUpdateDate(DateUtils.currentDate());
    }


    @Override
    @Transactional
    public void importExcel(MultipartFile file) throws Exception {
        //根据入参获取Excel文件备份到配置好的文件路径
        File uploadFile = importExcelFile(file);
        List<Att> attList = parseExcelFile(uploadFile);
        // 批量插入到数据库
        new AttServiceImpl().saveBatch(attList);
    }

    /**
     * 解析Excel表格
     *
     * @param uploadFile
     * @param
     * @return
     */
    private List<Att> parseExcelFile(File uploadFile) {
        List<Att> dataList = new ArrayList<>();
        Workbook workbook = null;
        InputStream in = null;

        try {
            in = new FileInputStream(uploadFile);
            // 读取工作簿
            workbook = WorkbookFactory.create(in);
            // 读取工作表
            Sheet sheet = workbook.getSheetAt(0);
            // 获取行数
            int rowNumber = sheet.getPhysicalNumberOfRows();
            // 获取总列数
            int coloumNum = sheet.getRow(0).getPhysicalNumberOfCells();

            if (rowNumber < 2) {
                throw new CustomException("文件吴内容");
            }

            // 上传的Excel如果表头为空就返回提示让重新上传Excel数据
            Row headRow = sheet.getRow(0);
            // 判断上传的表头数据是否正确
            String title1 = headRow.getCell(1).getStringCellValue(); // 源文件名
            String title2 = headRow.getCell(3).getStringCellValue(); // 类型
            String title3 = headRow.getCell(4).getStringCellValue(); // 大小
            String title4 = headRow.getCell(5).getStringCellValue(); // 路径
            if (!title1.contains("源文件名") || !title2.contains("类型") || !title3.contains("大小")|| !title4.contains("路径")) {
                logger.info("上传文件表头内容缺失!");
                throw new CustomException("上传文件表头内容缺失!");
            }

            //将表头内容读取出来
            List<String> headList = new ArrayList<>();
            for (int i = 0; i < coloumNum; i++) {
                headList.add(headRow.getCell(i).getStringCellValue());
            }

            // 循环读取每一行数据封装到实体
            for (int rIndex = 1; rIndex < rowNumber; rIndex++) {
                Row row = sheet.getRow(rIndex);
                // 文件表非空字段
                int index1 = 1; // 源文件名
                int index2 = 3; // 类型
                int index3 = 4; // 大小
                int index4 = 5; // 路径

                // 记录非空字段数据为空的信息
                if (null == row.getCell(index1) || row.getCell(index1).getCellTypeEnum() == CellType.BLANK) {
                    throw new CustomException("第" + rIndex + "行-" + index1 + "列(" + headList.get(index1) + ")值为空！<br/>");
                }
                if (null == row.getCell(index2) || row.getCell(index2).getCellTypeEnum() == CellType.BLANK) {
                    throw new CustomException("第" + rIndex + "行-" + index2 + "列(" + headList.get(index2) + ")值为空！<br/>");
                }
                if (null == row.getCell(index3) || row.getCell(index3).getCellTypeEnum() == CellType.BLANK) {
                    throw new CustomException("第" + rIndex + "行-" + index3 + "列(" + headList.get(index3) + ")值为空！<br/>");
                }
                if (null == row.getCell(index4) || row.getCell(index4).getCellTypeEnum() == CellType.BLANK) {
                    throw new CustomException("第" + rIndex + "行-" + index4 + "列(" + headList.get(index4) + ")值为空！<br/>");
                }

                // 封装数据Bean
                Att att = new Att();
                // 给属性设置
                att.setOriginName(row.getCell(1).getStringCellValue()); // 获取第一列值并赋值给相应属性值
                att.setType(row.getCell(3).getStringCellValue());
               // att.setFileSize(row.getCell(4).getStringCellValue());  // 字符串转long类型
                att.setPath(row.getCell(5).getStringCellValue());
                att.setYnFlag("1");
               // att.setCreatedTime(uploadDate); // 日期字符串转Date
                dataList.add(att);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("数据处理异常：" + e.getMessage());
            throw new CustomException(e.getMessage());
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (workbook != null) {
                    workbook.close();
                }
            } catch (Exception e) {
                logger.error("Close Exception", e.getMessage());
            }
        }
        return dataList;
    }


    /**
     * 根据入参获取Excel文件备份到配置好的文件路径
     *
     * @param file
     * @return
     * @throws Exception
     */
    private File importExcelFile(MultipartFile file) throws Exception {
        // 获取MultipartFile文件各个方法的调用内容
        System.out.println("文件类型ContentType=" + file.getContentType());
        System.out.println("文件组件名称Name=" + file.getName());
        System.out.println("文件原名称OriginalFilename=" + file.getOriginalFilename());
        System.out.println("文件大小Size=" + file.getSize() / 1024 + "KB");

        //  根据文件后缀进行判断文件类型
        String fileName = file.getOriginalFilename();
        String[] split = fileName.split("\\."); //.是特殊字符，需要转义
        if (!"xls".equals(split[1]) && !"xlsx".equals(split[1]) && !"docx".equals(split[1])) {
            throw new CustomException("导入文件不是.xls或者.xlsx格式");
        }

        // 为上传文件路径添加日期子文件（日期为系统日期，便于阅览） TODO 文件目录格式必须为：D:/upload/sysmgr/att/upload/2021/07/20210702100846395_.pdf
        String timeStr = DateUtils.getDate();

        // 构建上传文件存放的路径
        String targetPath = baseDir + "/" + timeStr;
        // 如果文件不为空，写入上传路径，进行文件上传
        if (!file.isEmpty()) {
            // 获取上传的文件名称，并结合存放的路径，构建新的文件名称
            File filePath = new File(new File(targetPath).getAbsolutePath() + '/', fileName);
            // 判断路径是否存在，不存在则新建一个
            if (!filePath.getParentFile().exists()) {
                filePath.getParentFile().mkdirs();
            }
            // 重新上传前删除旧文件
            //if(filePath.exists()){
            //    filePath.delete();
            //}
            try {
                // 将上传文件保存到目标文件目录
                file.transferTo(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 返回上传的文件路径
        return new File(targetPath + "/" + fileName);
    }

    @Override
    public Att save(String sourceUri, MultipartFile multipartFile) throws IllegalStateException, IOException {
        return this.save(sourceUri, multipartFile, FileSlot.newSlotId());
    }

    @Override
    public Att save(String sourceUri, MultipartFile multipartFile, String batchId)
            throws IllegalStateException, IOException {

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
            throws IllegalStateException, IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        Att fileEntity = new Att();
        int extendNameIndex = originalFilename.indexOf(".");
        if (extendNameIndex >= 0) {
            fileEntity.setType(originalFilename.substring(extendNameIndex).toLowerCase());
        }
        fileEntity.setOriginName(originalFilename);
        fileEntity.setFileSize(multipartFile.getSize());
        fileEntity.setSlotId(fileSlot.getSlotId());
        fileEntity.setYnFlag(YNFlagStatusEnum.VALID.getCode());
        if (UserContext.getCurrentUser() != null) {
            fileEntity.setCreator(UserContext.getCurrentUser().getAccount());
            //fileEntity.setEditor(fileEntity.getCreator());
        }
        fileEntity.setCreatedTime(DateUtils.currentDate());
        //fileEntity.setModifiedTime(fileEntity.getCreatedTime());
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

    /**
     * 在线展示员工上传的照片或pdf信息
     *
     * @param jsonObject
     * @return
     */
    @Override
    public String findFileInfoDetail(JSONObject jsonObject) {
        // 获取请求参数
        String attId = jsonObject.getString("attId");

        if (org.springframework.util.StringUtils.isEmpty(attId)) {
            throw new CustomException(ResultEnum.PARAMETERS_MISSING.getCode(), "前端请求参数userId不能为空");
        }
        // 查找
        QueryWrapper<Att> condition = new QueryWrapper<>();
        condition.eq("id", attId);
        Att user = baseMapper.selectOne(condition);
        if (user == null) {
            return null;
        }


        List<String> pathList = new ArrayList<String>();
        if (!org.springframework.util.StringUtils.isEmpty(user.getPath())) {
            pathList.addAll(Arrays.asList(user.getPath().split(";")));
        }
        //if(!StringUtils.isEmpty(crjRecord.getPdfPath())){
        //    pathList.addAll(Arrays.asList(crjRecord.getPdfPath().split(";")));
        //}

        //将图片和pdf转base64格式
        List<String> base64ImgList = new ArrayList<String>();
        BASE64Encoder encoder = new BASE64Encoder();
        try {
            for (int i = 0; i < pathList.size(); i++) {
                String fileType = pathList.get(i).split("\\.")[1];
                InputStream in = new FileInputStream(pathList.get(i));
                byte[] data = new byte[in.available()];
                in.read(data);
                in.close();
                String base64str = encoder.encode(data);
                String img = null;
                // 文件格式只考虑 pdf 和 一般的图片(jpg/jpeg/png等)
                if (fileType.equals("pdf")) {
                    img = "data:application/" + fileType + ";base64," + base64str;
                } else {
                    img = "data:image/" + fileType + ";base64," + base64str;
                }
                base64ImgList.add(img);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        StringBuilder result = new StringBuilder("");
        for (int i = 0; i < base64ImgList.size(); i++) {
            result.append(base64ImgList.get(i)).append("&&&");
        }
        return result.toString();
    }
}
