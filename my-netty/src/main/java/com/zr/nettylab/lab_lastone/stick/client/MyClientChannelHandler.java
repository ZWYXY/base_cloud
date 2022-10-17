package com.zr.nettylab.lab_lastone.stick.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class MyClientChannelHandler extends SimpleChannelInboundHandler<ByteBuf> {

    int count = 0;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        byte[] buffer = new byte[msg.readableBytes()];
        msg.readBytes(buffer);
        System.err.println("客户端接收到的消息内容：" + new String(buffer, CharsetUtil.UTF_8));
        System.err.println("客户端接收到的消息数量" + (++ this.count));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for(int i = 0; i < 10; i++) {
            ByteBuf byteBuf = Unpooled.copiedBuffer("msg from client", CharsetUtil.UTF_8);
            ctx.writeAndFlush(byteBuf);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }


}
