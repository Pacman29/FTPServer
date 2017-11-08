package FtpServer.Commands;

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
    public void execute() {
        PathChecker pathChecker = new PathChecker(this.dir);
        if (!pathChecker.isValid()) {
            //TODO: Ошибка в случаи не валидного пути
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