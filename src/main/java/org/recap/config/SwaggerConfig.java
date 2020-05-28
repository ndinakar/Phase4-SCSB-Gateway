package org.recap.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * @author Sheik Sahib on 5/28/20
 */
@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig extends SwaggerConfigBase {

    @Autowired
    private Environment environment;

    /**
     * Get the {@link UiConfiguration}.
     *
     * @return the {@code UiConfiguration}.
     */
    @Bean
    public UiConfiguration uiConfig() {
        String[] list = {"get", "post", "put", "delete"};
        String validatorUrl = null;
        return new UiConfiguration(validatorUrl, list);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ApiInfo apiInfo() {
        List<String> environmentList = new ArrayList<>(Arrays.asList(environment.getActiveProfiles()));
        environmentList.remove("${com.pgac.env.type}");
        environmentList.remove("${com.pgac.env.loc}");
        environmentList.remove("${com.pgac.env.instance}");

        String title = "ReCAP APIs";

        return new ApiInfoBuilder().title(title).description("APIs to interact with ReCAP middleware are RESTful and need an API_KEY for any call to be invoked. Further NCIP protocols are also supported.")
                .version("1.0.0")
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html").build();
    }

    @Bean
    public Docket appApi() {
        AuthorizationScope[] authScopes = new AuthorizationScope[1];
        authScopes[0] = new AuthorizationScopeBuilder().scope("global").description("full access").build();
        SecurityReference securityReference = SecurityReference.builder().reference("API Key")
                .scopes(authScopes).build();

        ArrayList<SecurityContext> securityContexts = newArrayList(
                SecurityContext.builder().securityReferences(newArrayList(securityReference)).build());
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.recap.controller.swagger"))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .securitySchemes(newArrayList(apiKey()))
                .securityContexts(securityContexts)
                .apiInfo(apiInfo());
    }

    private ApiKey apiKey() {
        return new ApiKey("API Key", "api_key", "header");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SwaggerInterceptor())
                .addPathPatterns("/sharedCollection/*")
                .addPathPatterns("/requestItem/*")
                .addPathPatterns("/searchService/*")
                .addPathPatterns("/dataDump/*");
    }
}
