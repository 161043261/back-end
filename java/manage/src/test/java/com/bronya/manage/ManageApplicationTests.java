package com.bronya.manage;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class ManageApplicationTests {

    @Test
    void testUUID() {
        System.out.println(UUID.randomUUID().toString());
    }

}
