package org.recap.model.transfer;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by sheiks on 13/07/17.
 */
@Getter
@Setter
public class ItemTransferRequest {
    private ItemSource source;
    private ItemDestination destination;
}
