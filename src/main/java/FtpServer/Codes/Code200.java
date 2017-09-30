package FtpServer.Codes;

public class Code200 implements CodeImpl{
    @Override
    public String code() {
        return "200";
    }

    @Override
    public String discription() {
        return "Command okay.";
    }

    @Override
    public String getAll() {
        return code()+" "+discription()+"\n";
    }
}
