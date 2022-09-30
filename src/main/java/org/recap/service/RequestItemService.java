package org.recap.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;
import org.recap.controller.swagger.RequestItemRestController;
import org.recap.entity.ItemRequestReceivedInformationEntity;
import org.recap.model.ItemRequestInformation;
import org.recap.model.request.RequestInfo;
import org.recap.model.request.RequestLogReportRequest;
import org.recap.repository.ItemRequestInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author Dinakar N created on 14/09/22
 */
@Service
public class RequestItemService {
    @Autowired
    private ItemRequestInformationRepository itemRequestInformationRepository;

    @Autowired
    private RequestItemRestController requestItemRestController;

     public void saveReceivedRequestInformation(ItemRequestInformation itemRequestInfo, boolean isResponseReceived) {
         ItemRequestReceivedInformationEntity itemRequestReceivedInformationEntity = new ItemRequestReceivedInformationEntity();
         prepareItemRequestReceivedInformationEntity(itemRequestInfo,itemRequestReceivedInformationEntity);
         if (isResponseReceived) {
             itemRequestReceivedInformationEntity.setStatus("SUCCESS");
             itemRequestReceivedInformationEntity.setStatusId(1);
         } else {
             itemRequestReceivedInformationEntity.setStatus("FAILED");
             itemRequestReceivedInformationEntity.setStatusId(2);
         }
         itemRequestInformationRepository.save(itemRequestReceivedInformationEntity);
    }

    private void prepareItemRequestReceivedInformationEntity(ItemRequestInformation itemRequestInfo, ItemRequestReceivedInformationEntity itemRequestReceivedInformationEntity) {
        Gson gson = new Gson();
        itemRequestReceivedInformationEntity.setItemOwningInstitution(itemRequestInfo.getItemOwningInstitution());
        itemRequestReceivedInformationEntity.setRequestedItemBarcode(String.join(",", itemRequestInfo.getItemBarcodes()));
        itemRequestReceivedInformationEntity.setRequestInstitution(itemRequestInfo.getRequestingInstitution());
        itemRequestReceivedInformationEntity.setRequestRecieved(gson.toJson(itemRequestInfo));
        itemRequestReceivedInformationEntity.setStatus("NA");
        itemRequestReceivedInformationEntity.setDate(new Date());
    }

    public RequestLogReportRequest getRequests(RequestLogReportRequest requestLogReportRequest) {
        List<ItemRequestReceivedInformationEntity> entityList = getRequestReports(requestLogReportRequest);
        return prepareRequestReponseFromEnityList(entityList, requestLogReportRequest);
    }

    public RequestLogReportRequest submitRequests(RequestLogReportRequest requestLogReportRequest) {
        if (requestLogReportRequest.getId() != 0) {
            ItemRequestInformation itemRequestInformation = prepareItemInfoFromrequest(requestLogReportRequest);
            if (itemRequestInformation != null)
                requestItemRestController.itemSubmitRequest(itemRequestInformation);
        } else {
            List<ItemRequestInformation> itemRequestInformationList = new ArrayList<>();
            Integer pageCount = getCount(requestLogReportRequest);
            requestLogReportRequest.setPageSize(500);
            for (int i = 0; i < pageCount; i++) {
                List<ItemRequestReceivedInformationEntity> entityList = getRequestReports(requestLogReportRequest);
                for (ItemRequestReceivedInformationEntity entity : entityList) {
                    ItemRequestInformation itemRequestInformation = prepareItemRequestInformationFromEntity(entity);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        if (itemRequestInformation != null)
                            requestItemRestController.itemSubmitRequest(itemRequestInformation);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        requestLogReportRequest.setStatus("Resubmit is successfull");
        return requestLogReportRequest;
    }

    private Integer getCount(RequestLogReportRequest requestLogReportRequest) {
         requestLogReportRequest.setPageSize(500);
        Pageable pageable = PageRequest.of(requestLogReportRequest.getPageNumber(), requestLogReportRequest.getPageSize());
        Page<ItemRequestReceivedInformationEntity> pageReponse = null;
        if(requestLogReportRequest.getInstitution() != null && !requestLogReportRequest.getInstitution().isBlank() && !requestLogReportRequest.getInstitution().isEmpty()
                && requestLogReportRequest.getStatus() != null && !requestLogReportRequest.getStatus().isBlank() && !requestLogReportRequest.getStatus().isEmpty()){
            pageReponse = itemRequestInformationRepository.findByInstitutionAndStatus(pageable,requestLogReportRequest.getInstitution(),requestLogReportRequest.getStatus());
        }else if(requestLogReportRequest.getInstitution() != null && !requestLogReportRequest.getInstitution().isBlank() && !requestLogReportRequest.getInstitution().isEmpty()) {
            pageReponse = itemRequestInformationRepository.findByInstitution(pageable,requestLogReportRequest.getInstitution());
        } else if(requestLogReportRequest.getStatus() != null && !requestLogReportRequest.getStatus().isBlank() && !requestLogReportRequest.getStatus().isEmpty()){
            pageReponse = itemRequestInformationRepository.findByStatus(pageable,requestLogReportRequest.getStatus());
        } else {
            pageReponse = itemRequestInformationRepository.findAll(pageable);
        }
       return pageReponse.getTotalPages();
    }

    private List<ItemRequestReceivedInformationEntity> getRequestReports(RequestLogReportRequest requestLogReportRequest) {
        List<ItemRequestReceivedInformationEntity> entityList = new ArrayList<>();
        Pageable pageable = PageRequest.of(requestLogReportRequest.getPageNumber(), requestLogReportRequest.getPageSize());
        Page<ItemRequestReceivedInformationEntity> pageReponse = null;
        if(requestLogReportRequest.getInstitution() != null && !requestLogReportRequest.getInstitution().isBlank() && !requestLogReportRequest.getInstitution().isEmpty()
                && requestLogReportRequest.getStatus() != null && !requestLogReportRequest.getStatus().isBlank() && !requestLogReportRequest.getStatus().isEmpty()){
            pageReponse = itemRequestInformationRepository.findByInstitutionAndStatus(pageable,requestLogReportRequest.getInstitution(),requestLogReportRequest.getStatus());
        }else if(requestLogReportRequest.getInstitution() != null && !requestLogReportRequest.getInstitution().isBlank() && !requestLogReportRequest.getInstitution().isEmpty()) {
            pageReponse = itemRequestInformationRepository.findByInstitution(pageable,requestLogReportRequest.getInstitution());
        } else if(requestLogReportRequest.getStatus() != null && !requestLogReportRequest.getStatus().isBlank() && !requestLogReportRequest.getStatus().isEmpty()){
            pageReponse = itemRequestInformationRepository.findByStatus(pageable,requestLogReportRequest.getStatus());
        } else {
            pageReponse = itemRequestInformationRepository.findAll(pageable);
        }
        entityList = pageReponse.getContent();
        requestLogReportRequest.setTotalPageCount(pageReponse.getTotalPages());
        requestLogReportRequest.setTotalRecordsCount(pageReponse.getTotalElements());
        return entityList;
     }

    private RequestLogReportRequest prepareRequestReponseFromEnityList(List<ItemRequestReceivedInformationEntity> entityList, RequestLogReportRequest requestLogReportRequest) {
         List<RequestInfo> requestInfoList = new ArrayList<>();
        for (ItemRequestReceivedInformationEntity entity : entityList) {
            RequestInfo requestInfo = new RequestInfo();
            requestInfo.setRequestInstitution(entity.getRequestInstitution());
            requestInfo.setItemOwningInstitution(entity.getItemOwningInstitution());
            requestInfo.setRequestedItemBarcode(entity.getRequestedItemBarcode());
            requestInfo.setDate(entity.getDate());
            requestInfo.setStatus(entity.getStatus());
            requestInfo.setId(entity.getId());
            requestInfoList.add(requestInfo);
        }
        requestLogReportRequest.setRequestInfoList(requestInfoList);
        return requestLogReportRequest;
    }
    @Transactional
    public void updateItemRequest(ItemRequestInformation itemRequestInfo) {
        updateReceivedRequestInformation(itemRequestInfo,Boolean.TRUE);
    }

    public void updateReceivedRequestInformation(ItemRequestInformation itemRequestInfo, boolean isResponseReceived) {
        if (isResponseReceived)
            itemRequestInformationRepository.update(itemRequestInfo.getId(),"SUCCESS",1);
    }

    private ItemRequestInformation prepareItemInfoFromrequest(RequestLogReportRequest requestLogReportRequest) {
        Optional<ItemRequestReceivedInformationEntity> itemRequestReceivedInformationEntity = itemRequestInformationRepository.findById(requestLogReportRequest.getId());
        ItemRequestInformation itemRequestInformation = null;
        if (itemRequestReceivedInformationEntity.isPresent()) {
            String requestRecieved = itemRequestReceivedInformationEntity.get().getRequestRecieved();
            Gson gsonObj = new Gson();
            itemRequestInformation = gsonObj.fromJson(requestRecieved, ItemRequestInformation.class);
        }
        itemRequestInformation.setId(requestLogReportRequest.getId());
        return itemRequestInformation;
    }

    private ItemRequestInformation prepareItemRequestInformationFromEntity(ItemRequestReceivedInformationEntity itemRequestReceivedInformationEntity) {
        ItemRequestInformation itemRequestInformation = null;
        String requestRecieved = itemRequestReceivedInformationEntity.getRequestRecieved();
        Gson gsonObj = new Gson();
        itemRequestInformation = gsonObj.fromJson(requestRecieved, ItemRequestInformation.class);
        itemRequestInformation.setId(itemRequestReceivedInformationEntity.getId());
        return itemRequestInformation;
    }

}
