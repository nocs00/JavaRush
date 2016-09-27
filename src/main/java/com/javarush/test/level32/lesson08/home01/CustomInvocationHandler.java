package com.javarush.test.level32.lesson08.home01;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CustomInvocationHandler implements InvocationHandler
{
    private SomeInterfaceWithMethods object;

    public CustomInvocationHandler(SomeInterfaceWithMethods object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    {
        String methodName = method.getName();
        System.out.println(methodName + " in");
        Object object = method.invoke(this.object, args);
        System.out.println(methodName + " out");

        return object;
    }
}
