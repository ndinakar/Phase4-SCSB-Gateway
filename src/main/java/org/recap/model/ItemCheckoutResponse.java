package org.recap.model;

import lombok.Data;

/**
 * Created by sudhishk on 16/12/16.
 */
@Data
public class ItemCheckoutResponse extends AbstractResponseItem {
    private Boolean Renewal;
    private Boolean magneticMedia;
    private Boolean Desensitize;
    private String transactionDate;
    private String institutionID;
    private String patronIdentifier;
    private String titleIdentifier;
    private String dueDate;
    private String feeType ;
    private String securityInhibit;
    private String currencyType;
    private String feeAmount;
    private String mediaType;
    private String bibId;
    private String ISBN;
    private String LCCN;
    private String jobId;
    private boolean processed;
    private String updatedDate;
    private String createdDate;
}
