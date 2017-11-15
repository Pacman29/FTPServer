package FtpServer.Commands;

import FtpServer.Codes.Code250;
import FtpServer.Codes.Code501;
import FtpServer.IClient;
import FtpServer.Modules.PathChecker;

import java.util.regex.Pattern;

public class CwdCommand implements ICommand {
    private IClient client;
    private String dir;

    public CwdCommand(IClient client, String dir) {
        this.client = client;
        this.dir = dir;
    }

    public CwdCommand(IClient client) {
        this.client = client;
        this.dir = "";
    }

    private String log = "CwdCommand not execute";
    private Pattern pattern = Pattern.compile("^\\s*CWD(\\s+\\w+\\s*)$",
            Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);

    @Override
    public String execute() {
        String reqDir = this.dir;
        if(this.dir.equals("..")){
            String newDir = client.getUserWorkingDirectory();
            newDir = newDir.substring(0,newDir.lastIndexOf("/"));
            if(client.getUserWorkingDirectory().contains(newDir)){
                // TODO : переход в закрытые директории
                return new Code501().getAll();
            } else {
                return new Code250().getAll();
            }

        } else {
            PathChecker pathChecker = new PathChecker(this.dir);
            if (!pathChecker.isValid()) {
                //TODO: Ошибка в случаи не валидного пути
            }
            client.setUserWorkingDirectory(client.getUserWorkingDirectory()+reqDir);
            return new Code250().getAll();
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