package org.recap.model.transfer;

import lombok.Data;


/**
 * Created by sheiks on 13/07/17.
 */
@Data
public class ItemTransferRequest {
    private ItemSource source;
    private ItemDestination destination;
}
