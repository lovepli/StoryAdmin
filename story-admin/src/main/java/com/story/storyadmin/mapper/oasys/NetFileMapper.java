package com.story.storyadmin.mapper.oasys;

import com.story.storyadmin.domain.entity.oasys.NetFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NetFileMapper {
    List<NetFile> selectNetFiles( @Param("userId") Long userId,@Param("parentId") Long parentId, @Param("personal") boolean personal);

    NetFile selectNetFile(@Param("id") Long id);

    void insertNetFile(NetFile netFile);

    void updateNetFile(@Param("id") Long id, @Param("newName") String newName);

    void deleteNetFiles(@Param("ids") Long[] ids);
}