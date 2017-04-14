package com.souche.aop.advice;

import com.souche.aop.introduce.Monitorable;
import com.souche.aop.proxy.PerformanceMonitor;
import com.souche.aop.service.ForumService;
import com.souche.aop.service.impl.ForumServiceImpl;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;


/**
 * @author guishangquan
 * @Description
 * @Package com.souche.aop.advice
 * @date 17/4/14
 **/
public class ControllablePerformanceMonitor extends DelegatingIntroductionInterceptor implements Monitorable {

    private ThreadLocal<Boolean> MonitorStatusMap = new ThreadLocal<Boolean>();


    public void setMonitorActive(boolean active) {
        MonitorStatusMap.set(active);
    }


    // 拦截方法
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Object ret = null;

        // 根据状态决定是否开启性能监控
        if (MonitorStatusMap.get() != null && MonitorStatusMap.get()) {
            PerformanceMonitor
                .begin(methodInvocation.getClass().getName() + "." + methodInvocation.getMethod().getName());
            ret = super.invoke(methodInvocation);
            PerformanceMonitor.end();
        } else {
            ret = super.invoke(methodInvocation);
        }

        return ret;
    }
}


class TestControllablePerformanceMonitor {

    public static void main(String[] args) {
        ForumService target = new ForumServiceImpl();
        ControllablePerformanceMonitor controllablePerformanceMonitor = new ControllablePerformanceMonitor();
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvice(controllablePerformanceMonitor);
        // 引介增强一定要通过 cglib 实现
        proxyFactory.setProxyTargetClass(true);
        ForumService proxy = (ForumService) proxyFactory.getProxy();

        proxy.removeForum(1);
        proxy.removeTopic(2);

        // Monitorable 相当于 ForumService 的子类
        Monitorable monitorable = (Monitorable) proxy;
        monitorable.setMonitorActive(true);

        proxy.removeForum(1);
        proxy.removeTopic(2);
    }
}
