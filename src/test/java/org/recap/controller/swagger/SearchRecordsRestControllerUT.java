package org.recap.controller.swagger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.recap.BaseTestCase;
import org.recap.PropertyKeyConstants;
import org.recap.ScsbCommonConstants;
import org.recap.ScsbConstants;
import org.recap.model.SearchRecordsResponse;
import org.recap.model.SearchResultRow;
import org.recap.model.search.SearchRecordsRequest;
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
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Created by hemalathas on 3/2/17.
 */
public class SearchRecordsRestControllerUT extends BaseTestCase{

    @Value("${" + PropertyKeyConstants.SCSB_SOLR_DOC_URL + "}")
    String scsbSolrClient;

    @Mock
    RestTemplate mockRestTemplate;

    @InjectMocks
    SearchRecordsRestController searchRecordsRestController;

    @Mock
    SearchRecordsRestController mockSearchRecordsRestController;

    @Mock
    RestHeaderService restHeaderService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(searchRecordsRestController,"scsbSolrClient",scsbSolrClient);
        ReflectionTestUtils.setField(searchRecordsRestController,"restHeaderService",restHeaderService);
    }

    public String getScsbSolrClient() {
        return scsbSolrClient;
    }

    public void setScsbSolrClient(String scsbSolrClient) {
        this.scsbSolrClient = scsbSolrClient;
    }

    @Test
    public void testSearchRecordServiceGet(){
        HttpEntity request = new HttpEntity(restHeaderService.getHttpHeaders());
        List<SearchResultRow> searchResultRowList = new ArrayList<>();
        ResponseEntity<List> httpEntity = new ResponseEntity<List>(searchResultRowList,HttpStatus.OK);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(scsbSolrClient + ScsbConstants.URL_SEARCH_BY_PARAM)
                .queryParam("fieldValue", "test")
                .queryParam("fieldName", "test")
                .queryParam("owningInstitutions", "PUL")
                .queryParam("collectionGroupDesignations","Shared")
                .queryParam("availability","Available")
                .queryParam("materialTypes","Monograph")
                .queryParam("useRestrictions","NoRestrictions")
                .queryParam("pageSize", 10);
        Mockito.when(mockRestTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, request, List.class)).thenReturn(httpEntity);
        List<SearchResultRow> searchResultRows= searchRecordsRestController.searchRecordsServiceGet("test","test","PUL","Shared","Available","Monograph","NoRestrictions",10);
        assertNotNull(searchResultRows);

    }

   @Test
    public void testSearchRecordServiceGet_Exception(){
        HttpEntity request = new HttpEntity(restHeaderService.getHttpHeaders());
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(scsbSolrClient + ScsbConstants.URL_SEARCH_BY_PARAM)
                .queryParam("fieldValue", "test")
                .queryParam("fieldName", "test")
                .queryParam("owningInstitutions", "PUL")
                .queryParam("collectionGroupDesignations","Shared")
                .queryParam("availability","Available")
                .queryParam("materialTypes","Monograph")
                .queryParam("useRestrictions","NoRestrictions")
                .queryParam("pageSize", 10);
        Mockito.when(mockRestTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, request, List.class)).thenReturn(null);
        List<SearchResultRow> searchResultRows= searchRecordsRestController.searchRecordsServiceGet("test","test","PUL","Shared","Available","Monograph","NoRestrictions",10);
        assertNotNull(searchResultRows);
    }

    private SearchRecordsRequest getSearchRecordsRequest() {
        SearchRecordsRequest searchRecordsRequest = new SearchRecordsRequest();
        searchRecordsRequest.setFieldValue("test");
        searchRecordsRequest.setFieldName("test");
        searchRecordsRequest.setAvailability(Arrays.asList("Available"));
        searchRecordsRequest.setOwningInstitutions(Arrays.asList("PUL"));
        searchRecordsRequest.setCollectionGroupDesignations(Arrays.asList("Open"));
        searchRecordsRequest.setUseRestrictions(Arrays.asList("Others"));
        searchRecordsRequest.setMaterialTypes(Arrays.asList("Monograph"));
        searchRecordsRequest.setCatalogingStatus("Complete");
        searchRecordsRequest.setDeleted(false);
        searchRecordsRequest.setPageSize(10);
        searchRecordsRequest.setPageNumber(10);
        return searchRecordsRequest;
    }

    public SearchRecordsResponse getSearchRecordsResponse(){
        SearchRecordsResponse searchRecordsResponse = new SearchRecordsResponse();
        searchRecordsResponse.setTotalPageCount(3);
        searchRecordsResponse.setTotalRecordsCount("1");
        searchRecordsResponse.setShowTotalCount(true);
        searchRecordsResponse.setTotalBibRecordsCount("1");
        searchRecordsResponse.setTotalItemRecordsCount("1");
        searchRecordsResponse.setErrorMessage("message");
        searchRecordsResponse.setSearchResultRows(Arrays.asList(new SearchResultRow()));
        return searchRecordsResponse;
    }

    @Test
    public void testSearchRecordwithParam(){

        ResponseEntity<SearchRecordsResponse> responseEntity = new ResponseEntity(getSearchRecordsResponse(), HttpStatus.OK);
        HttpEntity requestEntity = getSwaggerHttpEntity();
        Mockito.when(mockRestTemplate.exchange(scsbSolrClient + ScsbConstants.URL_SEARCH_BY_JSON, HttpMethod.POST, requestEntity, SearchRecordsResponse.class)).thenReturn(responseEntity);
        SearchRecordsResponse searchRecordsResponse = searchRecordsRestController.searchRecordsServiceGetParam(getSearchRecordsRequest());
        assertNotNull(searchRecordsResponse);

    }

    @Test
    public void testSearchRecordwithParam_Exception(){
        ResponseEntity responseEntity = new ResponseEntity(getSearchRecordsResponse(), HttpStatus.OK);
        HttpEntity requestEntity = getSwaggerHttpEntity();
        Mockito.when(mockRestTemplate.exchange(scsbSolrClient + ScsbConstants.URL_SEARCH_BY_JSON, HttpMethod.POST, requestEntity, SearchRecordsResponse.class)).thenReturn(null);
        SearchRecordsResponse searchRecordsResponse = searchRecordsRestController.searchRecordsServiceGetParam(getSearchRecordsRequest());
        assertNotNull(searchRecordsResponse);

    }
    public HttpEntity getSwaggerHttpEntity(){
        return new HttpEntity<>(getSearchRecordsRequest(),getSwaggerHeaders());
    }
    public static HttpHeaders getSwaggerHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(ScsbCommonConstants.API_KEY, SwaggerAPIProvider.getInstance().getSwaggerApiKey());
        return headers;
    }
}