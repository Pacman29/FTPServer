package FtpServer.Codes;

public class Code331 extends BaseCode {
    @Override
    public String code() {
        return "331";
    }

    @Override
    public String discription() {
        return "User name okay, need password.";
    }
}
