package FtpServer.Modules;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;

public class DataConnection {
    public static enum SocketType {ASCII,BINARY};

    public static interface IFileSender {
        int sendFile(Socket socket, File file);
    }

    public static interface IFileSenderFabric {
        IFileSender get(SocketType type);

        IFileSender getASCII();

        IFileSender getBINARY();
    }

    public static class FileSenderASCII implements IFileSender {
        @Override
        public int sendFile(Socket socket, File file) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] fileData = new byte[4096];
                Arrays.fill(fileData, (byte) 0);
                while (fileInputStream.read(fileData) != -1){
                    socket.getOutputStream().write(fileData);
                    Arrays.fill(fileData, (byte) 0);
                }
                //TODO : FileSenderBinary 0xFF  + 2?

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                //TODO : обработать ошибку
            } catch (IOException e) {
                e.printStackTrace();
                //TODO : обработать ошибку
            }
            return 0;
        }
    }

    public static class FileSenderBinary implements IFileSender {
        @Override
        public int sendFile(Socket socket, File file) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] fileData = new byte[4096];
                Arrays.fill(fileData, (byte) 0);
                while (fileInputStream.read(fileData) != -1){
                    socket.getOutputStream().write(fileData);
                    Arrays.fill(fileData, (byte) 0);
                }
                //TODO : FileSenderBinary 0xFF  + 2?

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                //TODO : обработать ошибку
            } catch (IOException e) {
                e.printStackTrace();
                //TODO : обработать ошибку
            }
            return 0;
        }
    }

    public static class FileSenderFabric implements IFileSenderFabric {
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
                    return getBINARY();
                default:
                    return null;
            }
        }

        public IFileSender getASCII(){
            return new FileSenderASCII();
        }

        public IFileSender getBINARY(){
            return new FileSenderBinary();
        }
    }

    public interface IFileSaverFabric{
        IFileSaver get(SocketType type);

        IFileSaver getASCII();

        IFileSaver getBINARY();
    }

    public static class FileSaverFabric implements IFileSaverFabric {
        private static volatile FileSaverFabric instance;

        private FileSaverFabric() {
        }

        public static IFileSaverFabric getInstance() {
            FileSaverFabric localInstance = FileSaverFabric.instance;
            if (localInstance == null) {
                synchronized (FileSaverFabric.class) {
                    localInstance = FileSaverFabric.instance;
                    if (localInstance == null) {
                        FileSaverFabric.instance = localInstance = new FileSaverFabric();
                    }
                }
            }
            return localInstance;
        }


        @Override
        public IFileSaver get(SocketType type) {
            switch (type) {
                case ASCII:
                    return getASCII();
                case BINARY:
                    return getBINARY();
                default:
                    return null;
            }
        }

        @Override
        public IFileSaver getASCII() {
            return new FileSaverASCII();
        }

        @Override
        public IFileSaver getBINARY() {
            return new FileSaverBINARY();
        }
    }

    public static interface IFileSaver {
        int saveFile(Socket socket, File file);
    }

    public static class FileSaverASCII implements IFileSaver{

        @Override
        public int saveFile(Socket socket, File file) {
            try {
                FileOutputStream fileOutputStream= new FileOutputStream(file);
                byte[] fileData = new byte[4096];
                Arrays.fill(fileData, (byte) 0);
                while (socket.getInputStream().read(fileData) != -1){
                    fileOutputStream.write(fileData);
                    Arrays.fill(fileData, (byte) 0);
                }
                //TODO : FileSenderBinary 0xFF  + 2?

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                //TODO : обработать ошибку
            } catch (IOException e) {
                e.printStackTrace();
                //TODO : обработать ошибку
            }
            return 0;
        }
    }

    public static class FileSaverBINARY implements IFileSaver{

        @Override
        public int saveFile(Socket socket, File file) {
            try {
                FileOutputStream fileOutputStream= new FileOutputStream(file);
                byte[] fileData = new byte[4096];
                Arrays.fill(fileData, (byte) 0);
                while (socket.getInputStream().read(fileData) != -1){
                    fileOutputStream.write(fileData);
                    Arrays.fill(fileData, (byte) 0);
                }
                //TODO : FileSenderBinary 0xFF  + 2?

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                //TODO : обработать ошибку
            } catch (IOException e) {
                e.printStackTrace();
                //TODO : обработать ошибку
            }
            return 0;
        }
    }

}
