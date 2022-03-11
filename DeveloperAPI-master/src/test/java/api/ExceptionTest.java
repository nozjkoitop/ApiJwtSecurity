package api;

import api.model.Developer;
import api.repository.DeveloperRepository;
import api.service.DeveloperService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.context.WebApplicationContext;


import static org.mockito.BDDMockito.given;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)

public class ExceptionTest {

    private MockMvc mockMvc = null;
    private static Developer developer;
    @Autowired
    private static DeveloperRepository developerRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeAll
    static void setUp() {
        developer = new Developer(1L, "Misha", "nozjkoitop@mail.ru");
        developerRepository.save(developer);
    }

    @Before
    public void before() throws Exception {
        mockMvc = webAppContextSetup(webApplicationContext)
                .build();
    }

    @After
    public void after() throws Exception {
        mockMvc = null;
    }


    @Test
    public void testProductFound() throws Exception {
        final MockHttpServletRequestBuilder builder = get("/api/developers/{id}", 1);
        final ResultActions result = mockMvc.perform(builder);
        result.andExpect(status().isOk());
    }
    @Test
    public void testProductNotFound() throws Exception {
        final MockHttpServletRequestBuilder builder = get("/api/developers/{id}", 555);
        final ResultActions result = mockMvc.perform(builder);
        result.andExpect(status().isNotFound());
    }

}

