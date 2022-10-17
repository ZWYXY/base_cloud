package com.zr.nettylab.lab_lastone.unstick.server;

import com.zr.nettylab.lab_lastone.unstick.PersonProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class MyServerChannelHandler extends SimpleChannelInboundHandler<PersonProtocol> {

    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PersonProtocol msg) throws Exception {

        // 接收到客户端的消息后返回
        System.err.println("接收到客户端消息的次数：" + (++ this.count));
        System.err.println("客户端发来的信息长度：" + msg.getLength());
        System.err.println("客户端发来的信息内容：" + new String(msg.getContent(), StandardCharsets.UTF_8) );

        byte[] uuid = UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8);
        int length  = uuid.length;

        ctx.writeAndFlush(new PersonProtocol(length, uuid));
    }

}
