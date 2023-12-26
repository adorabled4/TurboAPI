package com.dhx.apicore;

import com.dhx.apicore.model.DO.UserEntity;
import com.dhx.apicore.service.InterfaceInfoService;
import com.dhx.apicore.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class ApiCoreApplicationTests {

    @Resource
    InterfaceInfoService interfaceInfoService;

    @Resource
    UserService userService;

    @Test
    public void Test(){
        List<UserEntity> list = userService.list();
        System.out.println(list);
    }
    
}
