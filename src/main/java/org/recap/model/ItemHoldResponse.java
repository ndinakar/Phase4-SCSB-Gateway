package org.recap.model;


import lombok.Getter;
import lombok.Setter;

/**
 * Created by sudhishk on 16/12/16.
 */
@Getter
@Setter
public class ItemHoldResponse extends BaseResponseItem {

    private Boolean available;
    private String expirationDate;
    private String pickupLocation;
    private String queuePosition;
    private String trackingId;
    private String bibId;

}
