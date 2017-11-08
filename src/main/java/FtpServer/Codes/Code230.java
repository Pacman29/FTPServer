package FtpServer.Codes;

public class Code230 extends BaseCode {
    @Override
    public String code() {
        return "230";
    }

    @Override
    public String discription() {
        return "User logged in, proceed.";
    }

}
