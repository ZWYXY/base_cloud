package ogr.spring.nettylab.lab_lastone.unstick.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup eventExecutors = new NioEventLoopGroup() ;
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap
                    .group(eventExecutors)
                    .channel(NioSocketChannel.class)
                    .handler(new MyClientChannelInitializer())
            ;
            ChannelFuture channelFuture = bootstrap.connect("localhost", 1025).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            eventExecutors.shutdownGracefully();
        }
    }

}
