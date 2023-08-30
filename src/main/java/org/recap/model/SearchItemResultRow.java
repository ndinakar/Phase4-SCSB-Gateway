package org.recap.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.recap.model.search.AbstractSearchItemResultRow;

/**
 * Created by rajesh on 18-Jul-16.
 */
@Data
@EqualsAndHashCode(of = {"chronologyAndEnum"})
@Schema(name="SearchItemResultRow", description="Model for Displaying Item Result")
public class SearchItemResultRow extends AbstractSearchItemResultRow implements Comparable<SearchItemResultRow> {

    @Schema(name= "itemId", description= "Item Id",maxLength = 8)
    private Integer itemId;
    @Schema(name= "owningInstitutionItemId", description= "Owning Institution Item Id",maxLength = 9)
    private String owningInstitutionItemId;
    @Schema(name= "owningInstitutionHoldingsId", description= "Owning Institution Holdings Id",maxLength = 10)
    private String owningInstitutionHoldingsId;

    @Override
    public int compareTo(SearchItemResultRow searchItemResultRow) {
        if (null != this.getChronologyAndEnum() && null != searchItemResultRow && null != searchItemResultRow.getChronologyAndEnum()) {
            return this.getChronologyAndEnum().compareTo(searchItemResultRow.getChronologyAndEnum());
        }
        return 0;
    }
}
