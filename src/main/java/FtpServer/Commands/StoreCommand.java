package FtpServer.Commands;

import FtpServer.Codes.Code150;
import FtpServer.IClient;
import FtpServer.Modules.DataConnection;
import FtpServer.Modules.PathChecker;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

public class StoreCommand implements ICommand {
    private IClient client;
    private String dir;

    public StoreCommand(IClient client, String dir) {
        this.client = client;
        this.dir = dir;
    }

    private String log = "StoreCommand not execute";
    private Pattern pattern = Pattern.compile("^\\s*STOR\\s+\\w+\\s*$",
            Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);

    @Override
    public String execute() {
        PathChecker pathChecker = new PathChecker(this.dir);
        if(!pathChecker.isValid()){
            //TODO: Ошибка в случаи не валидного пути
            return "ERROR";
        }
        String response = null;
        File file = new File(client.getUserWorkingDirectory() + this.dir);
        if(file.isDirectory()){
            // TODO : обработать случай с переданной директорией
            return "ERROR";
        } else {
            if(!client.saveFile(file)){
                // TODO : обработать случай ошибки передачи файла
            }
            String res = String.format(new Code150().getAll(),
                    (client.getSocketType() == DataConnection.SocketType.ASCII) ? "ASCII" : "BINARY");
            this.log = "RetrCommand execute";
            return res;
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
