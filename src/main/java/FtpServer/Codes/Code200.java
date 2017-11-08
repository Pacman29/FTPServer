package FtpServer.Codes;

public class Code200 extends BaseCode {
    @Override
    public String code() {
        return "200";
    }

    @Override
    public String discription() {
        return "Command okay.";
    }
}
