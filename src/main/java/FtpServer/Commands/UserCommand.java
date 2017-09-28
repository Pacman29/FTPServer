package FtpServer.Commands;


import FtpServer.Client;
import FtpServer.ClientImpl;

import java.io.IOException;
import java.util.regex.Pattern;

// TODO: сделать авторизацию юзера, пока заглушка
public class UserCommand implements CommandImpl{

    private ClientImpl client;

    private String log = "UserCommand not execute";
    private Pattern pattern = Pattern.compile("");

    public UserCommand(ClientImpl client) {
        this.client = client;
    }

    @Override
    public void execute() {
        try {
            client.sendLine("331 username accepted\n");
            this.log = "UserCommand execute";
        } catch (IOException e) {
            // TODO: обработать ошибку в случае не удачи отправки сообщения клиенту
            e.printStackTrace();
        }
    }

    @Override
    public String getLogInfo() {
        return log;
    }

    @Override
    public Pattern getCommandPattern() {
        return pattern;
    }
}
