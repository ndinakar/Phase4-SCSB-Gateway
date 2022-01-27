package org.recap.model;

import lombok.Data;

@Data
public abstract class BaseResponseItem extends AbstractResponseItem {

    private String transactionDate;
    private String institutionID;
    private String patronIdentifier;
    private String titleIdentifier;
    private String ISBN;
    private String LCCN;
    private String jobId;
    private String updatedDate;
    private String createdDate;

}
