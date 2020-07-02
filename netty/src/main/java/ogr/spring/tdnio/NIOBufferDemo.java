package ogr.spring.tdnio;

import java.nio.ByteBuffer;

public class NIOBufferDemo {

    public static void main(String[] args) {

        // 开辟缓冲区的三个方式
            // 1
        ByteBuffer bb = ByteBuffer.allocate(10);

            // 2
        byte[] ba = new byte[10];
        ByteBuffer bb1 = ByteBuffer.wrap(ba); //对缓冲区的存取操作，会影响ba，对ba的操作也会影响缓冲区

            // 3
        byte[] ba1 = new byte[10];
        ByteBuffer bb2 = ByteBuffer.wrap(ba1, 2, 5); // 从下标为2开始截取5个作为初始缓冲区容量
        System.err.println(bb2.isDirect());


    }


}
