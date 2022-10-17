package com.zr.nettylab.lab_lastone.unstick.endecoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import com.zr.nettylab.lab_lastone.unstick.PersonProtocol;

import java.util.List;

public class PersonDecoder extends ReplayingDecoder<Void> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        int length = in.readInt();
        byte[] content = new byte[length];
        // 读length长的数据到content字节数组中
        in.readBytes(content);

        // 把数据构造成PersonProtocol放入到out中，下一个handler里取出来的就是PersonProtocol
        out.add(new PersonProtocol(length, content));
    }
}
