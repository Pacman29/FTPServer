package FtpServer.Commands;

import FtpServer.ClientImpl;
import FtpServer.Codes.Code250;
import FtpServer.Modules.Catalog;
import FtpServer.Modules.PathChecker;

import java.io.IOException;
import java.util.regex.Pattern;

public class ListCommand implements CommandImpl{
    private ClientImpl client;
    private String dir;

    public ListCommand(ClientImpl client, String dir) {
        this.client = client;
        this.dir = dir;
    }

    public ListCommand(ClientImpl client) {
        this.client = client;
        this.dir = "";
    }

    private String log = "ListCommand not execute";
    private Pattern pattern = Pattern.compile("^\\s*LIST(\\s+\\w+\\s*)|(s*)$",
            Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);

    @Override
    public void execute() {
        PathChecker pathChecker = new PathChecker(this.dir);
        if(!pathChecker.isValid()){
            //TODO: Ошибка в случаи не валидного пути
        }
        String response = null;
        try {
            response = Catalog.print(client.getUserWorkingDirectory()+this.dir);
        } catch (Exception e) {
            e.printStackTrace();
            //TODO: Ошибка если передан файл
        }
        try {
            client.sendLine(response + "\n" + new Code250().getAll());
            this.log = "ListCommand execute";
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
