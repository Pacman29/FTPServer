package FtpServer.Modules;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PathCheckerTest {
    @Test
    public void test1(){
        String path = "ere/erer/rer/er/../../../";
        PathChecker pathChecker = new PathChecker(path);
        System.out.println(pathChecker.getShortPath());
        assertSame(true,pathChecker.isValid());
    }

    @Test
    public void test2(){
        String path = "../../../";
        PathChecker pathChecker = new PathChecker(path);
        System.out.println(pathChecker.getShortPath());
        assertSame(false,pathChecker.isValid());
    }

    @Test
    public void test3(){
        String path = "";
        PathChecker pathChecker = new PathChecker(path);
        System.out.println(pathChecker.getShortPath());
        assertSame(true,pathChecker.isValid());
    }

    @Test
    public void test4(){
        String path = " ";
        PathChecker pathChecker = new PathChecker(path);
        System.out.println(pathChecker.getShortPath());
        assertSame(true,pathChecker.isValid());
    }
    @Test
    public void test5(){
        String path = "eee/eee/../rere/../";
        PathChecker pathChecker = new PathChecker(path);
        System.out.println(pathChecker.getShortPath());
        assertSame(true,pathChecker.isValid());
    }

    @Test
    public void test6(){
        String path = "eee/eee/../rere/../../..";
        PathChecker pathChecker = new PathChecker(path);
        System.out.println(pathChecker.getShortPath());
        assertSame(false,pathChecker.isValid());
    }
}