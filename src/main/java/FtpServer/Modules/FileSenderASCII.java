package FtpServer.Modules;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

public class FileSenderASCII implements IFileSender{
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
