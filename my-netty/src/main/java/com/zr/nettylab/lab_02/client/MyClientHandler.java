package com.zr.nettylab.lab_02.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

public class MyClientHandler extends SimpleChannelInboundHandler<String> {


        @Override
        protected void channelRead0 (ChannelHandlerContext ctx, String msg) throws Exception {
            System.err.println("msg form server:" + msg);
            ctx.writeAndFlush("from client:" + LocalDateTime.now());
        }

        @Override
        public void exceptionCaught (ChannelHandlerContext ctx, Throwable cause) throws Exception {
            cause.printStackTrace();
            ctx.close();
        }

}
