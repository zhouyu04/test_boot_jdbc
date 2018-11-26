package com.zzyy.mapper;

import com.zzyy.entity.BootLog;
import java.util.List;

public interface BootLogMapper {
    int deleteByPrimaryKey(Long logId);

    int insert(BootLog record);

    BootLog selectByPrimaryKey(Long logId);

    List<BootLog> selectAll();

    int updateByPrimaryKey(BootLog record);
}