package org.recap.model;

import lombok.Data;


/**
 * Created by sudhishk on 16/12/16.
 */
@Data
public class ItemRecallResponse extends AbstractResponseItem {
    private Boolean available;
    private String transactionDate;
    private String institutionID;
    private String patronIdentifier;
    private String titleIdentifier;
    private String expirationDate;
    private String pickupLocation;
    private String queuePosition;
    private String bibId;
    private String ISBN;
    private String LCCN;
    private String jobId;
}
