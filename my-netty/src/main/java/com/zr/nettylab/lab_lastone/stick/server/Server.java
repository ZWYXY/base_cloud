package com.zr.nettylab.lab_lastone.stick.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 这个Lab 演示TCP粘包、拆包问题
 * 粘包，拆包问题，是与TCP协议本身有关，不是netty的问题
 */
public class Server {

    public static void main(String[] args) throws InterruptedException {
        // 1、定义两个线程组 boss接收连接，并且保存连接信息
        EventLoopGroup boss = new NioEventLoopGroup(1);  // 因为我只想开放一个端口用于TCP连接，所以只放入一个线程
        EventLoopGroup worker = new NioEventLoopGroup(3);// 这里分配三个线程，用户IO等操作
        try {
            // 新建启动程序
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            //
            serverBootstrap
                .group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new MyServerChannelInitializer());

            //
            ChannelFuture channelFuture = serverBootstrap.bind(1025).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
