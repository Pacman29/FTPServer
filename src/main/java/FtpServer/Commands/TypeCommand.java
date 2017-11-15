package FtpServer.Commands;

import FtpServer.Codes.Code150;
import FtpServer.Codes.Code200;
import FtpServer.Codes.Code501;
import FtpServer.IClient;
import FtpServer.Modules.FileSenderFabric;
import FtpServer.Modules.IFileSenderFabric;
import FtpServer.Modules.PathChecker;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

public class TypeCommand implements ICommand {
    private IClient client;
    private String type;

    public TypeCommand(IClient client, String type) {
        this.client = client;
        this.type = type;
    }

    private String log = "TypeCommand not execute";
    private Pattern pattern = Pattern.compile("^\\s*TYPE\\s+\\w+\\s*$",
            Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);

    @Override
    public void execute() {
        String res;
        switch (type){
            case "A" :
                client.setSocketType(FileSenderFabric.SocketType.ASCII);
                res = new Code200().getAll();
                break;
            case "I" :
                client.setSocketType(FileSenderFabric.SocketType.BINARY);
                res = new Code200().getAll();
                break;
            default:
                res = new Code501().getAll();
        }
        try {
            client.sendLine(res);
        } catch (IOException e) {
            e.printStackTrace();
            // TODO: обработать ошибку в случае не удачи отправки сообщения клиенту
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

