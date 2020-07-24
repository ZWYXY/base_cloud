package ogr.spring.tdbio;

import lombok.SneakyThrows;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class BIOClient {

    @SneakyThrows
    public static void main(String[] args) {
        // 绑定Socket
        Socket socket = new Socket("localhost", 9901);

        // 获取IO流，读取服务端消息
        InputStream in = socket.getInputStream();
        byte[] inByte = new byte[1024];
        System.err.println(new String(inByte, 0, in.read(inByte)));

        // 获取IO流，向服务器写消息
        OutputStream out = socket.getOutputStream();
        out.write("你好服务器".getBytes(StandardCharsets.UTF_8));
        out.flush();

        // 保持客户端连接
        while (true) {

        }
    }



}
