package org.recap.model;

import lombok.Data;


/**
 * Created by sudhishk on 16/12/16.
 */
@Data
public class ItemCheckoutResponse extends BaseResponseItem {
    private Boolean renewal;
    private Boolean magneticMedia;
    private Boolean desensitize;
    private String dueDate;
    private String feeType ;
    private String securityInhibit;
    private String currencyType;
    private String feeAmount;
    private String mediaType;
    private boolean processed;
    private String bibId;
}
