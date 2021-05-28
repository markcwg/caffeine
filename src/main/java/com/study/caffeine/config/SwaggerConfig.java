package com.study.caffeine.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 *
 * @author : markcwg
 * @date : 2021/5/12 16:51
 */
@EnableSwagger2WebMvc
@EnableKnife4j
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.study.caffeine.web"))
                .paths(PathSelectors.any())
                .build()
                .groupName("caffeine学习测试接口");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("caffeine学习测试接口")
                .contact(new Contact("caiwg", "", "caiwg@sucsoft.com"))
                .version("1.0")
                .build();
    }

}
