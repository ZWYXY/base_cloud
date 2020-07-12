package ogr.spring.nettylab.lab_02.server;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup boss = new NioEventLoopGroup();  // 创建线程组，用于监听和接收连接
        EventLoopGroup worker = new NioEventLoopGroup();// 创建线程组，用于IO等操作
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boss, worker)
                .channel(NioServerSocketChannel.class)   //
                .childHandler(new MyServerInitializer());// 这里 childHandler 用于对worker线程组中的Channel进行逻辑处理

            ChannelFuture channelFuture =  serverBootstrap.bind(1024).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
