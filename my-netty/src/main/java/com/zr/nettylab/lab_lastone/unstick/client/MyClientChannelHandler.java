package com.zr.nettylab.lab_lastone.unstick.client;

import com.zr.nettylab.lab_lastone.unstick.PersonProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.StandardCharsets;


public class MyClientChannelHandler extends SimpleChannelInboundHandler<PersonProtocol> {

    private int count = 0;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for(int i = 0; i < 10; i++) {
            String msg = "Msg from client" + i;
            byte[] content = msg.getBytes();
            int length = content.length;
            PersonProtocol personProtocol = new PersonProtocol(length, content);
            ctx.writeAndFlush(personProtocol);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PersonProtocol msg) throws Exception {
        // 接受服务器发送的信息
        int length = msg.getLength();
        byte[] content = msg.getContent();
        System.err.println("服务器接收到消息的次数：" + (++ this.count));
        System.err.println("长度：" + length);
        System.err.println("内容：" + new String(content, StandardCharsets.UTF_8));
    }



}
