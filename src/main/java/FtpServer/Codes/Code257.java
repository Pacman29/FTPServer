package FtpServer.Codes;
public class Code257 implements CodeImpl{
    @Override
    public String code() {
        return "257";
    }

    @Override
    public String discription() {
        return "%s\n";
    }

    @Override
    public String getAll() {
        return code()+" "+discription();
    }
}
