package org.recap.model;

import lombok.Data;

/**
 * Created by sudhishk on 15/12/16.
 */
@Data
public class ItemCheckOutRequest extends AbstractRequestItem {
    private String patronIdentifier;
}
