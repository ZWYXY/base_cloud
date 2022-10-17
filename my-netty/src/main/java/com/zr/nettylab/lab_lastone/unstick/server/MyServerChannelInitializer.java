package com.zr.nettylab.lab_lastone.unstick.server;

import com.zr.nettylab.lab_lastone.unstick.endecoder.PersonEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import com.zr.nettylab.lab_lastone.unstick.endecoder.PersonDecoder;

public class MyServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new PersonDecoder());
        pipeline.addLast(new PersonEncoder());
        pipeline.addLast(new MyServerChannelHandler());
    }
}
