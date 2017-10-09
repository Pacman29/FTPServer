package FtpServer.Commands;

import FtpServer.ClientImpl;

import java.util.regex.Pattern;

public class ListCommand implements CommandImpl{
    private ClientImpl client;
    private String dir;

    public ListCommand(ClientImpl client, String dir) {
        this.client = client;
        this.dir = dir;
    }

    private String log = "ListCommand not execute";
    private Pattern pattern = Pattern.compile("^\\s*LIST\\s+\\w+\\s*$",
            Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);

    @Override
    public void execute() {

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
