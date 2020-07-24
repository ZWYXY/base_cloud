package ogr.spring.netty;


import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class DemoTest {

    @Test
    public void ioTest() throws IOException {
        InputStream in = new FileInputStream("a.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));

        int len; char[] chars = new char[1024];
        while ((len = bufferedReader.read(chars)) != -1) {
            System.err.println(new String(chars, 0, len));
        }
    }


}
