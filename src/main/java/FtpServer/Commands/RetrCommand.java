package FtpServer.Commands;

import FtpServer.Codes.Code150;
import FtpServer.IClient;
import FtpServer.Modules.PathChecker;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

public class RetrCommand implements ICommand {
    private IClient client;
    private String dir;

    public RetrCommand(IClient client, String dir) {
        this.client = client;
        this.dir = dir;
    }

    private String log = "RetrCommand not execute";
    private Pattern pattern = Pattern.compile("^\\s*RETR\\s+\\w+\\s*$",
            Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);

    @Override
    public void execute() {
        PathChecker pathChecker = new PathChecker(this.dir);
        if(!pathChecker.isValid()){
            //TODO: Ошибка в случаи не валидного пути
        }
        String response = null;
        File file = new File(client.getUserWorkingDirectory() + this.dir);
        if(file.isDirectory()){
            // TODO : обработать случай с переданной директорией
        }
        if(file.isFile()){
            if(!client.sendFile(file)){
                // TODO : обработать случай ошибки передачи файла
            }
            String res = null;
            switch (client.getSocketType()) {
                case ASCII:{
                    res = String.format(new Code150().getAll(),"ASCII");
                    break;
                }
                case BINARY:{
                    res = String.format(new Code150().getAll(),"BINARY");
                    break;
                }
            }
            try {
                client.sendLine(res);
            } catch (IOException e) {
                e.printStackTrace();
                //TODO: Ошибка при отправлении сообщения
            }
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
