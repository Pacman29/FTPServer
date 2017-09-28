import java.net.*;
import java.io.*;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestClient {
    public static void main(String[] ar) {
        int serverPort = 6666; // здесь обязательно нужно указать порт к которому привязывается сервер.
        String address = "127.0.0.1"; // это IP-адрес компьютера, где исполняется наша серверная программа. 
        // Здесь указан адрес того самого компьютера где будет исполняться и клиент.

        try {
            InetAddress ipAddress = InetAddress.getByName(address); // создаем объект который отображает вышеописанный IP-адрес.
            System.out.println("Any of you heard of a socket with IP address " + address + " and port " + serverPort + "?");

            ExecutorService service = Executors.newCachedThreadPool();

            for (int i = 0; i<10; i++){
                service.submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Socket socket = new Socket(ipAddress, serverPort); // создаем сокет используя IP-адрес и порт сервера.
                            System.out.println("Yes! I just got hold of the program.");

                            // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиентом.
                            InputStream sin = socket.getInputStream();
                            OutputStream sout = socket.getOutputStream();

                            // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
                            DataInputStream in = new DataInputStream(sin);
                            DataOutputStream out = new DataOutputStream(sout);


                            String line = String.valueOf(new Random().nextInt());
                            out.writeUTF(line); // отсылаем введенную строку текста серверу.
                            out.flush(); // заставляем поток закончить передачу данных.
                            line = in.readUTF(); // ждем пока сервер отошлет строку текста.
                            System.out.println("The server was very polite. It sent me this : " + line);
                            System.out.println("Looks like the server is pleased with us. Go ahead and enter more lines.");
                            System.out.println();
                        } catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                });
            }

        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}