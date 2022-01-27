package org.recap.model;

import lombok.Data;


/**
 * Created by sudhishk on 15/12/16.
 */
@Data
public class ItemHoldCancelRequest extends AbstractRequestItem{
    private String patronIdentifier;
    private String bibId;
    private String pickupLocation;
    private String trackingId;
}
