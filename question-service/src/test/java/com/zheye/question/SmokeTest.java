package com.zheye.question;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class SmokeTest {
    @Autowired
    private MockMvc mockMvc;

    public SmokeTest() {
    }

    @Test
    void one_plus_noe_should_equals_two() {
        Assertions.assertEquals(2, 2);
    }

    @Test
    void should_return_ok_when_request_endpoints_of_health() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/actuator/health", new Object[0]))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", new Object[0]).value("UP"));
    }
}
