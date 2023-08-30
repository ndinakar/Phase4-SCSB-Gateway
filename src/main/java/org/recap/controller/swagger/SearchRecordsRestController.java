package org.recap.controller.swagger;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.recap.ScsbConstants;
import org.recap.controller.AbstractController;
import org.recap.model.SearchRecordsResponse;
import org.recap.model.SearchResultRow;
import org.recap.model.search.SearchRecordsRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by sudhish on 13/10/16.
 */
@Slf4j
@RestController
@RequestMapping("/searchService")
@Tag(name="search")
public class SearchRecordsRestController extends AbstractController {


    /**
     * This method will call scsb-solr-client microservice to search books based on the given search records request parameter and returns a list of search result row.
     *
     * @param searchRecordsRequest the search records request
     * @return the search records response
     */
    @PostMapping(value="/search")
    @Operation(summary = "search",description ="The Search API allows the end user to search the SCSB database for bibliographic records using different fields. It takes in a JSON formatted request as input and allows pagination.",  operationId = "0")
    @ApiResponse(responseCode = "200", description = "Successful Search", content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = SearchRecordsResponse.class)) })
    public SearchRecordsResponse searchRecordsServiceGetParam(@Parameter(description = "Parameters to search a record in SCSB", required = true, name = "searchRecordsRequest")@RequestBody SearchRecordsRequest searchRecordsRequest) {
        SearchRecordsResponse searchRecordsResponse = new SearchRecordsResponse();
        try {
            HttpEntity<SearchRecordsRequest> httpEntity = new HttpEntity<>(searchRecordsRequest, getRestHeaderService().getHttpHeaders());

            ResponseEntity<SearchRecordsResponse> responseEntity = restTemplate.exchange(getScsbSolrClientUrl() + ScsbConstants.URL_SEARCH_BY_JSON, HttpMethod.POST, httpEntity, SearchRecordsResponse.class);
            searchRecordsResponse = responseEntity.getBody();
        } catch (RuntimeException e) {
            log.error("error--.",e);
            log.error(e.getMessage());
            searchRecordsResponse.setErrorMessage(e.getMessage());
        }
        return searchRecordsResponse;
    }

    /**
     *This method will call scsb-solr-client microservice to search books based on the given search parameters and returns a list of search result row.
     *
     * @param fieldValue                  the field value
     * @param fieldName                   the field name
     * @param owningInstitutions          the owning institutions
     * @param collectionGroupDesignations the collection group designations
     * @param availability                the availability
     * @param materialTypes               the material types
     * @param useRestrictions             the use restrictions
     * @param pageSize                    the page size
     * @return the list
     */
    @Hidden
    @GetMapping(value="/searchByParam")
    @Operation(summary = "searchParam",description ="The Search by param API allows the end user to search the SCSB database for bibliographic records using the parameters listed.",  operationId = "0")
    @ApiResponse(responseCode = "200", description = "Successful Search")
    public List<SearchResultRow> searchRecordsServiceGet(
            @RequestParam(name="fieldValue", required = false)  String fieldValue,
            @Parameter(name="fieldName",required = false,description = "Author_search,Title_search,TitleStartsWith,Publisher,PublicationPlace,PublicationDate,Subject,ISBN,ISSN,OCLCNumber,Notes,CallNumber_search,Barcode") @RequestParam(name="fieldName", value = "fieldName" , required = false)  String fieldName,
            @Parameter(name="owningInstitutions", description= "${swagger.values.owningInstitutions}")@RequestParam(name="owningInstitutions",required = false ) String owningInstitutions,
            @Parameter(name="collectionGroupDesignations", description = "${swagger.values.cgds}") @RequestParam(name="collectionGroupDesignations", value = "collectionGroupDesignations" , required = false)  String collectionGroupDesignations,
            @Parameter(name="availability", description = "Availability: Available, NotAvailable") @RequestParam(name="availability", value = "availability" , required = false)  String availability,
            @Parameter(name="materialTypes", description = "MaterialTypes: Monograph, Serial, Other") @RequestParam(name="materialTypes", value = "materialTypes" , required = false)  String materialTypes,
            @Parameter(name="useRestrictions", description = "Use Restrictions: NoRestrictions, InLibraryUse, SupervisedUse") @RequestParam(name="useRestrictions", value = "useRestrictions" , required = false)  String useRestrictions,
            @Parameter(name="pageSize", description = "Page Size in Numers - 10, 20 30...") @RequestParam(name="pageSize", required = false) Integer pageSize
    ) {
        HttpEntity<List> responseEntity = null;
        HttpEntity request = new HttpEntity<>(getRestHeaderService().getHttpHeaders());
        List<SearchResultRow> searchResultRows = null;
        try {

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(getScsbSolrClientUrl() + ScsbConstants.URL_SEARCH_BY_PARAM)
                    .queryParam("fieldValue", fieldValue)
                    .queryParam("fieldName", fieldName)
                    .queryParam("owningInstitutions", owningInstitutions)
                    .queryParam("collectionGroupDesignations", collectionGroupDesignations)
                    .queryParam("availability", availability)
                    .queryParam("materialTypes", materialTypes)
                    .queryParam("useRestrictions", useRestrictions)
                    .queryParam("pageSize", pageSize);

            responseEntity = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, request, List.class);
            searchResultRows = responseEntity.getBody();
        } catch (RuntimeException e) {
            searchResultRows = new ArrayList<>();
            log.error("Exception",e);
        }
        return searchResultRows;
    }

}
