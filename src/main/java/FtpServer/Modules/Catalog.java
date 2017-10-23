package FtpServer.Modules;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Catalog {
    public static String print(String dir) throws Exception {
        File folder = new File(dir);
        if(folder.isFile()){
            throw new Exception("Is not a directory");
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (final File fileEntry : folder.listFiles()) {
            stringBuilder.append(fileEntry.getName()).append("\r\n");
        }
        return stringBuilder.toString();
    }

    public static boolean isExist(String dir) throws Exception {
        File folder = new File(dir);
        if(folder.isFile()){
            throw new Exception("Is not a directory");
        }
        
    }
}
