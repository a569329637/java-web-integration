package com.souche.dao;

import com.souche.model.Classes;

public interface ClassesMapper {
    int deleteByPrimaryKey(Integer cId);

    int insert(Classes record);

    int insertSelective(Classes record);

    Classes selectByPrimaryKey(Integer cId);

    int updateByPrimaryKeySelective(Classes record);

    int updateByPrimaryKey(Classes record);
}