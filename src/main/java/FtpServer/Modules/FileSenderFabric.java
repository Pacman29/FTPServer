package FtpServer.Modules;

public class FileSenderFabric implements IFileSenderFabric {
    private static volatile FileSenderFabric instance;

    private FileSenderFabric() {
    }

    public static IFileSenderFabric getInstance() {
        FileSenderFabric localInstance = FileSenderFabric.instance;
        if (localInstance == null) {
            synchronized (FileSenderFabric.class) {
                localInstance = FileSenderFabric.instance;
                if (localInstance == null) {
                    FileSenderFabric.instance = localInstance = new FileSenderFabric();
                }
            }
        }
        return localInstance;
    }


    public IFileSender get(SocketType type){
        switch (type) {
            case ASCII:
                return getASCII();
            case BINARY:
                return getBINARI();
            default:
                return null;
        }
    }

    public IFileSender getASCII(){
        return new FileSenderASCII();
    }

    public IFileSender getBINARI(){
        return new FileSenderBinary();
    }
}
