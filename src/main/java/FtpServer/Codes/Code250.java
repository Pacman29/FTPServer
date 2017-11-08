package FtpServer.Codes;

public class Code250 extends BaseCode {
    @Override
    public String code() {
        return "250";
    }

    @Override
    public String discription() {
        return "Requested file action okay, completed.";
    }
}
