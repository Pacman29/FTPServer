package FtpServer;

import FtpServer.Modules.DataConnection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.*;

public class Client implements IClient {
    private DataConnection.SocketType socketType;
    private Socket socket;
    private Socket dataSocket = null;
    private String userRootDirectory;
    private String userWorkingDirectory;

    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    public Client(Socket socket, String rootDirectory) throws IOException {
        this.socket = socket;
        this.userRootDirectory = rootDirectory;
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

    @Override
    public void openDataSocket(String host, Integer port) throws IOException, UnknownHostException {
        if(host == null || port == null){
            //TODO: ошибка в случае если данные null
        }

        if(dataSocket != null && dataSocket.isBound()) {
            this.closeDataSocket();
        }

        InetAddress inetAddress = InetAddress.getByName(host);
        this.dataSocket = new Socket(host,port);
    }

    @Override
    public void closeDataSocket() throws IOException {
        dataSocket.close();
        this.dataSocket = null;
    }

    @Override
    public boolean sendFile(File file) {
        DataConnection.IFileSenderFabric fileSenderFabric = DataConnection.FileSenderFabric.getInstance();
        DataConnection.IFileSender fileSender = fileSenderFabric.get(this.socketType);
        if(fileSender.sendFile(this.dataSocket,file) == 0){
            return true;
        } else {
            return false;
        }
    }

    public DataConnection.SocketType getSocketType() {
        return socketType;
    }

    public void setSocketType(DataConnection.SocketType socketType) {
        this.socketType = socketType;
    }

    @Override
    public boolean saveFile(File file) {
        DataConnection.IFileSaverFabric fileSaverFabric = DataConnection.FileSaverFabric.getInstance();
        DataConnection.IFileSaver fileSender = fileSaverFabric.get(this.socketType);
        if(fileSender.saveFile(this.dataSocket,file) == 0){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getUserRootDirectory() {
        return userRootDirectory;
    }

    @Override
    public void sendLineToDataSocket(String line) throws IOException {
        DataOutputStream outputStream = new DataOutputStream(this.dataSocket.getOutputStream());
        outputStream.writeUTF(line);
        outputStream.flush();
    }
}
