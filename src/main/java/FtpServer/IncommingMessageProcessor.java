package FtpServer;

import java.io.IOException;

public class IncommingMessageProcessor implements IncommingMessageProcessorImpl {
    private ClientImpl client;

    public IncommingMessageProcessor(ClientImpl client) {
        this.client = client;
    }

    @Override
    public void start(){
        try {
            String line = null;
            while (true) {
                line = client.waitLine(); // ожидаем пока клиент пришлет строку текста.
                System.out.println("The dumb client just sent me this line : " + line);
                System.out.println("I'm sending it back...");
                client.sendLine(line); // заставляем поток закончить передачу данных.
                System.out.println("Waiting for the next line...");
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
