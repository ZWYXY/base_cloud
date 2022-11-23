package com.mirco.remoteservice;

import com.mirco.remoteservice.pojo.dos.UserDO;
import com.zr.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class RemoteServiceApplicationTests {

    @Resource
    private UserMapper userMapper;

    @Test
    void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<UserDO> userList = userMapper.selectList(null);
//        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

}
