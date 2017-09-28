import FtpServer.FtpServer;

public class Main {
    public static void main(String[] args) {
        FtpServer ftp = new FtpServer(6666);
        ftp.go();
    }
}
