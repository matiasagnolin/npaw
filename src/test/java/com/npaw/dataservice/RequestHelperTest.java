package com.npaw.dataservice;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public abstract class RequestHelperTest extends BaseTest{
    @Autowired
    private MockMvc mockMvc;

    public void assertResponse(String endPoint
            , Integer expectedStatus, String method, MultiValueMap<String,String> param)
            throws Exception {
        mockMvc
                .perform(request(HttpMethod.valueOf(method), endPoint)
                .params(param))
                .andExpect(status().is(expectedStatus));
        //        .andExpect(content().contentType(MediaType.APPLICATION_XML_VALUE));
    }
}
