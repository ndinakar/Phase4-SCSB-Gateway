package org.recap.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * Created by sudhishk on 15/12/16.
 */
@ApiModel(value = "ItemCheckInRequest", description = "Model for Requesting Checkin request")
@Data
public class ItemCheckInRequest extends AbstractRequestItem {
    @ApiModelProperty(name = "patronIdentifier", position = 2, required = true)
    private String patronIdentifier;
}
