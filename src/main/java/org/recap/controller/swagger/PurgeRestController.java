package org.recap.controller.swagger;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.recap.ScsbConstants;
import org.recap.controller.AbstractController;
import org.slf4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

;

/**
 * Created by hemalathas on 10/4/17.
 */
@Slf4j
@RestController
@RequestMapping("/purgeRestController")
public class PurgeRestController extends AbstractController {


    /**
     * Gets logger.
     *
     * @return the logger
     */
    public Logger getLogger() {
        return log;
    }

    /**
     *This method will call scsb-circ microservice to remove patron's email address from scsb database after
     *configured day in the external properties
     * @return the response entity
     */
    @GetMapping(value = "/purgeEmailAddress")
    @Operation(summary = "purgeEmailAddress",description = "The Purge email address API as the name suggests purges all email addresses of patrons stored in SCSB database as part of request information. This API is internally used to purge emails through a scheduled job at regular intervals to remove Patron identifying information from SCSB.",  operationId = "0")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity purgeEmailAddress(){
        ResponseEntity<Map> responseEntity = null;
        Map response = null;
        try{
            HttpEntity requestEntity = getHttpEntity();
            responseEntity = restTemplate.exchange(getScsbCoreUrl()+ ScsbConstants.REST_URL_PURGE_EMAIL_ADDRESS, HttpMethod.GET,requestEntity,Map.class);
            response = responseEntity.getBody();
        }catch(Exception e){
            getLogger().error("Exception",e);
        }

        return new ResponseEntity<>(response,getHttpHeaders(),HttpStatus.OK);
    }

    /**
     * This method will call scsb-circ microservice to remove exception request in the scsb database.
     *
     * @return the response entity
     */
    @GetMapping(value = "/purgeExceptionRequests")
    @Operation(summary = "purgeExceptionRequests",description = "The Purge exception requests API as the name suggests purges all requests that have gone to an exception status in SCSB database. This API is internally used by SCSB to purge requests with status as exception through a scheduled job at regular intervals.",  operationId = "0")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity purgeExceptionRequests() {
        Map response = null;
        try {
            HttpEntity requestEntity = getHttpEntity();
            ResponseEntity<Map> responseEntity = restTemplate.exchange(getScsbCoreUrl() + ScsbConstants.REST_URL_PURGE_EXCEPTION_REQUESTS, HttpMethod.GET, requestEntity, Map.class);
            response = responseEntity.getBody();
        } catch (Exception e) {
            getLogger().error("Exception", e);
        }
        return new ResponseEntity<>(response, getHttpHeaders(), HttpStatus.OK);
    }
}
