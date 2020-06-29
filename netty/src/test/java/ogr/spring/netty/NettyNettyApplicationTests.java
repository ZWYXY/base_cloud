package ogr.spring.netty;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NettyNettyApplicationTests {

    @Value("${local.netty.client-port}")
    private Integer port;

    @Test
    void contextLoads() {
        System.err.println(port);
    }

}
