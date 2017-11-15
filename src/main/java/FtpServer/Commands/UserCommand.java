package FtpServer.Commands;


import FtpServer.IClient;
import FtpServer.Codes.Code331;

import java.io.IOException;
import java.util.regex.Pattern;

// TODO: сделать авторизацию юзера, пока заглушка
public class UserCommand implements ICommand {

    private IClient client;
    private String username;

    private String log = "UserCommand not execute";
    private Pattern pattern = Pattern.compile("^\\s*USER\\s+\\w+\\s*$",
            Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);

    public UserCommand(IClient client, String username) {
        this.client = client;
        this.username = username;
    }

    @Override
    public String execute() {
        this.log = "UserCommand execute";
        return new Code331().getAll();

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
