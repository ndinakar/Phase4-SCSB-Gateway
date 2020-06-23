package org.recap.model.transfer;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by sheiks on 12/07/17.
 */
@Getter
@Setter
public class TransferResponse {
    private String message;
    private List<HoldingTransferResponse> holdingTransferResponses;
    private List<ItemTransferResponse> itemTransferResponses;
}
