package org.recap.controller;

import org.recap.RecapCommonConstants;
import org.recap.RecapConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by akulak on 27/2/18 .
 */
@RestController
@RequestMapping("/deAccessionService")
public class DeAccessionRestController extends AbstractController{

    private static final Logger logger = LoggerFactory.getLogger(DeAccessionRestController.class);

    /**
     * This method will call scsb-circ microservice to deaccession an item in SCSB.
     *
     * @param deAccessionRequest the de accession request
     * @return the map
     */
    @RequestMapping(value = "/deaccession", method = RequestMethod.POST)
    public Map<String, String> deAccession(@RequestBody String deAccessionRequest) {
        Map<String, String> resultMap = new HashMap<>();
        try {
            HttpEntity<String> requestEntity = new HttpEntity<>(deAccessionRequest, getRestHeaderService().getHttpHeaders());
            resultMap = getRestTemplate().postForObject(getScsbCircUrl() + "/sharedCollection/deAccession", requestEntity, Map.class);
        } catch (Exception ex) {
            logger.error(RecapCommonConstants.LOG_ERROR,ex);
        }
        return resultMap;
    }
}
