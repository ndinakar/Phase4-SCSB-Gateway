package org.recap.model;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by hemalathas on 1/11/16.
 */
@Getter
@Setter
public class ItemResponseInformation {
    private String patronBarcode;
    private List<String> itemBarcodes;
    private String requestType;
    private String deliveryLocation;
    private String requestingInstitution;
    private String bibliographicId;
    private String expirationDate;
    private String screenMessage;
    private boolean success;
    private String emailAddress;
    private String titleIdentifier;
}
