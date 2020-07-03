package org.recap.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.recap.model.search.AbstractSearchItemResultRow;

/**
 * Created by rajesh on 18-Jul-16.
 */
@Data
@EqualsAndHashCode(of = {"chronologyAndEnum"})
@ApiModel(value="SearchItemResultRow", description="Model for Displaying Item Result")
public class SearchItemResultRow extends AbstractSearchItemResultRow implements Comparable<SearchItemResultRow> {

    @ApiModelProperty(name= "itemId", value= "Item Id",position = 8)
    private Integer itemId;
    @ApiModelProperty(name= "owningInstitutionItemId", value= "Owning Institution Item Id",position = 9)
    private String owningInstitutionItemId;
    @ApiModelProperty(name= "owningInstitutionHoldingsId", value= "Owning Institution Holdings Id",position = 10)
    private String owningInstitutionHoldingsId;

    @Override
    public int compareTo(SearchItemResultRow searchItemResultRow) {
        if (null != this.getChronologyAndEnum() && null != searchItemResultRow && null != searchItemResultRow.getChronologyAndEnum()) {
            return this.getChronologyAndEnum().compareTo(searchItemResultRow.getChronologyAndEnum());
        }
        return 0;
    }
}
