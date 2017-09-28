package FtpServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public interface ClientImpl {
    public DataInputStream getInputStream();

    public DataOutputStream getOutputStream();

    public void sendLine(String line) throws IOException;

    public String waitLine() throws IOException;

    public void closeConnection() throws IOException;
}
