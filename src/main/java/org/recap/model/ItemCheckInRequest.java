package org.recap.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by sudhishk on 15/12/16.
 */
@ApiModel(value = "ItemCheckInRequest", description = "Model for Requesting Checkin request")
@Getter
@Setter
public class ItemCheckInRequest extends AbstractRequestItem {
    @ApiModelProperty(name = "patronIdentifier", position = 2, required = true)
    private String patronIdentifier;
}
