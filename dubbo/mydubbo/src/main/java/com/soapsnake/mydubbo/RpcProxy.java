package com.soapsnake.mydubbo;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.soapsnake.mydubbo.client.RpcClientInvocationHandler;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-10-09
 */
public class RpcProxy {
    private static final String classPrefix = "com.xcxcxcxcx.test.";

    ThreadLocal<RpcClientInvocationHandler> rpcClientThreadLocal = new ThreadLocal<>();

    private String serviceName;

    public RpcProxy(String serviceName){
        this.serviceName = serviceName;
        rpcClientThreadLocal.set(new RpcClientInvocationHandler(serviceName));
    }

    public Object create(){
        Class service = null;
        try {
            service = Class.forName(classPrefix + serviceName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Object o = Proxy.newProxyInstance(service.getClassLoader(),new Class[]{service},rpcClientThreadLocal.get());
        return o;
    }

    public void execute(String methodName,Object... args){

        //获取参数类型
        Class[] paramType = new Class[args.length];
        for(int i = 0;i < args.length;i++){
            paramType[i] = args[i].getClass();
            System.out.println("paramType["+i+"]的类型名:"+paramType[i].getTypeName());
        }

        try {
            Class service = Class.forName(classPrefix + serviceName);
            Method method = service.getMethod(methodName,paramType);
            Object o = Proxy.newProxyInstance(service.getClassLoader(),new Class[]{service},rpcClientThreadLocal.get());
            rpcClientThreadLocal.get().invoke(o,method,args);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
