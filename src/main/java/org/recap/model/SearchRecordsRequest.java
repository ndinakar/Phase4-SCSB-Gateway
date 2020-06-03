package org.recap.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.recap.ReCAPConstants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajeshbabuk on 6/7/16.
 */
@Data
@ApiModel(value="SearchRecordsRequest", description="Model for showing user details")
public class SearchRecordsRequest implements Serializable {


    @ApiModelProperty(name= "fieldValue", value= "Search Value",  position = 0)
    private String fieldValue = "";

    @ApiModelProperty(name ="fieldName", value= "Select a field name",position = 1)
    private String fieldName;

    @ApiModelProperty(name= "owningInstitutions", value= "Publications Owning Instutions", position = 2, allowableValues="PUL, NYPL, CUL")
    private List<String> owningInstitutions = null;

    @ApiModelProperty(name= "collectionGroupDesignations", value= "Collection Group Designations",position = 3, allowableValues="Shared, Open, Private")
    private List<String> collectionGroupDesignations = null;

    @ApiModelProperty(name= "availability", value= "Availability of books in ReCAP",position = 4, allowableValues = "Available, NotAvailable")
    private List<String> availability = null;

    @ApiModelProperty(name= "materialTypes", value= "Material Types",position = 5, allowableValues = "Serial, Monograph, Other")
    private List<String> materialTypes = null;

    @ApiModelProperty(name= "useRestrictions", value= "Book Use Restrictions",position = 6, allowableValues = "NoRestrictions, InLibraryUse, SupervisedUse")
    private List<String> useRestrictions = null;

    @ApiModelProperty(name= "isDeleted", value= "Is Deleted",position = 7)
    private boolean isDeleted = false;

    @ApiModelProperty(name= "catalogingStatus", value= "Cataloging Status",position = 8, allowableValues = "Complete, Incomplete")
    private String catalogingStatus;

    @ApiModelProperty(name= "pageNumber", value= "Current Page Number",position = 9)
    private Integer pageNumber = 0;

    @ApiModelProperty(name= "pageSize", value= "Total records to show is page", position = 10)
    private Integer pageSize = 10;

    /**
     * Instantiates a new Search records request.
     */
    public SearchRecordsRequest() {
        this.setFieldName("");
        this.setFieldValue("");
        this.setDeleted(false);
        this.setCatalogingStatus(ReCAPConstants.COMPLETE_STATUS);

        this.getOwningInstitutions().add("NYPL");
        this.getOwningInstitutions().add("CUL");
        this.getOwningInstitutions().add("PUL");

        this.getCollectionGroupDesignations().add("Shared");
        this.getCollectionGroupDesignations().add("Private");
        this.getCollectionGroupDesignations().add("Open");

        this.getAvailability().add("Available");
        this.getAvailability().add("NotAvailable");

        this.getMaterialTypes().add("Monograph");
        this.getMaterialTypes().add("Serial");
        this.getMaterialTypes().add("Other");

        this.getUseRestrictions().add("NoRestrictions");
        this.getUseRestrictions().add("InLibraryUse");
        this.getUseRestrictions().add("SupervisedUse");

        this.setPageNumber(0);
        this.setPageSize(10);
    }
}
