package api;

import api.controller.DevController;
import io.swagger.annotations.ApiResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class DemoApplicationTest {

    @Autowired
    private DevController devController;

    @Test
    void contextLoads() {
        assertThat(devController).isNotNull();
    }

}
