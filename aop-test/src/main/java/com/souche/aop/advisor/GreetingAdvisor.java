package com.souche.aop.advisor;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

import java.lang.reflect.Method;

/**
 * @author guishangquan
 * @Description
 * @Package com.souche.aop.advisor
 * @date 17/4/14
 **/
public class GreetingAdvisor extends StaticMethodMatcherPointcutAdvisor {

    public boolean matches(Method method, Class<?> aClass) {
        return "greetTo".equals(method.getName());
    }

    @Override
    public ClassFilter getClassFilter() {
        return new ClassFilter() {
            public boolean matches(Class<?> aClass) {
                return Waiter.class.isAssignableFrom(aClass);
            }
        };
    }
}
