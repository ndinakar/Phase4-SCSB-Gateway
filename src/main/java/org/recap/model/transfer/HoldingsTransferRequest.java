package org.recap.model.transfer;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by sheiks on 12/07/17.
 */
@Getter
@Setter
public class HoldingsTransferRequest {
    private Source source;
    private Destination destination;
}
