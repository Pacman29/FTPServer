package FtpServer.Commands;

import java.util.regex.Pattern;

public interface ICommand {
    public void execute();

    public String getLogInfo();

    public Pattern getCommandPattern();
}
