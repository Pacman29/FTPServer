package FtpServer.Modules;

import org.junit.jupiter.api.Test;

class CatalogPrinterTest {
    @Test
    public void test1(){
        try {
            System.out.println(Catalog.print("/home/pacman29/"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}