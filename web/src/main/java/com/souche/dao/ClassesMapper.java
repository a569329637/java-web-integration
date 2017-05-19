package com.souche.dao;

import com.souche.model.Classes;
import com.souche.model.ClassesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClassesMapper {
    int countByExample(ClassesExample example);

    int deleteByExample(ClassesExample example);

    int deleteByPrimaryKey(Integer cId);

    int insert(Classes record);

    int insertSelective(Classes record);

    List<Classes> selectByExample(ClassesExample example);

    Classes selectByPrimaryKey(Integer cId);

    int updateByExampleSelective(@Param("record") Classes record, @Param("example") ClassesExample example);

    int updateByExample(@Param("record") Classes record, @Param("example") ClassesExample example);

    int updateByPrimaryKeySelective(Classes record);

    int updateByPrimaryKey(Classes record);
}