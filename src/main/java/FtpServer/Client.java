package FtpServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client implements ClientImpl {
    private Socket socket;
    private String userRootDirectory;
    private String userWorkingDirectory;

    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    public Client(Socket socket) throws IOException {
        this.socket = socket;

        this.inputStream = new DataInputStream(socket.getInputStream());
        this.outputStream = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public DataInputStream getInputStream() {
        return inputStream;
    }

    @Override
    public DataOutputStream getOutputStream() {
        return outputStream;
    }

    @Override
    public void sendLine(String line) throws IOException {
        this.outputStream.writeUTF(line);
        this.outputStream.flush();
    }

    @Override
    public String waitLine() throws IOException {
        return this.inputStream.readUTF();
    }

    @Override
    public void closeConnection() throws IOException {
        this.socket.close();
    }

    @Override
    public String getUserWorkingDirectory() {
        return userWorkingDirectory;
    }

    @Override
    public void setUserWorkingDirectory(String userWorkingDirectory) {
        this.userWorkingDirectory = userWorkingDirectory;
    }
}
