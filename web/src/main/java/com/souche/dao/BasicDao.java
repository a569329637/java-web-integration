package com.souche.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author guishangquan
 * @Description
 * @Package com.souche.dao
 * @date 17/5/19
 **/
public interface BasicDao {
    List<Map<String, Object>> select(@Param("sql") String sql);

    List<Map<String, Object>> update(@Param("sql") String sql);
}
