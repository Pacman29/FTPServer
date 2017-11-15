package FtpServer.Commands;

import FtpServer.IClient;
import FtpServer.Codes.Code230;

import java.io.IOException;
import java.util.regex.Pattern;

// TODO: сделать авторизацию юзера, пока заглушка
public class PassCommand implements ICommand {
    private IClient client;
    private String password;

    public PassCommand(IClient client, String password) {
        this.client = client;
        this.password = password;
    }

    private String log = "PassCommand not execute";
    private Pattern pattern = Pattern.compile("^\\s*PASS\\s+\\w+\\s*$",
            Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);

    @Override
    public String execute() {
        //TODO : сделать авторизацию
        this.log = "PassCommand execute";
        return new Code230().getAll();

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
