package org.recap.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.recap.model.search.AbstractSearchResultRow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajeshbabuk on 11/7/16.
 */
@Data
@Schema(name="SearchResultRow", description="Model for Displaying Search Result")
public class SearchResultRow extends AbstractSearchResultRow {

    @Schema(name= "searchItemResultRows", description= "Item Results",maxLength = 16)
    private List<SearchItemResultRow> searchItemResultRows = new ArrayList<>();
    @Schema(name= "owningInstitutionBibId", description= "Owning Institution Bib Id",maxLength = 18)
    private String owningInstitutionBibId;
    @Schema(name= "owningInstitutionHoldingsId", description= "Owning Institution Holdings Id",maxLength = 19)
    private String owningInstitutionHoldingsId;
    @Schema(name= "owningInstitutionItemId", description= "Owning Institution Item Id",maxLength = 20)
    private String owningInstitutionItemId;

    private Integer requestPosition;
    private Integer patronBarcode;
    private String requestingInstitution;
    private String deliveryLocation;
    private String requestType;
    private String requestNotes;
    private String matchingIdentifier;
}