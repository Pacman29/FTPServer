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
                        // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
                        DataInputStream in = new DataInputStream(socket.getInputStream());
                        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                        String line = null;
                        while (true) {
                            line = in.readUTF(); // ожидаем пока клиент пришлет строку текста.
                            System.out.println("The dumb client just sent me this line : " + line);
                            System.out.println("I'm sending it back...");
                            out.writeUTF(line); // отсылаем клиенту обратно ту самую строку текста.
                            out.flush(); // заставляем поток закончить передачу данных.
                            System.out.println("Waiting for the next line...");
                            System.out.println();
                        }
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
