package com.story.storyadmin.service.sysmgr;

import com.alibaba.fastjson.JSONObject;
import com.story.storyadmin.config.upload.entity.FileSlot;
import com.story.storyadmin.domain.entity.sysmgr.Att;
import com.baomidou.mybatisplus.extension.service.IService;
import com.story.storyadmin.domain.vo.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 附件表 服务类
 * </p>
 *
 * @author sunnj
 * @since 2019-07-12
 */
public interface AttService extends IService<Att> {

    /**
     * 保存文件
     * @param sourceUri 模块路径。 例如: ocn/staff/student/save, <BR>
     * 如果是rest接口， 以直接取request.getRequestURI()的值为最好。
     * @param multipartFile 文件
     * @param fileBatch 实现了FileBatch接口的对象.
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    Att save(String sourceUri, MultipartFile multipartFile, FileSlot fileBatch)
            throws IllegalStateException, IOException;

    /**
     * 保存一个新文件。
     * @param sourceUri 模块存储路径。 例如: ocn/staff/student/save, <BR>
     * 如果是rest接口， 以直接取request.getRequestURI()的值为最好。
     * @param multipartFile 文件
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    Att save(String sourceUri, MultipartFile multipartFile)
            throws IllegalStateException, IOException;

    /**
     * 在指定batchId下， 添加一个新文件。
     * @param sourceUri 模块存储路径。 例如: ocn/staff/student/save, <BR>
     * 如果是rest接口， 以直接取request.getRequestURI()的值为最好。
     * @param multipartFile 文件
     * @param batchId 批次
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    Att save(String sourceUri, MultipartFile multipartFile, String batchId)
            throws IllegalStateException, IOException;

    List<Att> findBySlotId(String slotId);

    List<Att> findBySlotId(String slotId, String category);

    void downloadFile(HttpServletResponse response, Long fileId);

    Result export(JSONObject jsonObject, HttpServletResponse response);

    int uploadFile(MultipartFile file);

    void importExcel(MultipartFile file) throws Exception;

    String findFileInfoDetail(JSONObject jsonObject);

}
