package org.recap.controller.swagger;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.recap.ScsbCommonConstants;
import org.recap.ScsbConstants;
import org.recap.controller.AbstractController;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by premkb on 19/8/16.
 */
@Slf4j
@RestController
@RequestMapping("/dataDump")
@Tag(name="dataDump")
public class DataDumpRestController extends AbstractController  {



   /**
     * This method is the entry point to start data export process and passes to request to the scsb-etl microservice.
     *
     * @param institutionCodes          the institution codes
     * @param requestingInstitutionCode the requesting institution code
     * @param fetchType                 the fetch type
     * @param outputFormat              the output format
     * @param date                      the date
     * @param collectionGroupIds        the collection group ids
     * @param transmissionType          the transmission type
     * @param emailToAddress            the email to address
     * @return the response entity
     */
    @GetMapping("/exportDataDump")
    @Operation(summary = "exportDataDump",
           description =  "The Export Data Dump API allows export of bibliographic records in SCSB database into MARCXML or SCSBXML format. This is used by partners to export records in preferred format and update their respective discovery systems. These jobs are scheduled to run by support institution team.")
    @ApiResponse(responseCode = "200", description = ScsbConstants.DATADUMP_PROCESS_STARTED)
    @ResponseBody
    public ResponseEntity exportDataDump(@Parameter(description = "${swagger.values.institutionCodes}" , required = true, name = "institutionCodes") @RequestParam String institutionCodes,
                                         @Parameter(description = "${swagger.values.requestingInstitutionCode}",required=true, name = "requestingInstitutionCode") @RequestParam String requestingInstitutionCode,
                                         @Parameter(description = "${swagger.values.imsDepositoryCodes}", name = "imsDepositoryCodes") @RequestParam(required=false) String imsDepositoryCodes,
                                         @Parameter(description = "Type of export - Incremental (use 1) or Deleted (use 2) or Full Dump (use 10)" , required = true , name = "fetchType") @RequestParam String fetchType,
                                         @Parameter(description = "Type of format - Marc xml (use 0) or SCSB xml (use 1), for deleted records only json format (use 2)",required=true, name = "outputFormat") @RequestParam String outputFormat,
                                         @Parameter(description = "Get updates to middleware collection since the date provided. Date format will be a string (yyyy-MM-dd HH:mm) and is Eastern Time.",name = "date") @RequestParam(required = false) String date,
                                         @Parameter(description = "Data can be requested by Collection Group ID, either Shared (use 1)/Open (use 2)/Private (use 3)/Committed (use 5)/Uncommittable (use 6). Default is Shared, Open, Committed and Uncommittable, can use 1,2,3 as well.", name = "collectionGroupIds") @RequestParam(required=false) String collectionGroupIds,
                                         @Parameter(description = "Type of transmission - for S3 use 0, for HTTP response use 1. Default is S3.", name = "transmissionType")@RequestParam(required=false) String transmissionType,
                                         @Parameter(description = "Email address to whom email will be sent upon completion" , name = "emailToAddress")@RequestParam(required=false) String emailToAddress,
                                         @Parameter(hidden = true) @RequestParam(required=false) String userName
    ){
        Map<String,String> inputMap = new HashMap<>();
        setInputMapValues(inputMap, institutionCodes, requestingInstitutionCode, fetchType, outputFormat, date, collectionGroupIds, transmissionType, emailToAddress,imsDepositoryCodes,userName);
        try {
            HttpEntity requestEntity = getSwaggerHttpEntity();
            HttpHeaders responseHeaders = getHttpHeaders();
            responseHeaders.add(ScsbCommonConstants.RESPONSE_HEADER_CONTENT_TYPE,ScsbCommonConstants.RESPONSE_HEADER_CONTENT_TYPE_VALUE);
            ResponseEntity<String> response = restTemplate.exchange(getScsbEtlUrl() + "dataDump/exportDataDump/?institutionCodes={institutionCodes}&requestingInstitutionCode={requestingInstitutionCode}&imsDepositoryCodes={imsDepositoryCodes}&fetchType={fetchType}&outputFormat={outputFormat}&date={date}&collectionGroupIds={collectionGroupIds}&transmissionType={transmissionType}&emailToAddress={emailToAddress}&userName={userName}", HttpMethod.GET, requestEntity, String.class, inputMap);
            return new ResponseEntity<>(response.getBody(), responseHeaders, response.getStatusCode());
        } catch (RuntimeException exception) {
            log.error("error-->",exception);
            return new ResponseEntity<>("Scsb Etl Service is Unavailable.", getHttpHeaders(), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    /**
     * This method is the entry point to start data export process and passes to request to the scsb-etl microservice.
     *
     * @param institutionCodes          the institution codes
     * @param requestingInstitutionCode the requesting institution code
     * @param fetchType                 the fetch type
     * @param outputFormat              the output format
     * @param date                      the date
     * @param toDate                    the toDate
     * @param collectionGroupIds        the collection group ids
     * @param transmissionType          the transmission type
     * @param emailToAddress            the email to address
     * @return the response entity
     */
    @GetMapping("/exportDataDumpWithToDate")
    @Operation(summary = "exportDataDump",
           description =  "The Export Data Dump API allows export of bibliographic records in SCSB database into MARCXML or SCSBXML format. This is used by partners to export records in preferred format and update their respective discovery systems. These jobs are scheduled to run by support institution team.", operationId = "0")
    @ApiResponse(responseCode = "200", description = ScsbConstants.DATADUMP_PROCESS_STARTED)
    @ResponseBody
    public ResponseEntity exportDataDumpWithToDate(@Parameter(description = "${swagger.values.institutionCodes}" , required = true, name = "institutionCodes") @RequestParam String institutionCodes,
                                         @Parameter(description = "${swagger.values.requestingInstitutionCode}",required=true, name = "requestingInstitutionCode") @RequestParam String requestingInstitutionCode,
                                         @Parameter(description = "${swagger.values.imsDepositoryCodes}" ,name = "imsDepositoryCodes") @RequestParam(required=false) String imsDepositoryCodes,
                                         @Parameter(description = "Type of export - Incremental (use 1) or Deleted (use 2)" , required = true , name = "fetchType") @RequestParam String fetchType,
                                         @Parameter(description = "Type of format - Marc xml (use 0) or SCSB xml (use 1), for deleted records only json format (use 2)",required=true, name = "outputFormat") @RequestParam String outputFormat,
                                         @Parameter(description = "Get updates to middleware collection since the date provided. Date format will be a string (yyyy-MM-dd HH:mm) and is Eastern Time.",name = "date") @RequestParam(required = false) String date,
                                         @Parameter(description = "Get updates to middleware collection until the date provided. Date format will be a string (yyyy-MM-dd HH:mm) and is Eastern Time.",name = "toDate") @RequestParam(required = false) String toDate,
                                         @Parameter(description = "Data can be requested by Collection Group ID, either Shared (use 1)/Open (use 2)/Private (use 3)/Committed (use 5)/Uncommittable (use 6). Default is Shared, Open, Committed and Uncommittable, can use 1,2 as well.", name = "collectionGroupIds") @RequestParam(required=false) String collectionGroupIds,
                                         @Parameter(description = "Type of transmission - for S3 use 0, for HTTP response use 1. Default is S3.", name = "transmissionType")@RequestParam(required=false) String transmissionType,
                                         @Parameter(description = "Email address to whom email will be sent upon completion" , name = "emailToAddress")@RequestParam(required=false) String emailToAddress,
                                                   @Parameter(hidden = true) @RequestParam(required=false) String userName
    ){
        Map<String,String> inputMap = new HashMap<>();
        setInputMapValues(inputMap, institutionCodes, requestingInstitutionCode, fetchType, outputFormat, date, collectionGroupIds, transmissionType, emailToAddress,imsDepositoryCodes,userName);
        inputMap.put("toDate",toDate);
        try {
            HttpEntity requestEntity = getSwaggerHttpEntity();
            ResponseEntity<String> response = restTemplate.exchange(getScsbEtlUrl() + "dataDump/exportDataDump/?institutionCodes={institutionCodes}&requestingInstitutionCode={requestingInstitutionCode}&imsDepositoryCodes={imsDepositoryCodes}&fetchType={fetchType}&outputFormat={outputFormat}&date={date}&toDate={toDate}&collectionGroupIds={collectionGroupIds}&transmissionType={transmissionType}&emailToAddress={emailToAddress}&userName={userName}", HttpMethod.GET, requestEntity, String.class, inputMap);
            return new ResponseEntity<>(response.getBody(), getHttpHeaders(), response.getStatusCode());

        } catch (RuntimeException exception) {
            log.error("error-->",exception);
            return new ResponseEntity<>("Scsb Etl Service is Unavailable.", getHttpHeaders(), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    private static void setInputMapValues(Map<String,String> inputMap, String institutionCodes, String requestingInstitutionCode, String fetchType,
                                   String outputFormat, String date, String collectionGroupIds, String transmissionType, String emailToAddress,String imsDepositoryCodes,String userName)
    {
        inputMap.put("institutionCodes",institutionCodes);
        inputMap.put("requestingInstitutionCode",requestingInstitutionCode);
        inputMap.put("fetchType",fetchType);
        inputMap.put("outputFormat",outputFormat);
        inputMap.put("date",date);
        inputMap.put("collectionGroupIds",collectionGroupIds);
        inputMap.put("transmissionType",transmissionType);
        inputMap.put("emailToAddress",emailToAddress);
        inputMap.put("imsDepositoryCodes",imsDepositoryCodes);
        inputMap.put("userName",userName);
    }
}
