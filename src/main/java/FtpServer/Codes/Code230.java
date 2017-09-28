package FtpServer.Codes;

public class Code230 implements CodeImpl{
    @Override
    public String code() {
        return "230";
    }

    @Override
    public String discription() {
        return "User logged in, proceed.";
    }
}
