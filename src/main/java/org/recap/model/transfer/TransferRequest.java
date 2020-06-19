package org.recap.model.transfer;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by sheiks on 12/07/17.
 */
@Getter
@Setter
public class TransferRequest {
    private String institution;
    private List<HoldingsTransferRequest> holdingTransfers;
    private List<ItemTransferRequest> itemTransfers;
}
