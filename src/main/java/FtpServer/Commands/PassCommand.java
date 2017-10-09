package FtpServer.Commands;

import FtpServer.ClientImpl;
import FtpServer.Codes.Code230;

import java.io.IOException;
import java.util.regex.Pattern;

// TODO: сделать авторизацию юзера, пока заглушка
public class PassCommand implements CommandImpl{
    private ClientImpl client;
    private String password;

    public PassCommand(ClientImpl client, String password) {
        this.client = client;
        this.password = password;
    }

    private String log = "PassCommand not execute";
    private Pattern pattern = Pattern.compile("^\\s*PASS\\s+\\w+\\s*$",
            Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);

    @Override
    public void execute() {
        try {
            client.sendLine(new Code230().getAll());
            this.log = "PassCommand execute";
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
