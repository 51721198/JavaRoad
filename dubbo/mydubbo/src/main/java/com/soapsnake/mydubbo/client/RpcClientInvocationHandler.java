package com.soapsnake.mydubbo.client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.soapsnake.mydubbo.constants.Constant;
import com.soapsnake.mydubbo.msg.RpcRequest;
import com.soapsnake.mydubbo.msg.RpcResponse;
import com.soapsnake.mydubbo.regcenter.ServiceRegistry;
import com.soapsnake.mydubbo.regcenter.impl.SimpleServiceRegistry;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-10-09
 */
public class RpcClientInvocationHandler implements InvocationHandler{

    private String serviceName;

    private EventLoopGroup group;

    private Bootstrap bootstrap;

    private ServiceRegistry serviceRegistry = new SimpleServiceRegistry();

    public RpcClientInvocationHandler(String ServiceName) {
        this.serviceName = ServiceName;
        //建立连接前的channel初始化
        group = new NioEventLoopGroup();
        bootstrap = new Bootstrap()
                .group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast("decoder", new ObjectDecoder(ClassResolvers.softCachingConcurrentResolver(
                                RpcResponse.class.getClassLoader())));
                        pipeline.addLast("handler", new MyDubboClientHandler());
                        pipeline.addLast("encoder", new ObjectEncoder());

                    }
                });
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("TestService方法执行!");
        //创建RpcRequest
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setServiceName(Constant.CLASS_PREFIX + serviceName);
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setParamType(method.getParameterTypes());
        rpcRequest.setReturnType(method.getReturnType());
        rpcRequest.setArgs(args);

        //服务发现，解析host和port
        String serviceAddress = serviceRegistry.discoverService(serviceName);
        String host = serviceAddress.split(":")[0];
        int port = Integer.parseInt(serviceAddress.split(":")[1]);
        System.out.println("服务发现远程调用地址为: " +host+":"+port);

        //传递RpcRequest对象，调用远程方法，并异步获取执行结果
        Channel channel = bootstrap.connect(host, port).sync().channel();

        System.out.println("client 连接server 成功!!!!!!");
//        channel.writeAndFlush("hello\r\n");

        ChannelFuture future = channel.writeAndFlush(rpcRequest);
        System.out.println("client 发起调用 成功!!!!!! >>>>" + future.toString());
        return null;
    }

    @Override
    protected void finalize() throws Throwable {
        if (group != null) {
            group.shutdownGracefully();
            System.out.println("group销毁!");
        }
    }

}