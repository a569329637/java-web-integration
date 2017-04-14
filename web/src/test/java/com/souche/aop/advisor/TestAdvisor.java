package com.souche.aop.advisor;

import com.souche.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author guishangquan
 * @Description
 * @Package com.souche.aop.advisor
 * @date 17/4/14
 **/
public class TestAdvisor extends BaseTest {

    @Autowired
    private Waiter waiterTarget;

    @Autowired
    private Seller sellerTarget;

//    @Autowired
//    private Waiter staticAdvisorWaiter;
//
//    @Autowired
//    private Seller staticAdvisorSeller;

    @Test
    public void testStaticAdvisor() {
        waiterTarget.greetTo("name1");
        waiterTarget.serveTo("name2");
        sellerTarget.greetTo("name3");
//        staticAdvisorWaiter.greetTo("name1");
//        staticAdvisorWaiter.serveTo("name2");
//        staticAdvisorSeller.greetTo("name3");
    }

    @Autowired
    private Waiter waiter1;

    @Test
    public void testStaticRegexpAdvisor() {
        waiter1.greetTo("name1");
        waiter1.serveTo("name2");
    }

}
