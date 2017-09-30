package FtpServer.Codes;

public class Code331 implements CodeImpl{
    @Override
    public String code() {
        return "331";
    }

    @Override
    public String discription() {
        return "User name okay, need password.";
    }

    @Override
    public String getAll() {
        return code()+" "+discription()+"\n";
    }
}
