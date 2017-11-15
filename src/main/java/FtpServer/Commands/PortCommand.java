package FtpServer.Commands;

import FtpServer.IClient;
import FtpServer.Codes.Code200;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PortCommand implements ICommand {
    private IClient client;
    private String address;

    public PortCommand(IClient client, String address) {
        this.client = client;
        this.address = address;
    }

    private String log = "PortCommand not execute";
    private Pattern pattern = Pattern.compile("^\\s*PORT\\s+[0-9]+(?:\\.[0-9]+){3}:[0-9]+\\s*$",
            Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);


    @Override
    public String execute() {
            Pattern pat = Pattern.compile("[0-9]+(?:\\.[0-9]+){3}");
            Matcher matcher = pat.matcher(address);
            String host = null;
            if (matcher.find()) {
                host = matcher.group(1);
            } else {
                //TODO: ошибка в случае, если в строке не было хоста
            }

            pat = Pattern.compile(":[0-9]+");
            matcher = pat.matcher(address);
            Integer port = null;
            if(matcher.find()){
                port = Integer.valueOf(matcher.group(1).substring(1));
            } else {
                //TODO: ошибка в случае, если в строке нет порта
            }

        try {
            client.openDataSocket(host, port);
        } catch (IOException e) {
            //TODO: ошибка если не удалось открыть сокет данных
        }
        this.log = "PortCommand execute";
            return new Code200().getAll();
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
