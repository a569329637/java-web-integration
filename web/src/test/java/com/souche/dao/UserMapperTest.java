package com.souche.dao;

import com.souche.BaseTest;
import com.souche.model.User;
import com.souche.model.UserExample;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author guishangquan
 * @Description
 * @Package com.souche.dao
 * @date 17/5/19
 **/
public class UserMapperTest extends BaseTest {
    @Autowired
    private UserMapper userMapper;

    @Before
    public void before() {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUserNameEqualTo("a569329637_0");
        List<User> userList = userMapper.selectByExample(userExample);

        if (userList.size() == 0) {
            // 插入 UserMapperTest 测试所需要的所有数据
            for (int i = 0; i < 5; ++i) {
                User user = new User();
                user.setUserName("a569329637_" + i);
                user.setPassword("mima_" + i);
                user.setRealName("realname_" + i);
                userMapper.insert(user);
            }
            for (int i = 5; i < 10; ++i) {
                User user = new User();
                user.setUserName("a569329637_" + i);
                user.setPassword("mima_" + i);
                user.setRealName("realname_" + i);
                userMapper.insertSelective(user);
            }
        }
    }

    @Test
    public void test() {
        // selectByExample
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUserNameEqualTo("a569329637_0");
        List<User> userList = userMapper.selectByExample(userExample);
        Assert.assertEquals(userList.size(), 1);

        // selectByPrimaryKey
        User user = userMapper.selectByPrimaryKey(userList.get(0).getId());
        Assert.assertEquals(user.getId(), userList.get(0).getId());
        Assert.assertEquals(user.getUserName(), userList.get(0).getUserName());
        Assert.assertEquals(user.getPassword(), userList.get(0).getPassword());
        Assert.assertEquals(user.getRealName(), userList.get(0).getRealName());

        // countByExample
        UserExample userExample1 = new UserExample();
        UserExample.Criteria criteria1 = userExample1.createCriteria();
        criteria1.andUserNameLike("a569329637_%");
        int count = userMapper.countByExample(userExample1);
        Assert.assertEquals(10, count);

        // updateByPrimaryKey
        User user1 = new User();
        user1.setId(user.getId());
        user1.setUserName(user.getUserName());
        user1.setPassword(user.getPassword());
        user1.setRealName("new real name");
        userMapper.updateByPrimaryKey(user1);
        user1 = userMapper.selectByPrimaryKey(user.getId());
        Assert.assertEquals("new real name", user1.getRealName());

        // updateByPrimaryKeySelective
        User user2 = new User();
        user2.setId(user.getId());
        user2.setUserName(user.getUserName());
        user2.setPassword(null);
        user2.setRealName("new real name");
        userMapper.updateByPrimaryKeySelective(user2);
        user2 = userMapper.selectByPrimaryKey(user.getId());
        Assert.assertEquals("new real name", user2.getRealName());
        Assert.assertEquals(user.getPassword(), user2.getPassword());

        // deleteByPrimaryKey
        userMapper.deleteByPrimaryKey(user.getId());
        User user3 = userMapper.selectByPrimaryKey(user.getId());
        Assert.assertNull(user3);

        // deleteByExample
        UserExample userExample2 = new UserExample();
        UserExample.Criteria criteria2 = userExample2.createCriteria();
        criteria2.andIdEqualTo(user.getId() + 1);
        userMapper.deleteByExample(userExample2);
        User user4 = userMapper.selectByPrimaryKey(user.getId() + 1);
        Assert.assertNull(user4);

        // updateByExample
        // 更新 record，where 条件是 example
        // 这个方法可以不需要，当 example 满足多个条件时，用 record 去更新所有的数据，肯定会有主键冲突
        UserExample userExample3 = new UserExample();
        UserExample.Criteria criteria3 = userExample3.createCriteria();
        criteria3.andUserNameEqualTo("a569329637_4");
        User user5 = new User();
        user5.setUserName(user.getUserName());
        user5.setPassword(user.getPassword());
        user5.setRealName(user.getRealName());
        user5.setId(user.getId());
        userMapper.updateByExample(user5, userExample3);

        // updateByExampleSelective
        UserExample userExample4 = new UserExample();
        UserExample.Criteria criteria4 = userExample4.createCriteria();
        criteria4.andUserNameLike("a569329637_%");
        User user6 = new User();
        user6.setPassword("xxxxxx");
        user6.setRealName("xxx");
        user6.setUserName("xxxx");
        userMapper.updateByExampleSelective(user6, userExample4);
    }

}
