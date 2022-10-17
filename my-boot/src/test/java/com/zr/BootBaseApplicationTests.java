package com.zr;

import com.zr.service.TestUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BootBaseApplicationTests {

    @Autowired
    private TestUserService testUserService;

    @Test
    void contextLoads() {
        testUserService.queryAssociationById(1L);
    }

}
