package FtpServer.Modules;

public interface IFileSenderFabric {
    enum SocketType {ASCII,BINARY}
    IFileSender get(SocketType type);

    IFileSender getASCII();

    IFileSender getBINARI();
}
