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
