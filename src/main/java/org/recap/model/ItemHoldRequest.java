package org.recap.model;

import lombok.Data;


/**
 * Created by sudhishk on 15/12/16.
 */
@Data
public class ItemHoldRequest extends AbstractRequestItem {
    private String patronIdentifier;
    private String bibId;
    private String pickupLocation;
    private String trackingId;
    private String title;
    private String author;
    private String callNumber;
}
