package ogr.spring._1tcp.netty.preface.discard.server;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@ChannelHandler.Sharable
@Component
public class NettyServerHandler extends ChannelInboundHandlerAdapter {


    /**
     * 客户端连接会触发
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        log.info("{}:上线", getIpAddr(ctx));
        NettyServer.map.put(getIpAddr(ctx), ctx.channel());
    }

    /**
     * 客户端发消息会触发
     * 这边接收硬件发送来的消息，处理并回写
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        log.info("Server received: {}", msg);
    }

    /**
     * 发生异常触发
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.error("tcp连接异常", cause);
        NettyServer.map.remove(getIpAddr(ctx));
        ctx.close();
    }

    /**
     * 不活跃的通道
     * 连接丢失后执行
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        log.info("{}:断开连接", getIpAddr(ctx));
        NettyServer.map.remove(getIpAddr(ctx));
        ctx.close();
    }

    /**
     * 客户下线触发
     *
     * @param ctx 上下文
     * @throws Exception 异常
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        super.handlerRemoved(ctx);
    }

    private String getIpAddr(ChannelHandlerContext ctx) {
        return ctx.channel().remoteAddress().toString().split(":")[0].split("/")[1];
    }
}