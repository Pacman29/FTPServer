package FtpServer.Codes;

public class Code501 implements CodeImpl {
    @Override
    public String code() {
        return "501";
    }

    @Override
    public String discription() {
        return "Syntax error in parameters or arguments.";
    }

    @Override
    public String getAll() {
        return code()+" "+discription()+"\n";
    }
}
