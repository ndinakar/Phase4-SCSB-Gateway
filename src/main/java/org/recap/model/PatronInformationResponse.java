package org.recap.model;

import lombok.Data;


import java.util.List;

/**
 * Created by sudhishk on 26/12/16.
 */
@Data
public class PatronInformationResponse extends AbstractResponseItem {
    private String patronIdentifier = "";
    private String patronName = "";
    private String Email = "";
    private String BirthDate;
    private String Phone;
    private String PermanentLocation;
    private String PickupLocation;
    private int ChargedItemsCount;
    private int ChargedItemsLimit;
    private String FeeLimit;
    private String FeeType;
    private int HoldItemsCount;
    private int HoldItemsLimit;
    private int UnavailableHoldsCount;
    private int FineItemsCount;
    private String FeeAmount;
    private String HomeAddress;
    private List<String> Items;
    private String ItemType;
    private int OverdueItemsCount;
    private int OverdueItemsLimit;
    private String PacAccessType;
    private String PatronGroup;
    private String PatronType;
    private String DueDate;
    private String ExpirationDate;
    private String Status;
}
