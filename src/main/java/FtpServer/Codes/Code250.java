package FtpServer.Codes;

public class Code250 implements CodeImpl{
    @Override
    public String code() {
        return "250";
    }

    @Override
    public String discription() {
        return "Requested file action okay, completed.\n";
    }

    @Override
    public String getAll() {
        return code()+" "+discription();
    }
}
