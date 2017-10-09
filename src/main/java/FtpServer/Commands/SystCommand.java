package FtpServer.Commands;

import FtpServer.ClientImpl;
import FtpServer.Codes.Code215;
import FtpServer.Codes.Code257;

import java.io.IOException;
import java.util.regex.Pattern;

public class SystCommand implements CommandImpl{
    private ClientImpl client;

    public SystCommand(ClientImpl client) {
        this.client = client;
    }

    private String log = "SystCommand not execute";
    private Pattern pattern = Pattern.compile("^\\s*SYST\\s*$",
            Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);


    @Override
    public void execute() {
        try {
            String response = new Code215().getAll();
            client.sendLine(response);
            this.log = "SystCommand execute";
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
