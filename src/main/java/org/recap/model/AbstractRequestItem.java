package org.recap.model;



import lombok.Data;

import java.util.List;

/**
 * Created by sudhishk on 15/12/16.
 */
@Data
public abstract class AbstractRequestItem {
    private List<String> itemBarcodes;
    private String itemOwningInstitution="";
}
