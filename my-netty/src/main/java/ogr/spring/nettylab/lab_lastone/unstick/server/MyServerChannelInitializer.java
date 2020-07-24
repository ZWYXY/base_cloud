package ogr.spring.nettylab.lab_lastone.unstick.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import ogr.spring.nettylab.lab_lastone.unstick.endecoder.PersonDecoder;
import ogr.spring.nettylab.lab_lastone.unstick.endecoder.PersonEncoder;

public class MyServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new PersonDecoder());
        pipeline.addLast(new PersonEncoder());
        pipeline.addLast(new MyServerChannelHandler());
    }
}
