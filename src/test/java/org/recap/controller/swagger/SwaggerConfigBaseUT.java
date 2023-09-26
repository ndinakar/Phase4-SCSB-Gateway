package org.recap.controller.swagger;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.recap.BaseTestCase;
import org.recap.config.SwaggerConfig;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Charan Raj C created on 02/05/23
 */
public class SwaggerConfigBaseUT extends BaseTestCase {

    @InjectMocks
    SwaggerConfig swaggerConfig;
    private static final String BAD_REQUEST_ERROR_MESSAGE = "Bad Request";

    @Test
    public void testGetBadRequestErrorMessage() {
        String errorMessage = swaggerConfig.getBadRequestErrorMessage();
        assertEquals(BAD_REQUEST_ERROR_MESSAGE, errorMessage);
    }
}
