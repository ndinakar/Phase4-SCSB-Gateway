package org.recap.controller;

import lombok.extern.slf4j.Slf4j;
import org.recap.ScsbCommonConstants;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by akulak on 27/2/18 .
 */
@Slf4j
@RestController
@RequestMapping("/deAccessionService")
public class DeAccessionRestController extends AbstractController{

    ;

    /**
     * This method will call scsb-circ microservice to deaccession an item in SCSB.
     *
     * @param deAccessionRequest the de accession request
     * @return the map
     */
    @PostMapping(value = "/deaccession")
    public Map<String, String> deAccession(@RequestBody String deAccessionRequest) {
        Map<String, String> resultMap = new HashMap<>();
        try {
            HttpEntity<String> requestEntity = new HttpEntity<>(deAccessionRequest, getRestHeaderService().getHttpHeaders());
            resultMap = restTemplate.postForObject(getScsbCircUrl() + "/sharedCollection/deAccession", requestEntity, Map.class);
        } catch (RuntimeException ex) {
            log.error(ScsbCommonConstants.LOG_ERROR,ex);
        }
        return resultMap;
    }
}
