package org.recap.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by sudhishk on 16/12/16.
 */
@Getter
@Setter
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


    /**
     * Gets permanent location.
     *
     * @return the permanent location
     */
    public String getPermanentLocation() {
        return permanentLocation;
    }

    /**
     * Sets permanent location.
     *
     * @param permanentLocation the permanent location
     */
    public void setPermanentLocation(String permanentLocation) {
        this.permanentLocation = permanentLocation;
    }

    /**
     * Gets sort bin.
     *
     * @return the sort bin
     */
    public String getSortBin() {
        return sortBin;
    }

    /**
     * Sets sort bin.
     *
     * @param sortBin the sort bin
     */
    public void setSortBin(String sortBin) {
        this.sortBin = sortBin;
    }

    /**
     * Gets collection code.
     *
     * @return the collection code
     */
    public String getCollectionCode() {
        return collectionCode;
    }

    /**
     * Sets collection code.
     *
     * @param collectionCode the collection code
     */
    public void setCollectionCode(String collectionCode) {
        this.collectionCode = collectionCode;
    }

    /**
     * Gets call number.
     *
     * @return the call number
     */
    public String getCallNumber() {
        return callNumber;
    }

    /**
     * Sets call number.
     *
     * @param callNumber the call number
     */
    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }

    /**
     * Gets destination location.
     *
     * @return the destination location
     */
    public String getDestinationLocation() {
        return destinationLocation;
    }

    /**
     * Sets destination location.
     *
     * @param destinationLocation the destination location
     */
    public void setDestinationLocation(String destinationLocation) {
        this.destinationLocation = destinationLocation;
    }

    /**
     * Gets alert type.
     *
     * @return the alert type
     */
    public String getAlertType() {
        return alertType;
    }

    /**
     * Sets alert type.
     *
     * @param alertType the alert type
     */
    public void setAlertType(String alertType) {
        this.alertType = alertType;
    }

    /**
     * Gets hold patron id.
     *
     * @return the hold patron id
     */
    public String getHoldPatronId() {
        return holdPatronId;
    }

    /**
     * Sets hold patron id.
     *
     * @param holdPatronId the hold patron id
     */
    public void setHoldPatronId(String holdPatronId) {
        this.holdPatronId = holdPatronId;
    }

    /**
     * Gets hold patron name.
     *
     * @return the hold patron name
     */
    public String getHoldPatronName() {
        return holdPatronName;
    }

    /**
     * Sets hold patron name.
     *
     * @param holdPatronName the hold patron name
     */
    public void setHoldPatronName(String holdPatronName) {
        this.holdPatronName = holdPatronName;
    }

    /**
     * Gets alert.
     *
     * @return the alert
     */
    public boolean getAlert() {
        return alert;
    }

    /**
     * Sets alert.
     *
     * @param alert the alert
     */
    public void setAlert(boolean alert) {
        this.alert = alert;
    }

    /**
     * Gets magnetic media.
     *
     * @return the magnetic media
     */
    public boolean getMagneticMedia() {
        return magneticMedia;
    }

    /**
     * Sets magnetic media.
     *
     * @param magneticMedia the magnetic media
     */
    public void setMagneticMedia(boolean magneticMedia) {
        this.magneticMedia = magneticMedia;
    }

    /**
     * Gets resensitize.
     *
     * @return the resensitize
     */
    public boolean getResensitize() {
        return resensitize;
    }

    /**
     * Sets resensitize.
     *
     * @param resensitize the resensitize
     */
    public void setResensitize(boolean resensitize) {
        this.resensitize = resensitize;
    }

    /**
     * Gets due date.
     *
     * @return the due date
     */
    public String getDueDate() {
        return dueDate;
    }

    /**
     * Sets due date.
     *
     * @param dueDate the due date
     */
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Gets fee type.
     *
     * @return the fee type
     */
    public String getFeeType() {
        return feeType;
    }

    /**
     * Sets fee type.
     *
     * @param feeType the fee type
     */
    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    /**
     * Gets security inhibit.
     *
     * @return the security inhibit
     */
    public String getSecurityInhibit() {
        return securityInhibit;
    }

    /**
     * Sets security inhibit.
     *
     * @param securityInhibit the security inhibit
     */
    public void setSecurityInhibit(String securityInhibit) {
        this.securityInhibit = securityInhibit;
    }

    /**
     * Gets currency type.
     *
     * @return the currency type
     */
    public String getCurrencyType() {
        return currencyType;
    }

    /**
     * Sets currency type.
     *
     * @param currencyType the currency type
     */
    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    /**
     * Gets fee amount.
     *
     * @return the fee amount
     */
    public String getFeeAmount() {
        return feeAmount;
    }

    /**
     * Sets fee amount.
     *
     * @param feeAmount the fee amount
     */
    public void setFeeAmount(String feeAmount) {
        this.feeAmount = feeAmount;
    }

    /**
     * Gets media type.
     *
     * @return the media type
     */
    public String getMediaType() {
        return mediaType;
    }

    /**
     * Sets media type.
     *
     * @param mediaType the media type
     */
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }


    /**
     * Is processed boolean.
     *
     * @return the boolean
     */
    public boolean isProcessed() {
        return processed;
    }

    /**
     * Sets processed.
     *
     * @param processed the processed
     */
    public void setProcessed(boolean processed) {
        this.processed = processed;
    }
}
