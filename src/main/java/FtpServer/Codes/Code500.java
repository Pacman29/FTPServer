package FtpServer.Codes;

public class Code500 extends BaseCode {
    @Override
    public String code() {
        return "500";
    }

    @Override
    public String discription() {
        return "Syntax error, command unrecognized. This may include errors such as command line too long.";
    }
}
