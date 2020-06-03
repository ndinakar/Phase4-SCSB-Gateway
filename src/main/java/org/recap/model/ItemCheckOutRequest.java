package org.recap.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by sudhishk on 15/12/16.
 */
@Getter
@Setter
public class ItemCheckOutRequest extends AbstractRequestItem {
    private String patronIdentifier;
}
