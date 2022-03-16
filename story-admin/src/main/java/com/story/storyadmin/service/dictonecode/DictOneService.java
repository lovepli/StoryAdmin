package com.story.storyadmin.service.dictonecode;


import com.story.storyadmin.domain.entity.dictonecode.DictOneDO;
import com.story.storyadmin.utils.dictonecode.R;
import org.springframework.web.multipart.MultipartFile;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author hanxinghua
 * @create 2019/12/28
 * @since 1.0.0
 */
public interface DictOneService {



    int save(DictOneDO dict);

    R importExcel(MultipartFile file);


}
