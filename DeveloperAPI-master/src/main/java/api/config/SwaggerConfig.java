package api.config;


import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;
import java.util.Optional;


/*
 * @Author : NozjkoiTop
 * @Date : Created in 12.02.2022
 **/


@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket restApi() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())//
                .securityContexts(List.of(securityContext()))//
                .securitySchemes(List.of(apiKey()))//
                .select()//
                .apis(RequestHandlerSelectors.any())//
                .paths(Predicates.not(PathSelectors.regex("/error")))
                .build()//
                .useDefaultResponseMessages(false)//
                .tags(new Tag("developers", "Operations about developers"))//
                .genericModelSubstitutes(Optional.class);

    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Developers api")
                .contact(new Contact("NozjkoiTop", "https://t.me/ruchkoihlop", "mikhailsviatohorof@gmail.com"))
                .version("1.0")
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("Auth", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.any())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return List.of(new SecurityReference("Authorization", authorizationScopes));
    }

}
