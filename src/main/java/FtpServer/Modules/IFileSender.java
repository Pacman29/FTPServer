package FtpServer.Modules;

import java.io.File;
import java.net.Socket;

public interface IFileSender {
    int sendFile(Socket socket, File file);
}
