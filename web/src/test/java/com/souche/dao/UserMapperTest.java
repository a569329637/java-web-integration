package com.souche.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.souche.BaseTest;
import com.souche.model.User;
import org.springframework.test.annotation.Rollback;
import org.springframework.util.ObjectUtils;

/**
 * @author guishangquan
 * @Description
 * @Package com.souche.dao
 * @date 17/4/28
 **/
public class UserMapperTest extends BaseTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    @Rollback(false)
    public void testInsert() {
        User user = new User();
        user.setAddress("杭州-五常大道");
        user.setName("啦啦啦");

        userMapper.insert(user);

        System.out.println("user = " + user);
    }

    @Test
    @Rollback(false)
    public void testSelectAndUpdate() {
        Long id = 1L;

        User user = userMapper.selectByPrimaryKey(id);
        System.out.println("user = " + user);

        user.setName(user.getName() + "1");
        userMapper.updateByPrimaryKey(user);
        System.out.println("user = " + user);
    }

    @Test
    @Rollback(false)
    public void testDelete() {
        Long id = 2L;

        User user = userMapper.selectByPrimaryKey(id);
        System.out.println("user = " + user);

        if (!ObjectUtils.isEmpty(user)) {
            userMapper.deleteByPrimaryKey(user.getId());
            System.out.println("user = " + user);
        }
    }

    @Test
    @Rollback(false)
    public void testInsertSelective() {
        User user = new User();
        user.setName("啊啊啊");
        user.setAddress("");

        userMapper.insertSelective(user);
        System.out.println("user = " + user);
    }

    @Test
    @Rollback(false)
    public void testUpdateByPrimaryKeySelective() {
        Long id = 3L;

        User user = userMapper.selectByPrimaryKey(id);
        System.out.println("user = " + user);

        user.setAddress("杭州-五常大道联盛路口");
        user.setName(null);
        userMapper.updateByPrimaryKeySelective(user);


        user = userMapper.selectByPrimaryKey(id);
        System.out.println("user = " + user);
    }
}
