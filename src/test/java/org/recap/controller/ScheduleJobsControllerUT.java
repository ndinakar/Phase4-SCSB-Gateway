package org.recap.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.recap.PropertyKeyConstants;
import org.recap.ScsbCommonConstants;
import org.recap.ScsbConstants;
import org.recap.model.ScheduleJobRequest;
import org.recap.model.ScheduleJobResponse;
import org.recap.service.RestHeaderService;
import org.recap.spring.SwaggerAPIProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Created by rajeshbabuk on 20/4/17.
 */
public class ScheduleJobsControllerUT extends BaseControllerUT {

    @Value("${" + PropertyKeyConstants.SCSB_BATCH_SCHEDULE_URL + "}")
    String scsbScheduleUrl;

    @Mock
    private RestTemplate mockRestTemplate;

    @InjectMocks
    private ScheduleJobsController scheduleJobsController;

    @Mock
    RestHeaderService restHeaderService;

    public String getScsbScheduleUrl() {
        return scsbScheduleUrl;
    }

    public void setScsbScheduleUrl(String scsbScheduleUrl) {
        this.scsbScheduleUrl = scsbScheduleUrl;
    }

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testScheduleJob() {
        ScheduleJobRequest scheduleJobRequest = new ScheduleJobRequest();
        scheduleJobRequest.setJobName(ScsbCommonConstants.PURGE_EXCEPTION_REQUESTS);
        scheduleJobRequest.setScheduleType(ScsbConstants.SCHEDULE);
        ScheduleJobResponse scheduleJobResponse = new ScheduleJobResponse();
        scheduleJobResponse.setMessage("Scheduled");
        ResponseEntity<ScheduleJobResponse> responseEntity = new ResponseEntity<>(scheduleJobResponse, HttpStatus.OK);
        Mockito.doReturn(responseEntity).when(mockRestTemplate).exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<ScheduleJobResponse>>any());
        ReflectionTestUtils.setField(scheduleJobsController,"scsbScheduleUrl",scsbScheduleUrl);
        ScheduleJobResponse scheduleJobResponse1 = scheduleJobsController.scheduleJob(scheduleJobRequest);
        assertNotNull(scheduleJobResponse1);
    }

    @Test
    public void testScheduleJob_Exception() {
        ReflectionTestUtils.setField(scheduleJobsController,"scsbScheduleUrl",scsbScheduleUrl);
        HttpEntity requestEntity = getSwaggerHttpEntity();
        ScheduleJobRequest scheduleJobRequest = new ScheduleJobRequest();
        scheduleJobRequest.setJobName(ScsbCommonConstants.PURGE_EXCEPTION_REQUESTS);
        scheduleJobRequest.setScheduleType(ScsbConstants.SCHEDULE);
        ScheduleJobResponse scheduleJobResponse = new ScheduleJobResponse();
        scheduleJobResponse.setMessage("Scheduled");
        ResponseEntity<ScheduleJobResponse> responseEntity = new ResponseEntity<>(scheduleJobResponse, HttpStatus.OK);
        Mockito.when(mockRestTemplate.exchange(scsbScheduleUrl + ScsbConstants.URL_SEARCH_BY_JSON, HttpMethod.POST, requestEntity, ScheduleJobResponse.class)).thenReturn(null);
        ScheduleJobResponse scheduleJobResponse1 = scheduleJobsController.scheduleJob(scheduleJobRequest);
        assertNotNull(scheduleJobResponse1.getMessage());
    }

    @Test
    public void testcustomLoggerTest() {
        ScheduleJobResponse scheduleJobResponse1 = scheduleJobsController.customLoggerTest();
        assertEquals("Scheduler job response",scheduleJobResponse1.getMessage());
    }

    public HttpEntity getSwaggerHttpEntity(){
        return new HttpEntity<>(getSwaggerHeaders());
    }
    public static HttpHeaders getSwaggerHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(ScsbCommonConstants.API_KEY, SwaggerAPIProvider.getInstance().getSwaggerApiKey());
        return headers;
    }

}
