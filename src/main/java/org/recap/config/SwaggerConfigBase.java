package org.recap.config;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * @author Sheik Sahib on 5/28/20
 */
abstract class SwaggerConfigBase extends WebMvcConfigurerAdapter {

    protected static String ERROR_INFORMATION_CLASS = "ErrorInfo";
    protected static String INTERNAL_SERVER_ERROR_MESSAGE = "Internal Server Error";
    protected static String GONE_ERROR_MESSAGE = "Gone";
    protected static String NOT_FOUND_ERROR_MESSAGE = "Not Found";
    protected static String BAD_REQUEST_ERROR_MESSAGE = "Bad Request";

    private final ResponseMessage INTERNAL_SERVER_ERROR_RESPONSE_MESSAGE =
            new ResponseMessageBuilder().code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message(INTERNAL_SERVER_ERROR_MESSAGE).responseModel(new ModelRef(ERROR_INFORMATION_CLASS)).build();

    private final ResponseMessage GONE_ERROR_RESPONSE_MESSAGE =
            new ResponseMessageBuilder().code(HttpStatus.GONE.value()).message(GONE_ERROR_MESSAGE)
                    .responseModel(new ModelRef(ERROR_INFORMATION_CLASS)).build();

    private final ResponseMessage NOT_FOUND_RESPONSE_MESSAGE =
            new ResponseMessageBuilder().code(HttpStatus.NOT_FOUND.value()).message(NOT_FOUND_ERROR_MESSAGE)
                    .responseModel(new ModelRef(ERROR_INFORMATION_CLASS)).build();

    private final ResponseMessage BAD_REQUEST_RESPONSE_MESSAGE =
            new ResponseMessageBuilder().code(HttpStatus.BAD_REQUEST.value()).message(BAD_REQUEST_ERROR_MESSAGE)
                    .responseModel(new ModelRef(ERROR_INFORMATION_CLASS)).build();

    private final List<ResponseMessage> RESPONSE_MESSAGE_LIST =
            newArrayList(INTERNAL_SERVER_ERROR_RESPONSE_MESSAGE, GONE_ERROR_RESPONSE_MESSAGE, NOT_FOUND_RESPONSE_MESSAGE,
                    BAD_REQUEST_RESPONSE_MESSAGE);

    @Autowired
    private TypeResolver typeResolver;

    /**
     * Get the {@link Docket} API.
     *
     * @return Docket.
     */
    @Bean
    @Required
    protected abstract ApiInfo apiInfo();
}

