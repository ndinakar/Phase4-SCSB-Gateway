package org.recap.model;

import lombok.Data;

/**
 * Created by sudhishk on 16/12/16.
 */
@Data
public class ItemCheckinResponse extends BaseResponseItem {
    private boolean alert;
    private boolean magneticMedia;
    private boolean resensitize;
    private String dueDate;
    private String feeType ;
    private String securityInhibit;
    private String currencyType;
    private String feeAmount;
    private String mediaType;
    private String permanentLocation;
    private String sortBin;
    private String collectionCode;
    private String callNumber;
    private String destinationLocation;
    private String alertType;
    private String holdPatronId;
    private String holdPatronName;
    private boolean processed;
    private String bibId;
}
