package com.zr.nettylab.lab_02.client;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup eGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eGroup)
                .channel(NioSocketChannel.class)
                .handler(new MyClientInitializer());
            ChannelFuture channelFuture = bootstrap.connect("localhost", 1024).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            eGroup.shutdownGracefully();
        }

    }

}
