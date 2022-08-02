package org.recap.controller;

import lombok.extern.slf4j.Slf4j;
import org.recap.ScsbCommonConstants;
import org.recap.ScsbConstants;
import org.recap.model.reports.ReportsRequest;
import org.recap.model.reports.ReportsResponse;
import org.recap.model.reports.TitleMatchedReport;
import org.recap.model.submitCollection.SubmitCollectionReport;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by rajeshbabuk on 13/1/17.
 */
@Slf4j
@RestController
@RequestMapping("/reportsService")
public class ReportsRestController extends AbstractController {


    /**
     * This method will call scsb-solr-client microservice to get total counts of accessioned and deaccessioned items in scsb.
     *
     * @param reportsRequest the reporostts request
     * @return the reports response
     */
    @PostMapping(value = "/accessionDeaccessionCounts")
    public ReportsResponse accessionDeaccessionCounts(@RequestBody ReportsRequest reportsRequest) {
        return responseData(reportsRequest, ScsbConstants.URL_REPORTS_ACCESSION_DEACCESSION_COUNTS);
    }

    /**
     * This method will call scsb-solr-client microservice to get items counts based on the collection group designation.
     *
     * @param reportsRequest the reports request
     * @return the reports response
     */
    @PostMapping(value = "/cgdItemCounts")
    public ReportsResponse cgdItemCounts(@RequestBody ReportsRequest reportsRequest) {
        return responseData(reportsRequest, ScsbConstants.URL_REPORTS_CGD_ITEM_COUNTS);
    }

    /**
     * This method will call scsb-solr-client microservice to get the items which are deaccessioned in scsb.
     *
     * @param reportsRequest the reports request
     * @return the reports response
     */
    @PostMapping(value = "/deaccessionResults")
    public ReportsResponse deaccessionResults(@RequestBody ReportsRequest reportsRequest) {
        return responseData(reportsRequest, ScsbConstants.URL_REPORTS_DEACCESSION_RESULTS);
    }

    /**
     * This method will call scsb-solr-client microservice to get the item incomplete records results.
     *
     * @param reportsRequest the reports request
     * @return the reports response
     */
    @PostMapping(value = "/incompleteRecords")
    public ReportsResponse incompleteRecords(@RequestBody ReportsRequest reportsRequest) {
        return responseData(reportsRequest, ScsbConstants.URL_REPORTS_INCOMPLETE_RESULTS);
    }

    /**
     *
     * @param submitCollectionReprot
     * @return SubmitCollection Report
     */
    @PostMapping("/submitCollection")
    public ResponseEntity<SubmitCollectionReport> submitCollectionReprot(@RequestBody SubmitCollectionReport submitCollectionReprot){
        HttpEntity<SubmitCollectionReport> httpEntity = new HttpEntity<>(submitCollectionReprot, getRestHeaderService().getHttpHeaders());
        ResponseEntity<SubmitCollectionReport> submitCollectionReprotResponseEntity = restTemplate.exchange(getScsbSolrClientUrl()+ScsbConstants.URL_SUBMIT_COLLECTION_REPORT,HttpMethod.POST,httpEntity,SubmitCollectionReport.class);
        return  new ResponseEntity<>(submitCollectionReprotResponseEntity.getBody(), HttpStatus.OK);
    }
    /**
     *
     * @param submitCollectionReprot
     * @return SubmitCollection Report
     */
    @PostMapping("/accessionReports")
    public ResponseEntity<SubmitCollectionReport> accessionReport(@RequestBody SubmitCollectionReport submitCollectionReprot){
        HttpEntity<SubmitCollectionReport> httpEntity = new HttpEntity<>(submitCollectionReprot, getRestHeaderService().getHttpHeaders());
        ResponseEntity<SubmitCollectionReport> submitCollectionReprotResponseEntity = restTemplate.exchange(getScsbSolrClientUrl()+ScsbConstants.URL_ACCESSION_EXCEPTION_REPORT,HttpMethod.POST,httpEntity,SubmitCollectionReport.class);
        return  new ResponseEntity<>(submitCollectionReprotResponseEntity.getBody(), HttpStatus.OK);
    }

    /**
     *
     * @param titleMatchedReport
     * @return List of TitleMatchedReports
     */
    @PostMapping("/titleMatchCount")
    public ResponseEntity<TitleMatchedReport> titleMatchCount(@RequestBody TitleMatchedReport titleMatchedReport){
        HttpEntity<TitleMatchedReport> httpEntity = new HttpEntity<>(titleMatchedReport, getRestHeaderService().getHttpHeaders());
        ResponseEntity<TitleMatchedReport> titleMatchedReportResponseEntity = restTemplate.exchange(getScsbSolrClientUrl()+ScsbConstants.URL_TITLE_MATCH_COUNT,HttpMethod.POST,httpEntity,TitleMatchedReport.class);
        return  new ResponseEntity<>(titleMatchedReportResponseEntity.getBody(), HttpStatus.OK);
    }
    /**
     *
     * @param titleMatchedReport
     * @return List of TitleMatchedReports
     */
    @PostMapping("/titleMatchReport")
    public ResponseEntity<TitleMatchedReport> titleMatchReport(@RequestBody TitleMatchedReport titleMatchedReport){
        HttpEntity<TitleMatchedReport> httpEntity = new HttpEntity<>(titleMatchedReport, getRestHeaderService().getHttpHeaders());
        ResponseEntity<TitleMatchedReport> titleMatchedReportResponseEntity = restTemplate.exchange(getScsbSolrClientUrl()+ScsbConstants.URL_TITLE_MATCH_REPORT,HttpMethod.POST,httpEntity,TitleMatchedReport.class);
        return  new ResponseEntity<>(titleMatchedReportResponseEntity.getBody(), HttpStatus.OK);
    }
    /**
     *
     * @param titleMatchedReport
     * @return List of TitleMatchedReports
     */
    @PostMapping("/titleMatchReportExport")
    public ResponseEntity<TitleMatchedReport> titleMatchReportExport(@RequestBody TitleMatchedReport titleMatchedReport){
        HttpEntity<TitleMatchedReport> httpEntity = new HttpEntity<>(titleMatchedReport, getRestHeaderService().getHttpHeaders());
        ResponseEntity<TitleMatchedReport> titleMatchedReportResponseEntity = restTemplate.exchange(getScsbSolrClientUrl()+ScsbConstants.URL_TITLE_MATCH_REPORT_EXPORT,HttpMethod.POST,httpEntity,TitleMatchedReport.class);
        return  new ResponseEntity<>(titleMatchedReportResponseEntity.getBody(), HttpStatus.OK);
    }

    /**
     *
     * @param titleMatchedReport
     * @return List of TitleMatchedReports
     */
    @PostMapping("/title-match-report-export-s3")
    public ResponseEntity<TitleMatchedReport> titleMatchReportExportS3(@RequestBody TitleMatchedReport titleMatchedReport){
        HttpEntity<TitleMatchedReport> httpEntity = new HttpEntity<>(titleMatchedReport, getRestHeaderService().getHttpHeaders());
        ResponseEntity<TitleMatchedReport> titleMatchedReportResponseEntity = restTemplate.exchange(getScsbSolrClientUrl()+ScsbConstants.URL_TITLE_MATCH_REPORT_EXPORT_S3,HttpMethod.POST,httpEntity,TitleMatchedReport.class);
        return  new ResponseEntity<>(titleMatchedReportResponseEntity.getBody(), HttpStatus.OK);
    }

    private ReportsResponse responseData(ReportsRequest reportsRequest, String countsUrl) {
        ReportsResponse reportsResponse = new ReportsResponse();
        try {
            HttpEntity<ReportsRequest> httpEntity = new HttpEntity<>(reportsRequest, getRestHeaderService().getHttpHeaders());

            ResponseEntity<ReportsResponse> responseEntity = restTemplate.exchange(getScsbSolrClientUrl() + countsUrl, HttpMethod.POST, httpEntity, ReportsResponse.class);
            reportsResponse = responseEntity.getBody();
        } catch (RuntimeException e) {
            log.error(ScsbCommonConstants.LOG_ERROR, e);
            reportsResponse.setMessage(e.getMessage());
        }
        return reportsResponse;
    }
}
