package com.souche.dao;

import com.souche.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author guishangquan
 * @Description
 * @Package com.souche.dao
 * @date 17/5/19
 **/
public class BasicDaoTest extends BaseTest {

    @Autowired
    private BasicDao basicDao;

    @Test
    public void test() {
        String sql = "select * from user";
        List<Map<String, Object>> res = basicDao.select(sql);
        for (Map<String, Object> d : res) {
            System.out.println("d = " + d);
        }

        List<Map<String, Object>> update = basicDao.update(sql);
        System.out.println("update = " + update);
    }
}
