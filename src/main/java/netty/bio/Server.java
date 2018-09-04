package netty.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serverSocket;
    private int port;

    public Server(int port) {
        this.port = port;
    }

    public void start() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("服务端启动");
            while (true) {
                Socket socket = serverSocket.accept();
                new ServerHandler(socket).handler();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server(8888);
        server.start();
    }


}

class ServerHandler {

    private Socket socket;

    private static final int MAX_DATA_LEN = 1024;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    public void handler() {
        new Thread(() -> {
            try {
                InputStream inputStream = socket.getInputStream();
                while (true) {
                    byte[] data = new byte[MAX_DATA_LEN];
                    int len = 0;
                    while ((len = inputStream.read(data)) != -1) {
                        String message = new String(data, 0, len);
                        System.out.println("客户端传来信息：" + message);
                        socket.getOutputStream().write(data);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
