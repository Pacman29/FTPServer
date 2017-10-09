package FtpServer.Commands;

import FtpServer.ClientImpl;
import FtpServer.Codes.Code200;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PortCommand implements CommandImpl{
    private ClientImpl client;
    private String address;

    public PortCommand(ClientImpl client, String address) {
        this.client = client;
        this.address = address;
    }

    private String log = "PortCommand not execute";
    private Pattern pattern = Pattern.compile("^\\s*PORT\\s+[0-9]+(?:\\.[0-9]+){3}:[0-9]+\\s*$",
            Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);


    @Override
    public void execute() {
        try {
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

            client.openDataSocket(host, port);
            client.sendLine(new Code200().getAll());
            this.log = "PortCommand execute";
        } catch (IOException e) {
            // TODO: обработать ошибку в случае не удачи отправки сообщения клиенту
            e.printStackTrace();
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
