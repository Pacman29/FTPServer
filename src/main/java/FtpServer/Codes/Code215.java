package FtpServer.Codes;

public class Code215 implements CodeImpl{
    @Override
    public String code() {
        return "215";
    }

    @Override
    public String discription() {
        return "UNIX system type\n";
    }

    @Override
    public String getAll() {
        return code()+" "+discription()+"\n";
    }
}
