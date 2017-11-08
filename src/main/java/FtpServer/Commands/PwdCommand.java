package FtpServer.Commands;

import FtpServer.IClient;
import FtpServer.Codes.Code257;

import java.io.IOException;
import java.util.regex.Pattern;

public class PwdCommand implements ICommand {
    private IClient client;

    public PwdCommand(IClient client) {
        this.client = client;
    }

    private String log = "PwdCommand not execute";
    private Pattern pattern = Pattern.compile("^\\s*PWD\\s*$",
            Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);


    @Override
    public void execute() {
        try {
            String response = String.format(new Code257().getAll(),client.getUserWorkingDirectory());
            client.sendLine(response);
            this.log = "PwdCommand execute";
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
