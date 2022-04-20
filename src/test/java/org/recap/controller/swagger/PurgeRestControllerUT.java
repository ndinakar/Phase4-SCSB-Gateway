package org.recap.controller.swagger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.recap.BaseTestCase;
import org.recap.PropertyKeyConstants;
import org.recap.ScsbCommonConstants;
import org.recap.ScsbConstants;
import org.recap.model.SearchRecordsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Created by hemalathas on 10/4/17.
 */
public class PurgeRestControllerUT extends BaseTestCase{


    @InjectMocks
    PurgeRestController purgeRestController;

    @Value("${" + PropertyKeyConstants.SCSB_CIRC_URL + "}")
    String scsbCircUrl;

    @Value("${" + PropertyKeyConstants.SCSB_CORE_URL + "}")
    private String scsbCoreUrl;

    public String getScsbCircUrl() {
        return scsbCircUrl;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    @Mock
    HttpEntity mockedHttpEntity;

    @Mock
    RestTemplate restTemplate;


    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(purgeRestController,"scsbCircUrl",scsbCircUrl);
        ReflectionTestUtils.setField(purgeRestController,"scsbCoreUrl",scsbCoreUrl);
    }

    HttpEntity requestEntity = new HttpEntity<>(getHttpHeaders());

    @Test
    public void testPurgeEmailAddress(){
        ResponseEntity responseEntity1 = new ResponseEntity(getValue(), HttpStatus.OK);
        Mockito.when(restTemplate.exchange(scsbCoreUrl+ ScsbConstants.REST_URL_PURGE_EMAIL_ADDRESS, HttpMethod.GET,requestEntity,Map.class)).thenReturn(responseEntity1);
        ResponseEntity responseEntity = purgeRestController.purgeEmailAddress();
        assertNotNull(responseEntity);
    }

    @Test
    public void testPurgeEmailAddressException(){
        Mockito.when(restTemplate.exchange(getScsbCircUrl()+ ScsbConstants.REST_URL_PURGE_EMAIL_ADDRESS, HttpMethod.GET,requestEntity,Map.class)).thenThrow(new NullPointerException("Exception occured"));
        ResponseEntity responseEntity = purgeRestController.purgeEmailAddress();
        assertNotNull(responseEntity);
    }

    @Test
    public void testPurgeExceptionRequests() {
        Map responseMap = new HashMap();
        responseMap.put(ScsbCommonConstants.STATUS, ScsbCommonConstants.SUCCESS);
        ResponseEntity<Map> responseEntity1 = new ResponseEntity<>(responseMap, HttpStatus.OK);
        Mockito.doReturn(responseEntity1).when(restTemplate).exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<SearchRecordsResponse>>any());
        ResponseEntity responseEntity = purgeRestController.purgeExceptionRequests();
        assertEquals(responseMap,responseEntity.getBody());
    }

    @Test
    public void testPurgeExceptionRequestsException() {
        Map responseMap = new HashMap();
        responseMap.put(ScsbCommonConstants.STATUS, ScsbCommonConstants.SUCCESS);
        Mockito.when(restTemplate.exchange(getScsbCircUrl() + ScsbConstants.REST_URL_PURGE_EMAIL_ADDRESS, HttpMethod.GET, requestEntity, Map.class)).thenThrow(new NullPointerException("Exception occured"));
        ResponseEntity responseEntity = purgeRestController.purgeExceptionRequests();
        assertNotNull(responseEntity);
    }

    private Map<String,Integer> getValue(){
        Map<String,Integer> map = new HashMap<>();
        map.put("physicalRequest",1);
        map.put("eddRequest",1);
        return map;
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(ScsbCommonConstants.RESPONSE_DATE, new Date().toString());
        return responseHeaders;
    }

    public HttpEntity getHttpEntity(){
        return new HttpEntity<>(getHttpHeaders());
    }

}