package ogr.spring._1tcp.netty.preface.discard;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class NettyClient {

    private SocketChannel socketChannel;
    private EventLoopGroup group = new NioEventLoopGroup();

    @Value("${local.netty.client-port}")
    private Integer port; // 给硬件发送时，往这个端口发

    @Value("${local.netty.client-host}")
    private String host; //  硬件Ip地址

    /**
     * 发送消息
     */
    public ChannelFuture sendMsg(String msg) {
        log.debug("Client send: {}", msg);
        return socketChannel.writeAndFlush(msg);
    }

    @PostConstruct
    public void start() {
        log.debug("客户端将要连接的socket{}:{}", host, port);
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .remoteAddress(host, port)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new NettyClientInitializer());
        ChannelFuture future = bootstrap.connect();
        //客户端断线重连逻辑
        future.addListener((ChannelFutureListener) future1 -> {
            if (future1.isSuccess()) {
                log.info("连接Netty服务端成功");
            } else {
                log.info("连接失败，进行断线重连");
                future1.channel().eventLoop().schedule(this::start, 2, TimeUnit.SECONDS);
            }
        });
        socketChannel = (SocketChannel) future.channel();
    }

    class NettyClientInitializer extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel socketChannel) {
            socketChannel.pipeline().addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
            socketChannel.pipeline().addLast("encoder", new StringEncoder(CharsetUtil.US_ASCII));
            socketChannel.pipeline().addLast(new NettyClientHandler());
        }
    }

    class NettyClientHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelActive(ChannelHandlerContext ctx) {
            log.info("客户端Active .....");
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg)  {
            log.info("Client received: {}", msg.toString());
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            log.error("连接异常", cause);
//            ctx.close();
        }
    }


}






