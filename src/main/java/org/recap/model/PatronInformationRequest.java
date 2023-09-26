package org.recap.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Created by sudhishk on 26/12/16.
 */
@Schema(name = "PatronInformationRequest", description = "Model for Requesting Patron Information")
@Data
public class PatronInformationRequest{

    @Schema(name = "patronIdentifier", maxLength = 0, required = true)
    private String patronIdentifier = "";
    @Schema(name = "itemOwningInstitution", maxLength = 1, required = true)
    private String itemOwningInstitution="";
}
