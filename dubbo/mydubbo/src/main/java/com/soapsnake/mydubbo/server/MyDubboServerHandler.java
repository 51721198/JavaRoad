package com.soapsnake.mydubbo.server;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.soapsnake.mydubbo.msg.RpcRequest;
import com.soapsnake.mydubbo.msg.RpcResponse;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * 
 * Created on 2020-10-09
 */
public class MyDubboServerHandler extends ChannelInboundHandlerAdapter {

    private RpcInvocationHandler rpcInvocationHandler;

//    public static void main(String[] args) {
//    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("client有请求!" + msg);
        //获取到client的请求对象，根据请求对象调用服务，并将执行结果封装后返回给客户端
        RpcRequest rpcRequest = (RpcRequest) msg;
        String serviceName = rpcRequest.getServiceName();
        Class<?> service = Class.forName(serviceName);
        System.out.println(serviceName);
        System.out.println(rpcRequest.getMethodName());
        Method method = service.getMethod(rpcRequest.getMethodName(), rpcRequest.getParamType());
        Object[] args = rpcRequest.getArgs();
        int start = serviceName.lastIndexOf(".") + 1;
        int end = serviceName.length();
        rpcInvocationHandler = new RpcInvocationHandler(serviceName.substring(start, end));

        Object serviceProxy = Proxy
                .newProxyInstance(service.getClassLoader(), service.getInterfaces(), rpcInvocationHandler);
        try {
            RpcResponse rpcResponse = new RpcResponse();
            rpcResponse.setReturnObject(rpcInvocationHandler.invoke(serviceProxy, method, args));
            Channel channel = ctx.channel();
            channel.writeAndFlush(rpcResponse);
            System.out.println(rpcResponse);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }


    }


}
