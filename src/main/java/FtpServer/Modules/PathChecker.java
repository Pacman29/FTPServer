package FtpServer.Modules;

import com.sun.deploy.util.StringUtils;

import java.lang.reflect.Array;
import java.util.*;

public class PathChecker {
    private String realPath;
    private String shortPath = null;

    public PathChecker(String path) {
        this.realPath = path;
    }

    private String getShorterPath(){
        LinkedList files = new LinkedList(Arrays.asList(this.realPath.split("/")));
        for (int i = 0; i< files.size()-1;){
            if(!files.get(i).equals("..") && files.get(i+1).equals("..") ){
                files.subList(i, i+2).clear();
                i -= (i > 0) ? 1 : 0;
                continue;
            }
            i++;
        }
        return StringUtils.join(files,"/");
    }

    public String getShortPath() {
        if(this.shortPath == null){
            this.shortPath = this.getShorterPath();
        }
        return this.shortPath;
    }

    public boolean isValid(){
        this.getShortPath();
        return !this.shortPath.startsWith("..");
    }
}
