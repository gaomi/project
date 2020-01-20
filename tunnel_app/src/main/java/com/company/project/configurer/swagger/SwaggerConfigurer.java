package com.company.project.configurer.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Mybatis & Mapper & PageHelper 配置
 */
@Configuration
@EnableSwagger2
public class SwaggerConfigurer {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.company.project.module"))
                .paths(PathSelectors.any())
                .build();
    }
//C:\Users\Administrator\.m2\repository\org\webjars\bower\github-com-novaeye-jquery-easyui-bower\1.5.0.1\github-com-novaeye-jquery-easyui-bower-1.5.0.1.jar!\META-INF\resources\webjars\github-com-novaeye-jquery-easyui-bower\1.5.0.1\jquery.easyui.min.js
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建RESTful API")
                .description("rest api 文档构建利器")
                .termsOfServiceUrl("http://www.paodingsoft.com")
                .contact(new Contact("paoding", "http://www.paodingsoft.com", "fuwu@paodingsoft.com"))
                .version("1.0")
                .build();
    }

}


