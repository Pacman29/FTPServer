package FtpServer.Commands;

import FtpServer.IClient;
import FtpServer.Codes.Code215;

import java.io.IOException;
import java.util.regex.Pattern;

public class SystCommand implements ICommand {
    private IClient client;

    public SystCommand(IClient client) {
        this.client = client;
    }

    private String log = "SystCommand not execute";
    private Pattern pattern = Pattern.compile("^\\s*SYST\\s*$",
            Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);


    @Override
    public String execute() {
        String response = new Code215().getAll();
        this.log = "SystCommand execute";
        return response;
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
