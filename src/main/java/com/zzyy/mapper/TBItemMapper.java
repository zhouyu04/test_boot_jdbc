package com.zzyy.mapper;

import com.zzyy.entity.TBItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TBItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TBItem record);

    TBItem selectByPrimaryKey(Long id);

    List<TBItem> selectAll();

    int updateByPrimaryKey(TBItem record);
}