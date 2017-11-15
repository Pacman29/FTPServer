package FtpServer.Commands;

import java.util.regex.Pattern;

public interface ICommand {
    public String execute();

    public String getLogInfo();

    public Pattern getCommandPattern();
}
