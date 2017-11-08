package FtpServer.Commands;

import FtpServer.IClient;
import FtpServer.Codes.Code257;
import FtpServer.Codes.Code501;
import FtpServer.Modules.Catalog;
import FtpServer.Modules.PathChecker;

import java.io.IOException;
import java.util.regex.Pattern;

public class RmdCommand implements ICommand {
    private IClient client;
    private String dir;

    public RmdCommand(IClient client, String dir) {
        this.client = client;
        this.dir = dir;
    }

    private String log = "RmdCommand not execute";
    private Pattern pattern = Pattern.compile("^\\s*RMD\\s+\\w+\\s*$",
            Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);

    @Override
    public void execute() {
        PathChecker pathChecker = new PathChecker(this.dir);
        if(!pathChecker.isValid()){
            //TODO: Ошибка в случаи не валидного пути
        }
        String response = null;
        try {
            if(Catalog.rmdir(client.getUserWorkingDirectory()+this.dir)){
                response = String.format(new Code257().getAll(),"dir created");
                this.log = "RmdCommand execute";
            } else {
                response = new Code501().getAll();
                this.log = "RmdCommand execute with error";
            }
        } catch (Exception e) {
            e.printStackTrace();
            //TODO: Ошибка при удалении директории
        }
        try {
            client.sendLine(response);
        } catch (IOException e) {
            e.printStackTrace();
            //TODO: Ошибка при отправлении сообщения
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
