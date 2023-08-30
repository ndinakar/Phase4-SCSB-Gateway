package org.recap.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


/**
 * Created by sudhishk on 15/12/16.
 */
@Schema(name = "ItemCheckInRequest", description = "Model for Requesting Checkin request")
@Data
public class ItemCheckInRequest extends AbstractRequestItem {
    @Schema(name = "patronIdentifier", maxLength = 2, required = true)
    private String patronIdentifier;
}
