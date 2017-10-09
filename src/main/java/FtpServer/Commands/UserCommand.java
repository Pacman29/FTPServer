package FtpServer.Commands;


import FtpServer.Client;
import FtpServer.ClientImpl;
import FtpServer.Codes.Code331;

import java.io.IOException;
import java.util.regex.Pattern;

// TODO: сделать авторизацию юзера, пока заглушка
public class UserCommand implements CommandImpl{

    private ClientImpl client;
    private String username;

    private String log = "UserCommand not execute";
    private Pattern pattern = Pattern.compile("^\\s*USER\\s+\\w+\\s*$",
            Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);

    public UserCommand(ClientImpl client, String username) {
        this.client = client;
        this.username = username;
    }

    @Override
    public void execute() {
        try {
            client.sendLine(new Code331().getAll());
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
