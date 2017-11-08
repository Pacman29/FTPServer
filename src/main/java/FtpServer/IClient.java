package FtpServer;

import FtpServer.Modules.FileSenderFabric;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;

public interface IClient {
    DataInputStream getInputStream();

    DataOutputStream getOutputStream();

    void sendLine(String line) throws IOException;

    String waitLine() throws IOException;

    void closeConnection() throws IOException;

    String getUserWorkingDirectory();

    void setUserWorkingDirectory(String userWorkingDirectory);

    void openDataSocket(String host, Integer port) throws IOException;

    void closeDataSocket() throws IOException;

    boolean sendFile(File file);

    public FileSenderFabric.SocketType getSocketType();

    public void setSocketType(FileSenderFabric.SocketType socketType);
}
