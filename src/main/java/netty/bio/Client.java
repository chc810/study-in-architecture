package netty.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class Client {

    public static void main(String[] strings) throws Exception {
        Socket socket = new Socket("127.0.0.1",8888);
        System.out.println("客户端启动成功");
        handleInput(socket);
        while (true) {
            String message = "hello world";
            System.out.println("客户端发送数据：" + message);
            socket.getOutputStream().write(message.getBytes());

            Thread.sleep(5000);
        }
    }

    private static void handleInput(final Socket socket) {
        new Thread(() -> {
            byte[] bytes = new byte[2000];
            while (true) {
                try {
                    InputStream inputStream = socket.getInputStream();
                    int len;
                    while ((len = inputStream.read(bytes)) != -1) {
                        String message = new String(bytes, 0, len);
                        System.out.println("客户端接受数据：" + message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }).start();
    }


}
