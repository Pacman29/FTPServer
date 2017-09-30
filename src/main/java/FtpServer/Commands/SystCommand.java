package FtpServer.Commands;

import FtpServer.ClientImpl;

import java.util.regex.Pattern;

public class SystCommand implements CommandImpl{
    private ClientImpl client;

    public SystCommand(ClientImpl client) {
        this.client = client;
    }

    private String log = "SystCommand not execute";
    private Pattern pattern = Pattern.compile("^\\s*PWD\\s*$",
            Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);


    @Override
    public void execute() {

    }

    @Override
    public String getLogInfo() {
        return null;
    }

    @Override
    public Pattern getCommandPattern() {
        return null;
    }
}
