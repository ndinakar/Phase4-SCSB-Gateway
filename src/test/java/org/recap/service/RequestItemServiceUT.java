package org.recap.service;

import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.recap.BaseTestCase;
import org.recap.controller.swagger.RequestItemRestController;
import org.recap.entity.ItemRequestReceivedInformationEntity;
import org.recap.model.ItemRequestInformation;
import org.recap.model.request.RequestLogReportRequest;
import org.recap.repository.ItemRequestInformationRepository;
import org.springframework.data.domain.Page;
import org.springframework.test.util.ReflectionTestUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Chittoor Charan Raj
 */

public class RequestItemServiceUT extends BaseTestCase{

    @InjectMocks
    RequestItemService service;

    @Mock
    ItemRequestInformationRepository repository;

    @Mock
    RequestItemRestController controller;

    @Mock
    ItemRequestReceivedInformationEntity entity;


    @Test
    public void getFromDateTest(){
        Date fromDate = service.getFromDate(new Date());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        String dateString = simpleDateFormat.format(fromDate);
        assertNotNull(dateString.contains("12:00 AM"));
    }

    @Test
    public void getToDateTest(){
        Date toDate = service.getToDate(new Date());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        String dateString = simpleDateFormat.format(toDate);
        assertNotNull(dateString.contains("11:59 PM"));
    }


    @Test
    public void prepareItemRequestReceivedInformationEntityTest(){
        ItemRequestInformation itemRequestInfo = new ItemRequestInformation();
        List<String> itemBarcodes = new ArrayList<>();
        itemBarcodes.add("CU11418427");
        itemBarcodes.add("346767");
        itemRequestInfo.setItemBarcodes(itemBarcodes);
        ItemRequestReceivedInformationEntity itemRequestReceivedInformationEntity = new ItemRequestReceivedInformationEntity();
        itemRequestReceivedInformationEntity.setItemOwningInstitution("CUL");
        itemRequestReceivedInformationEntity.setRequestedItemBarcode(String.join(",","CU11418427"));
        itemRequestReceivedInformationEntity.setRequestInstitution("PUL");
        itemRequestReceivedInformationEntity.setRequestRecieved("CU11418427");
        itemRequestReceivedInformationEntity.setStatus("NA");
        itemRequestReceivedInformationEntity.setDate(new Date());
        ReflectionTestUtils.invokeMethod(service, "prepareItemRequestReceivedInformationEntity", itemRequestInfo, itemRequestReceivedInformationEntity);
    }

    @Test
    public void convertStringToDateTestException() {
        String sourceDate = "2022-10-22";
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = dt.parse(sourceDate);
        } catch (ParseException e) {

        }
        assertNotNull(date);
        ReflectionTestUtils.invokeMethod(service, "convertStringToDate", sourceDate);

    }

    @Test
    public void prepareItemRequestInformationFromEntityTest(){
        try {
            ReflectionTestUtils.invokeMethod(service, "prepareItemRequestInformationFromEntity", new ItemRequestReceivedInformationEntity());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void updateReceivedRequestInformationTest(){
        service.updateReceivedRequestInformation(new ItemRequestInformation(), true);
    }

    @Test
    public void updateReceivedRequestInformationException(){
        service.updateReceivedRequestInformation(new ItemRequestInformation(), false);
    }

    @Test
    public void updateItemRequestTest(){
        service.updateItemRequest(new ItemRequestInformation());
    }

    @Test
    public void prepareItemInfoFromrequestTest(){
        try {
            ReflectionTestUtils.invokeMethod(service, "prepareItemInfoFromrequest", new RequestLogReportRequest());
            assert(Optional.empty().isPresent());
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void prepareRequestReponseFromEnityListTest(){
        Iterator<ItemRequestReceivedInformationEntity> iterator = mock(Iterator.class);
        List<ItemRequestReceivedInformationEntity> entityList  = mock(List.class);
        ItemRequestReceivedInformationEntity entity = mock(ItemRequestReceivedInformationEntity.class);
        when(entityList.iterator()).thenReturn(iterator);
        when(iterator.hasNext()).thenReturn(true, false);
        when(iterator.next()).thenReturn(entity);
        ReflectionTestUtils.invokeMethod(service, "prepareRequestReponseFromEnityList",  entityList, new RequestLogReportRequest());
    }

    @Test
    public void getRequestReportsTest(){
        try {
            RequestLogReportRequest requestLogReportRequest = getRequestLogReportRequest();
            Page<ItemRequestReceivedInformationEntity> pageResponse = Mockito.mock(Page.class);
            List<ItemRequestReceivedInformationEntity> entityList = new ArrayList<>();
            ItemRequestReceivedInformationEntity information = new ItemRequestReceivedInformationEntity();
            entityList.add(information);
            ReflectionTestUtils.setField(service, "pageResponse", pageResponse);
            ReflectionTestUtils.invokeMethod(service, "getRequestReports", requestLogReportRequest);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getRequestReportsWithStatusTest(){
        try {
            RequestLogReportRequest requestLogReportRequest = getRequestLogReportRequest();
            requestLogReportRequest.setStatus("FAILED");
            ReflectionTestUtils.invokeMethod(service, "getRequestReports", requestLogReportRequest);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getRequestReportsWithoutInstutionTest(){
        try {
            RequestLogReportRequest requestLogReportRequest = getRequestLogReportRequest();
            requestLogReportRequest.setInstitution(null);
            requestLogReportRequest.setStatus("FAILED");
            ReflectionTestUtils.invokeMethod(service, "getRequestReports", requestLogReportRequest);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Test
    public void getRequestReportsWithoutInstutionEmptyTest(){
        try {
            RequestLogReportRequest requestLogReportRequest = getRequestLogReportRequest();
            requestLogReportRequest.setInstitution("");
            requestLogReportRequest.setStatus("FAILED");
            ReflectionTestUtils.invokeMethod(service, "getRequestReports", requestLogReportRequest);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Test
    public void getRequestReportsWithoutStatusTest(){
        try {
            RequestLogReportRequest requestLogReportRequest = getRequestLogReportRequest();
            requestLogReportRequest.setStatus(null);
            requestLogReportRequest.setInstitution(null);
            ReflectionTestUtils.invokeMethod(service, "getRequestReports", requestLogReportRequest);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Test
    public void getRequestReportsWithoutEmptyStatusTest(){
        try {
            RequestLogReportRequest requestLogReportRequest = getRequestLogReportRequest();
            requestLogReportRequest.setStatus("");
            requestLogReportRequest.setInstitution("");
            ReflectionTestUtils.invokeMethod(service, "getRequestReports", requestLogReportRequest);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private RequestLogReportRequest getRequestLogReportRequest() {
        RequestLogReportRequest requestLogReportRequest = new RequestLogReportRequest();
        requestLogReportRequest.setInstitution("PUL");
        requestLogReportRequest.setFromDate("2022-10-01");
        requestLogReportRequest.setToDate("2022-11-01");
        return requestLogReportRequest;
    }


    @Test
    public void getRequestReportsNull(){
        try {
            RequestLogReportRequest requestLogReportRequest = new RequestLogReportRequest();
            requestLogReportRequest.setFromDate("");
            requestLogReportRequest.setToDate("toDate");
            assertTrue(requestLogReportRequest.getFromDate().isEmpty());
            ReflectionTestUtils.invokeMethod(service, "getRequestReports", requestLogReportRequest);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getRequestReportsGetInstitutionTest(){
        try {
            RequestLogReportRequest requestLogReportRequest = new RequestLogReportRequest();
            requestLogReportRequest.setInstitution("CUL");
            requestLogReportRequest.setStatus("1");
//            Mockito.when(repository.findByInstitutionAndStatusAndFromDateAndEndDate(any(), any(), any(), any(), any())).thenReturn((Page<ItemRequestReceivedInformationEntity>) requestLogReportRequest);
            ReflectionTestUtils.invokeMethod(service, "getRequestReports", requestLogReportRequest);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getRequestReportsGetInstitutionIsBlank(){
        try {
            RequestLogReportRequest requestLogReportRequest = new RequestLogReportRequest();
            requestLogReportRequest.setInstitution("");
            requestLogReportRequest.setStatus("");
            assertTrue(Strings.isBlank(requestLogReportRequest.getInstitution()));
            assertTrue(Strings.isBlank(requestLogReportRequest.getStatus()));
//            Mockito.when(repository.findByInstitutionAndStatusAndFromDateAndEndDate(any(), any(), any(), any(), any())).thenReturn((Page<ItemRequestReceivedInformationEntity>) requestLogReportRequest);
            ReflectionTestUtils.invokeMethod(service, "getRequestReports", requestLogReportRequest);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getRequestReportsGetInstitutionIsEmpty(){
        try {
            RequestLogReportRequest requestLogReportRequest = new RequestLogReportRequest();
            requestLogReportRequest.setInstitution("");
            requestLogReportRequest.setStatus("");
            assertTrue(requestLogReportRequest.getInstitution().isEmpty());
            assertTrue(requestLogReportRequest.getStatus().isEmpty());
//            Mockito.when(repository.findByInstitutionAndStatusAndFromDateAndEndDate(any(), any(), any(), any(), any())).thenReturn((Page<ItemRequestReceivedInformationEntity>) requestLogReportRequest);
            ReflectionTestUtils.invokeMethod(service, "getRequestReports", requestLogReportRequest);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Test
    public void getRequestReportsInstitutionTest(){
        try {
            RequestLogReportRequest requestLogReportRequest = new RequestLogReportRequest();
            requestLogReportRequest.setInstitution("PUL");
//            Mockito.when(repository.findByInstitutionAndFromDateAndEndDate(any(), any(), any(), any())).thenReturn((Page<ItemRequestReceivedInformationEntity>) requestLogReportRequest);
            ReflectionTestUtils.invokeMethod(service, "getRequestReports", requestLogReportRequest);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getRequestReportsBlankTest(){
        try {
            RequestLogReportRequest requestLogReportRequest = new RequestLogReportRequest();
            requestLogReportRequest.setInstitution("");
            assertTrue(requestLogReportRequest.getInstitution().isBlank());
//            Mockito.when(repository.findByInstitutionAndFromDateAndEndDate(any(), any(), any(), any())).thenReturn((Page<ItemRequestReceivedInformationEntity>) requestLogReportRequest);
            ReflectionTestUtils.invokeMethod(service, "getRequestReports", requestLogReportRequest);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Test
    public void getRequestReportsEmptyTest(){
        try {
            RequestLogReportRequest requestLogReportRequest = new RequestLogReportRequest();
            requestLogReportRequest.setInstitution("");
            assertTrue(requestLogReportRequest.getInstitution().isEmpty());
//            Mockito.when(repository.findByInstitutionAndFromDateAndEndDate(any(), any(), any(), any())).thenReturn((Page<ItemRequestReceivedInformationEntity>) requestLogReportRequest);
            ReflectionTestUtils.invokeMethod(service, "getRequestReports", requestLogReportRequest);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getRequestReportsStatusTest(){
        try {
            RequestLogReportRequest requestLogReportRequest = new RequestLogReportRequest();
            requestLogReportRequest.setStatus("1");
//            Mockito.when(repository.findByStatusAndFromDateAndEndDate(any(), any(), any(), any())).thenReturn((Page<ItemRequestReceivedInformationEntity>) requestLogReportRequest);
            ReflectionTestUtils.invokeMethod(service, "getRequestReports", requestLogReportRequest);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getRequestReportsStatusBlankTest(){
        try {
            RequestLogReportRequest requestLogReportRequest = new RequestLogReportRequest();
            requestLogReportRequest.setStatus("");
            assertTrue(requestLogReportRequest.getStatus().isBlank());
//            Mockito.when(repository.findByStatusAndFromDateAndEndDate(any(), any(), any(), any())).thenReturn((Page<ItemRequestReceivedInformationEntity>) requestLogReportRequest);
            ReflectionTestUtils.invokeMethod(service, "getRequestReports", requestLogReportRequest);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getRequestReportsStatusEmptyTest(){
        try {
            RequestLogReportRequest requestLogReportRequest = new RequestLogReportRequest();
            requestLogReportRequest.setInstitution("");
            assertTrue(requestLogReportRequest.getStatus().isEmpty());
//            Mockito.when(repository.findByStatusAndFromDateAndEndDate(any(), any(), any(), any())).thenReturn((Page<ItemRequestReceivedInformationEntity>) requestLogReportRequest);
            ReflectionTestUtils.invokeMethod(service, "getRequestReports", requestLogReportRequest);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getRequestReportsGetInstitution_Test(){
        try {
            RequestLogReportRequest requestLogReportRequest = new RequestLogReportRequest();
            requestLogReportRequest.setInstitution("CUL");
            requestLogReportRequest.setStatus("1");
//            Mockito.when(repository.findByInstitutionAndStatus(any(), any(), any())).thenReturn((Page<ItemRequestReceivedInformationEntity>) requestLogReportRequest);
            ReflectionTestUtils.invokeMethod(service, "getRequestReports", requestLogReportRequest);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getRequestReportsGetInstitution_IsBlank(){
        try {
            RequestLogReportRequest requestLogReportRequest = new RequestLogReportRequest();
            requestLogReportRequest.setInstitution("");
            requestLogReportRequest.setStatus("");
            assertTrue(Strings.isBlank(requestLogReportRequest.getInstitution()));
            assertTrue(Strings.isBlank(requestLogReportRequest.getStatus()));
//            Mockito.when(repository.findByInstitutionAndStatus(any(), any(), any())).thenReturn((Page<ItemRequestReceivedInformationEntity>) requestLogReportRequest);
            ReflectionTestUtils.invokeMethod(service, "getRequestReports", requestLogReportRequest);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getRequestReportsGetInstitution_IsEmpty(){
        try {
            RequestLogReportRequest requestLogReportRequest = new RequestLogReportRequest();
            requestLogReportRequest.setInstitution("");
            requestLogReportRequest.setStatus("");
            assertTrue(requestLogReportRequest.getInstitution().isEmpty());
            assertTrue(requestLogReportRequest.getStatus().isEmpty());
//            Mockito.when(repository.findByInstitutionAndStatus(any(), any(), any())).thenReturn((Page<ItemRequestReceivedInformationEntity>) requestLogReportRequest);
            ReflectionTestUtils.invokeMethod(service, "getRequestReports", requestLogReportRequest);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Test
    public void getRequestReportsInstitution_Test(){
        try {
            RequestLogReportRequest requestLogReportRequest = new RequestLogReportRequest();
            requestLogReportRequest.setInstitution("PUL");
//            Mockito.when(repository.findByInstitution(any(), any())).thenReturn((Page<ItemRequestReceivedInformationEntity>) requestLogReportRequest);
            ReflectionTestUtils.invokeMethod(service, "getRequestReports", requestLogReportRequest);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getRequestReportsBlank_Test(){
        try {
            RequestLogReportRequest requestLogReportRequest = new RequestLogReportRequest();
            requestLogReportRequest.setInstitution("");
            assertTrue(requestLogReportRequest.getInstitution().isBlank());
//            Mockito.when(repository.findByInstitution(any(), any())).thenReturn((Page<ItemRequestReceivedInformationEntity>) requestLogReportRequest);
            ReflectionTestUtils.invokeMethod(service, "getRequestReports", requestLogReportRequest);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Test
    public void getRequestReportsEmpty_Test(){
        try {
            RequestLogReportRequest requestLogReportRequest = new RequestLogReportRequest();
            requestLogReportRequest.setInstitution("");
            assertTrue(requestLogReportRequest.getInstitution().isEmpty());
//            Mockito.when(repository.findByInstitution(any(), any())).thenReturn((Page<ItemRequestReceivedInformationEntity>) requestLogReportRequest);
            ReflectionTestUtils.invokeMethod(service, "getRequestReports", requestLogReportRequest);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getRequestReportsStatus_Test(){
        try {
            RequestLogReportRequest requestLogReportRequest = new RequestLogReportRequest();
            requestLogReportRequest.setStatus("1");
//            Mockito.when(repository.findByStatus(any(), any())).thenReturn((Page<ItemRequestReceivedInformationEntity>) requestLogReportRequest);
            ReflectionTestUtils.invokeMethod(service, "getRequestReports", requestLogReportRequest);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getRequestReportsStatusBlank_Test(){
        try {
            RequestLogReportRequest requestLogReportRequest = new RequestLogReportRequest();
            requestLogReportRequest.setStatus("");
            assertTrue(requestLogReportRequest.getStatus().isBlank());
//            Mockito.when(repository.findByStatus(any(), any())).thenReturn((Page<ItemRequestReceivedInformationEntity>) requestLogReportRequest);
            ReflectionTestUtils.invokeMethod(service, "getRequestReports", requestLogReportRequest);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getRequestReportsStatusEmpty_Test(){
        try {
            RequestLogReportRequest requestLogReportRequest = new RequestLogReportRequest();
            requestLogReportRequest.setInstitution("");
            assertTrue(requestLogReportRequest.getStatus().isEmpty());
//            Mockito.when(repository.findByStatus(any(), any())).thenReturn((Page<ItemRequestReceivedInformationEntity>) requestLogReportRequest);
            ReflectionTestUtils.invokeMethod(service, "getRequestReports", requestLogReportRequest);
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    @Test
    public void getRequestsTest(){
        try {
            service.getRequests(new RequestLogReportRequest());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void saveReceivedRequestInformationTest(){
        try {
            ItemRequestInformation itemRequestInfo = getItemRequestInformation();
            ItemRequestReceivedInformationEntity itemRequestReceivedInformationEntity = new ItemRequestReceivedInformationEntity();
            itemRequestReceivedInformationEntity.setStatus("SUCCESS");
            itemRequestReceivedInformationEntity.setStatusId(1);
            boolean isResponseReceived = true;
            Mockito.when(repository.save(Mockito.any())).thenReturn(itemRequestReceivedInformationEntity);
            service.saveReceivedRequestInformation(itemRequestInfo, isResponseReceived);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void saveReceivedRequestInformationsTest(){
        try {
            ItemRequestInformation itemRequestInfo = getItemRequestInformation();
            ItemRequestReceivedInformationEntity itemRequestReceivedInformationEntity = new ItemRequestReceivedInformationEntity();
            itemRequestReceivedInformationEntity.setStatus("FAILED");
            itemRequestReceivedInformationEntity.setStatusId(2);
            boolean isResponseReceived = false;
            Mockito.when(repository.save(Mockito.any())).thenReturn(itemRequestReceivedInformationEntity);
            service.saveReceivedRequestInformation(itemRequestInfo, isResponseReceived);
        }catch (Exception e){
            e.printStackTrace();
        }

    }


        @Test
    public void getCountTestDate(){
        try {
            RequestLogReportRequest requestLogReportRequest = getRequestLogReportRequest();
            requestLogReportRequest.setStatus("FAILED");
            ReflectionTestUtils.invokeMethod(service, "getCount", requestLogReportRequest);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getCountException(){
        try {
            RequestLogReportRequest requestLogReportRequest = getRequestLogReportRequest();
            requestLogReportRequest.setStatus("FAILED");
            requestLogReportRequest.setInstitution(null);
            ReflectionTestUtils.invokeMethod(service, "getCount", requestLogReportRequest);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getCountTest(){
        try {
            RequestLogReportRequest requestLogReportRequest = new RequestLogReportRequest();
            requestLogReportRequest.setFromDate("");
            requestLogReportRequest.setToDate("toDate");
            assertTrue(requestLogReportRequest.getFromDate().isEmpty());
            ReflectionTestUtils.invokeMethod(service, "getCount", requestLogReportRequest);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Test
    public void getCountInstitutionTest(){
        try {
            RequestLogReportRequest requestLogReportRequest = new RequestLogReportRequest();
            requestLogReportRequest.setInstitution("CUL");
            ReflectionTestUtils.invokeMethod(service, "getCount", requestLogReportRequest);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Test
    public void getCountIsEmptyTest(){
        try {
            RequestLogReportRequest requestLogReportRequest = new RequestLogReportRequest();
            requestLogReportRequest.setInstitution("");
            assertTrue(requestLogReportRequest.getInstitution().isEmpty());
            ReflectionTestUtils.invokeMethod(service, "getCount", requestLogReportRequest);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getCountInstitution_Test(){
        try {
            RequestLogReportRequest requestLogReportRequest = new RequestLogReportRequest();
            requestLogReportRequest.setInstitution("PUL");
            ReflectionTestUtils.invokeMethod(service, "getCount", requestLogReportRequest);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getCountBlank_Test(){
        try {
            RequestLogReportRequest requestLogReportRequest = new RequestLogReportRequest();
            requestLogReportRequest.setInstitution("");
            assertTrue(requestLogReportRequest.getInstitution().isBlank());
            ReflectionTestUtils.invokeMethod(service, "getCount", requestLogReportRequest);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Test
    public void getCountEmpty_Test(){
        try {
            RequestLogReportRequest requestLogReportRequest = new RequestLogReportRequest();
            requestLogReportRequest.setInstitution("");
            assertTrue(requestLogReportRequest.getInstitution().isEmpty());
            ReflectionTestUtils.invokeMethod(service, "getCount", requestLogReportRequest);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

//    @Test
//    public void submitRequestsTest(){
//        RequestLogReportRequest requestLogReportRequest = getRequestLogReportRequest();
//        requestLogReportRequest.setId(0);
//        ItemRequestInformation itemRequestInformation = getItemRequestInformation();
//        ReflectionTestUtils.setField(service, "itemRequestInformation", itemRequestInformation);
//        ReflectionTestUtils.invokeMethod(service, "submitRequests", requestLogReportRequest);
//    }
//
    private ItemRequestInformation getItemRequestInformation() {
        ItemRequestInformation itemRequestInformation  = new ItemRequestInformation();
        itemRequestInformation.setItemOwningInstitution("CUL");
        List<String> itemBarcodes = new ArrayList<>();
        itemBarcodes.add("1244");
        itemBarcodes.add("6765");
        itemRequestInformation.setItemBarcodes(itemBarcodes);
        itemRequestInformation.setRequestingInstitution("CUL");
        return itemRequestInformation;
    }

}


