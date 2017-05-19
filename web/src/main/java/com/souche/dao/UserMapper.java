package com.souche.dao;

import com.souche.model.User;
import com.souche.model.UserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    /**
     * 根据条件查询数量
     * @param example
     * @return
     */
    int countByExample(UserExample example);

    /**
     * 根据条件删除多条
     * @param example
     * @return
     */
    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    /**
     * 根据条件查询多条
     * @param example
     * @return
     */
    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Long id);

    /**
     * 按条件更新多条，更新不为 null 的字段
     * @param record
     * @param example
     * @return
     */
    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    /**
     * 按条件更新多条,更新所有字段，更新多条的时候应该会不成功，因为有 id 等不能重复的字段
     * @param record
     * @param example
     * @return
     */
    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}