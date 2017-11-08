package FtpServer.Codes;

public class BaseCode implements ICode{

    @Override
    public String code() {
        return null;
    }

    @Override
    public String discription() {
        return null;
    }

    @Override
    public String getAll() {
        return code()+" "+discription()+"\r\n";
    }
}
