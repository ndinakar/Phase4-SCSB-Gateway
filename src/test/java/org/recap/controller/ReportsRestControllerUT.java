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
import org.recap.model.reports.ReportsRequest;
import org.recap.model.reports.ReportsResponse;
import org.recap.model.reports.TitleMatchedReport;
import org.recap.model.search.DeaccessionItemResultsRow;
import org.recap.model.submitCollection.SubmitCollectionReport;
import org.recap.service.RestHeaderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Created by rajeshbabuk on 13/1/17.
 */

public class ReportsRestControllerUT extends BaseControllerUT {

    @Value("${" + PropertyKeyConstants.SCSB_SOLR_DOC_URL + "}")
    String scsbSolrClientUrl;

    @Mock
    private RestTemplate mockRestTemplate;

    @InjectMocks
    private ReportsRestController reportsRestController;

    @Mock
    private RestHeaderService restHeaderService;

    @Mock
    SubmitCollectionReport submitCollectionReprot;

    @Mock
   TitleMatchedReport  titleMatchedReport;



    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    public String getScsbSolrClientUrl() {
        return scsbSolrClientUrl;
    }

    public void setScsbSolrClientUrl(String scsbSolrClientUrl) {
        this.scsbSolrClientUrl = scsbSolrClientUrl;
    }

    @Test
    public void accessionDeaccessionCounts() {
        ReportsRequest reportsRequest = new ReportsRequest();
        reportsRequest.setAccessionDeaccessionFromDate("09/27/2016");
        reportsRequest.setAccessionDeaccessionToDate("01/27/2017");
        reportsRequest.setOwningInstitutions(Arrays.asList("CUL", "PUL", "NYPL"));
        reportsRequest.setCollectionGroupDesignations(Arrays.asList("Private", "Open", "Shared"));
        reportsRequest.setPageNumber(1);
        reportsRequest.setPageSize(10);
        reportsRequest.setDeaccessionOwningInstitution("CUL");
        reportsRequest.setIncompleteRequestingInstitution("PUL");
        reportsRequest.setIncompletePageNumber(10);
        reportsRequest.setIncompletePageSize(10);
        reportsRequest.setExport(true);
        DeaccessionItemResultsRow deaccessionItemResultsRow = getDeaccessionItemResultsRow();
        ReportsResponse reportsResponse = new ReportsResponse();
        reportsResponse.setMessage(ScsbCommonConstants.SUCCESS);
        reportsResponse.setDeaccessionItemResultsRows(Arrays.asList(deaccessionItemResultsRow));
        ResponseEntity<ReportsResponse> responseEntity = new ResponseEntity<ReportsResponse>(reportsResponse, HttpStatus.OK);
        Mockito.doReturn(responseEntity).when(mockRestTemplate).exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<ReportsResponse>>any()); ReportsResponse reportsResponse1 = reportsRestController.accessionDeaccessionCounts(reportsRequest);
        assertNotNull(reportsResponse1);
        assertNotNull(deaccessionItemResultsRow.getCgd());
        assertNotNull(deaccessionItemResultsRow.getDeaccessionDate());
        assertNotNull(deaccessionItemResultsRow.getDeaccessionNotes());
        assertEquals("PUL", deaccessionItemResultsRow.getDeaccessionOwnInst());
        assertNotNull(deaccessionItemResultsRow.getItemBarcode());
        assertNotNull(deaccessionItemResultsRow.getItemId());
        assertNotNull(deaccessionItemResultsRow.getTitle());
    }

    //@Test
    public void accessionDeaccessionCounts_Exception() {
        ReportsRequest reportsRequest = new ReportsRequest();
        ReportsResponse reportsResponse1 = reportsRestController.accessionDeaccessionCounts(reportsRequest);
        assertNull(reportsResponse1.getMessage());
    }

    public DeaccessionItemResultsRow getDeaccessionItemResultsRow() {
        DeaccessionItemResultsRow deaccessionItemResultsRow = new DeaccessionItemResultsRow();
        deaccessionItemResultsRow.setItemId(123);
        deaccessionItemResultsRow.setCgd("Open");
        deaccessionItemResultsRow.setDeaccessionDate(new Date().toString());
        deaccessionItemResultsRow.setDeaccessionNotes("test");
        deaccessionItemResultsRow.setTitle("test");
        deaccessionItemResultsRow.setDeaccessionOwnInst("PUL");
        deaccessionItemResultsRow.setItemBarcode("326598741256985");
        return deaccessionItemResultsRow;
    }

    @Test
    public void cgdItemCounts() {
        ReportsRequest reportsRequest = new ReportsRequest();
        reportsRequest.setOwningInstitutions(Arrays.asList("CUL", "PUL", "NYPL"));
        reportsRequest.setCollectionGroupDesignations(Arrays.asList("Private", "Open", "Shared"));
        ReportsResponse reportsResponse = new ReportsResponse();
        reportsResponse.setMessage(ScsbCommonConstants.SUCCESS);
        ResponseEntity<ReportsResponse> responseEntity = new ResponseEntity<ReportsResponse>(reportsResponse, HttpStatus.OK);
        Mockito.doReturn(responseEntity).when(mockRestTemplate).exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<ReportsResponse>>any()); ReportsResponse reportsResponse1 = reportsRestController.cgdItemCounts(reportsRequest);
        assertNotNull(reportsResponse1);
    }

    @Test
    public void cgdItemCounts_Exception() {
        ReportsRequest reportsRequest = new ReportsRequest();
        reportsRequest.setOwningInstitutions(Arrays.asList("TEST", "TEST", "TEST"));
        reportsRequest.setCollectionGroupDesignations(Arrays.asList("TEST", "TEST", "TEST"));
        ReportsResponse reportsResponse = new ReportsResponse();
        reportsResponse.setMessage(ScsbCommonConstants.SUCCESS);
        Mockito.doReturn(null).when(mockRestTemplate).exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<ReportsResponse>>any());
        ReportsResponse reportsResponse1 = reportsRestController.cgdItemCounts(reportsRequest);
        assertNotNull(reportsResponse1);
    }

    @Test
    public void deaccessionResults() {
        ReportsRequest reportsRequest = new ReportsRequest();
        reportsRequest.setAccessionDeaccessionFromDate("09/27/2016");
        reportsRequest.setAccessionDeaccessionToDate("01/27/2017");
        reportsRequest.setDeaccessionOwningInstitution("PUL");
        ReportsResponse reportsResponse = new ReportsResponse();
        reportsResponse.setMessage(ScsbCommonConstants.SUCCESS);
        ResponseEntity<ReportsResponse> responseEntity = new ResponseEntity<ReportsResponse>(reportsResponse, HttpStatus.OK);
        Mockito.doReturn(responseEntity).when(mockRestTemplate).exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<ReportsResponse>>any()); ReportsResponse reportsResponse1 = reportsRestController.deaccessionResults(reportsRequest);
        assertNotNull(reportsResponse1);
    }

    //@Test
    public void deaccessionResults_Exception() {
        ReportsRequest reportsRequest = new ReportsRequest();
        ReportsResponse reportsResponse1 = reportsRestController.deaccessionResults(reportsRequest);
        assertNull(reportsResponse1.getMessage());
    }

    @Test
    public void incompleteRecords() {
        ReportsRequest reportsRequest = new ReportsRequest();
        reportsRequest.setDeaccessionOwningInstitution("PUL");
        ReportsResponse reportsResponse = new ReportsResponse();
        reportsResponse.setMessage(ScsbCommonConstants.SUCCESS);
        ResponseEntity<ReportsResponse> responseEntity = new ResponseEntity<ReportsResponse>(reportsResponse, HttpStatus.OK);
        Mockito.doReturn(responseEntity).when(mockRestTemplate).exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<ReportsResponse>>any());
        ReportsResponse reportsResponse1 = reportsRestController.incompleteRecords(reportsRequest);
        assertNotNull(reportsResponse1);
    }

    //@Test
    public void incompleteRecords_Exception() {
        ReportsRequest reportsRequest = new ReportsRequest();
        ReportsResponse reportsResponse1 = reportsRestController.incompleteRecords(reportsRequest);
        assertNull(reportsResponse1.getMessage());
    }

    @Test
    public void submitCollectionReprot() {
        ResponseEntity<SubmitCollectionReport> submitCollectionReprotResponseEntity=new ResponseEntity<SubmitCollectionReport>(submitCollectionReprot,HttpStatus.OK);
        Mockito.doReturn(submitCollectionReprotResponseEntity).when(mockRestTemplate).exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<ReportsResponse>>any());
        ResponseEntity responseEntity1 = reportsRestController.submitCollectionReprot(submitCollectionReprot);
        assertNotNull(responseEntity1);
    }

    @Test
    public void accessionReport() {
        ResponseEntity<SubmitCollectionReport> submitCollectionReprotResponseEntity = new ResponseEntity<SubmitCollectionReport>(submitCollectionReprot, HttpStatus.OK);
        Mockito.doReturn(submitCollectionReprotResponseEntity).when(mockRestTemplate).exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<ReportsResponse>>any());
        ResponseEntity responseEntity1 = reportsRestController.accessionReport(submitCollectionReprot);
        assertNotNull(responseEntity1);
    }

        @Test
        public void titleMatchCount() {
            ResponseEntity<TitleMatchedReport> titleMatchedReportResponseEntity =new ResponseEntity<TitleMatchedReport>(titleMatchedReport,HttpStatus.OK);
            Mockito.doReturn(titleMatchedReportResponseEntity).when(mockRestTemplate).exchange(
                    ArgumentMatchers.anyString(),
                    ArgumentMatchers.any(HttpMethod.class),
                    ArgumentMatchers.any(),
                    ArgumentMatchers.<Class<ReportsResponse>>any());
            ResponseEntity responseEntity = reportsRestController.titleMatchCount(titleMatchedReport);
            assertNotNull(responseEntity);
    }

    @Test
    public void titleMatchReport() {
        ResponseEntity<TitleMatchedReport> titleMatchedReportResponseEntity =new ResponseEntity<TitleMatchedReport>(titleMatchedReport,HttpStatus.OK);
        Mockito.doReturn(titleMatchedReportResponseEntity).when(mockRestTemplate).exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<ReportsResponse>>any());
        ResponseEntity responseEntity1 = reportsRestController.titleMatchReport(titleMatchedReport);
        assertNotNull(responseEntity1);
    }

    @Test
    public void titleMatchReportExport() {
        ResponseEntity<TitleMatchedReport> titleMatchedReportResponseEntity =new ResponseEntity<TitleMatchedReport>(titleMatchedReport,HttpStatus.OK);
        Mockito.doReturn(titleMatchedReportResponseEntity).when(mockRestTemplate).exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<ReportsResponse>>any());
        ResponseEntity responseEntity1 = reportsRestController.titleMatchReportExport(titleMatchedReport);
        assertNotNull(responseEntity1);
    }

    @Test
    public void titleMatchReportExportS3() {
        ResponseEntity<TitleMatchedReport> titleMatchedReportResponseEntity =new ResponseEntity<TitleMatchedReport>(titleMatchedReport,HttpStatus.OK);
        Mockito.doReturn(titleMatchedReportResponseEntity).when(mockRestTemplate).exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<ReportsResponse>>any());
        ResponseEntity responseEntity1 = reportsRestController.titleMatchReportExportS3(titleMatchedReport);
        assertNotNull(responseEntity1);
    }
}
