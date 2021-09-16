package org.recap.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.recap.model.search.AbstractSearchResultRow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajeshbabuk on 11/7/16.
 */
@Getter
@Setter
@ApiModel(value="SearchResultRow", description="Model for Displaying Search Result")
public class SearchResultRow extends AbstractSearchResultRow {

    @ApiModelProperty(name= "searchItemResultRows", value= "Item Results",position = 16)
    private List<SearchItemResultRow> searchItemResultRows = new ArrayList<>();
    @ApiModelProperty(name= "owningInstitutionBibId", value= "Owning Institution Bib Id",position = 18)
    private String owningInstitutionBibId;
    @ApiModelProperty(name= "owningInstitutionHoldingsId", value= "Owning Institution Holdings Id",position = 19)
    private String owningInstitutionHoldingsId;
    @ApiModelProperty(name= "owningInstitutionItemId", value= "Owning Institution Item Id",position = 20)
    private String owningInstitutionItemId;

    private Integer requestPosition;
    private Integer patronBarcode;
    private String requestingInstitution;
    private String deliveryLocation;
    private String requestType;
    private String requestNotes;
    private String matchingIdentifier;
}