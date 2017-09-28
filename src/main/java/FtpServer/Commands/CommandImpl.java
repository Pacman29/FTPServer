package FtpServer.Commands;

import java.util.regex.Pattern;

public interface CommandImpl {
    public void execute();

    public String getLogInfo();

    public Pattern getCommandPattern();
}
