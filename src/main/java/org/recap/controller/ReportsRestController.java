package org.recap.controller;

import org.recap.ScsbCommonConstants;
import org.recap.ScsbConstants;
import org.recap.model.reports.ReportsRequest;
import org.recap.model.reports.ReportsResponse;
import org.recap.model.submitCollection.SubmitCollectionReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RestController
@RequestMapping("/reportsService")
public class ReportsRestController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(ReportsRestController.class);

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

    private ReportsResponse responseData(ReportsRequest reportsRequest, String countsUrl) {
        ReportsResponse reportsResponse = new ReportsResponse();
        try {
            HttpEntity<ReportsRequest> httpEntity = new HttpEntity<>(reportsRequest, getRestHeaderService().getHttpHeaders());

            ResponseEntity<ReportsResponse> responseEntity = restTemplate.exchange(getScsbSolrClientUrl() + countsUrl, HttpMethod.POST, httpEntity, ReportsResponse.class);
            reportsResponse = responseEntity.getBody();
        } catch (Exception e) {
            logger.error(ScsbCommonConstants.LOG_ERROR, e);
            reportsResponse.setMessage(e.getMessage());
        }
        return reportsResponse;
    }
}
