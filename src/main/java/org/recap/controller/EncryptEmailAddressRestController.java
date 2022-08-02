package org.recap.controller;

import lombok.extern.slf4j.Slf4j;
import org.recap.ScsbCommonConstants;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by akulak on 21/9/17.
 */
@Slf4j
@RestController
@RequestMapping("/encryptEmailAddressService")
public class EncryptEmailAddressRestController extends AbstractController {

    @GetMapping(value = "/encryptEmailAddress")
    public ResponseEntity<String> encryptEmailAddress() {
        String response = "";
        try {
            HttpEntity requestEntity = getHttpEntity();
            ResponseEntity<String> exchange = restTemplate.exchange(getScsbCircUrl() + "/encryptEmailAddress/startEncryptEmailAddress", HttpMethod.GET, requestEntity, String.class);
            response  = exchange.getBody();
        } catch (RuntimeException e) {
            log.error(ScsbCommonConstants.LOG_ERROR, e);
            return new ResponseEntity<>(response, getHttpHeaders(), HttpStatus.SERVICE_UNAVAILABLE);
        }
        return new ResponseEntity<>(response, getHttpHeaders(), HttpStatus.OK);
    }
}
