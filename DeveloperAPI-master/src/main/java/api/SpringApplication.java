package api;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @Author : NozjkoiTop
 * @Date : Created in 12.02.2022
 */

@EnableJpaRepositories("api.repository")
@EntityScan("api.model")
@SpringBootApplication
@EnableSwagger2

public class SpringApplication {

    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(SpringApplication.class, args);
    }



}