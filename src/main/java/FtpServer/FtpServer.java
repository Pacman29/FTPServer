package FtpServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FtpServer {
    private int port;

    private ExecutorService executorService = Executors.newCachedThreadPool();

    public FtpServer(int port) {
        this.port = port;
    }

    public void go(){
        try {
            ServerSocket ss = new ServerSocket(port); // создаем сокет сервера и привязываем его к вышеуказанному порту
            System.out.println("Waiting for a client...");

            while (true) {
                Socket socket = ss.accept(); // заставляем сервер ждать подключений и выводим сообщение когда кто-то связался с сервером
                System.out.println("Got a client :) ... Finally, someone saw me through all the cover!");
                System.out.println();
                executorService.submit(() -> {
                    try {
                        IncommingMessageProcessorImpl incommingMessageProcessor = new IncommingMessageProcessor(new Client(socket));
                        incommingMessageProcessor.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch(Exception x) { x.printStackTrace(); }
    }

    public int getPort() {
        return port;
    }
}
