package org.recap.controller;


import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.recap.BaseTestCase;
import org.recap.model.request.RequestLogReportRequest;
import org.recap.service.RequestItemService;
import org.springframework.http.ResponseEntity;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

/**
 * @author Chittoor Charan Raj
 */

public class RequestItemLogControllerUT extends BaseTestCase {

    @InjectMocks
    RequestItemLogController requestItemLogController;

    @Mock
    RequestItemService requestItemService;

    @Test
    public void getRequestLogReportsTest(){
        RequestLogReportRequest requestLogReportRequest = getRequestLogReportRequest();
        Mockito.when(requestItemService.submitRequests(any())).thenReturn(requestLogReportRequest);
        ResponseEntity<RequestLogReportRequest> requestLogReports = requestItemLogController.getRequestLogReports(new RequestLogReportRequest());
        assertNotNull(requestLogReports);
    }

    @Test
    public void getRequestLogSubmitReportsTest(){
        RequestLogReportRequest requestLogReportRequest = new RequestLogReportRequest();
        Mockito.when(requestItemService.submitRequests(requestLogReportRequest)).thenReturn(requestLogReportRequest);
        ResponseEntity<RequestLogReportRequest> requestLogReports = requestItemLogController.getRequestLogSubmitReports(new RequestLogReportRequest());
        assertNotNull(requestLogReports);
    }

    @Test
    public void getRequestLogSubmitReportsExceptionTest(){
        RequestLogReportRequest requestLogReportRequest = new RequestLogReportRequest();
        requestLogReportRequest.setStatus(null);
        ResponseEntity<RequestLogReportRequest> requestLogReports = requestItemLogController.getRequestLogSubmitReports(requestLogReportRequest);
        assertNotNull(requestLogReports);
    }

    private RequestLogReportRequest getRequestLogReportRequest() {
        RequestLogReportRequest requestLogReportRequest = new RequestLogReportRequest();
        requestLogReportRequest.setStatus("FAILED");
        requestLogReportRequest.setPageSize(1);
        requestLogReportRequest.setId(1);
        requestLogReportRequest.setInstitution("PUL");
        requestLogReportRequest.setFromDate(new Date().toString());
        return requestLogReportRequest;
    }

}
