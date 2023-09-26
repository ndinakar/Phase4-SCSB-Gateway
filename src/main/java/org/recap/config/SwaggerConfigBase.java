package org.recap.config;

import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Sheik Sahib on 5/28/20
 */
abstract class SwaggerConfigBase implements WebMvcConfigurer {

    protected static final String ERROR_INFORMATION_CLASS = "ErrorInfo";
    protected static final String INTERNAL_SERVER_ERROR_MESSAGE = "Internal Server Error";
    protected static final String GONE_ERROR_MESSAGE = "Gone";
    protected static final String NOT_FOUND_ERROR_MESSAGE = "Not Found";
    protected static final String BAD_REQUEST_ERROR_MESSAGE = "Bad Request";

    public static String getBadRequestErrorMessage() {
        return BAD_REQUEST_ERROR_MESSAGE;
    }

    @Bean
    protected abstract Info apiInfo();
}

