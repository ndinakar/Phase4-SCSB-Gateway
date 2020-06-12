package org.recap.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by sudhishk on 16/12/16.
 */
@Getter
@Setter
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
