package org.recap.model;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * Created by sudhishk on 15/12/16.
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ItemRecalRequest extends AbstractRequestItem {
    private String patronIdentifier;
    private String bibId;
    private String pickupLocation;
}
