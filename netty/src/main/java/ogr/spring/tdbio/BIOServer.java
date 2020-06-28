package ogr.spring.tdbio;

import lombok.SneakyThrows;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ConcurrentHashMap;

public class BIOServer {

    ConcurrentHashMap<String, Socket> concurrentHashMap = new ConcurrentHashMap<>();

    @SneakyThrows
    public void start(int port) {
        ServerSocket cSocket = new ServerSocket(port);
        for (; ; ) {
            Socket s = cSocket.accept();
            new Thread(() -> dealInput(s)).start();
        }
    }

    @SneakyThrows
    public void dealInput(Socket socket) {
        // 向客户端回写一句
        OutputStream out = socket.getOutputStream();
        out.write("Welcome to connect".getBytes(StandardCharsets.UTF_8));
        out.flush();
        // 接收客户端消息
        byte[] inByte = new byte[1024];
        InputStream in = socket.getInputStream();
        System.err.println(new String(inByte, 0, in.read(inByte)));
        // 保存Channel
        String ip = socket.getInetAddress().getHostAddress();
        concurrentHashMap.put(ip, socket);
    }

    public static void main(String[] args) {
        new BIOServer().start(9901);
    }

}
