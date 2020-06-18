package org.recap.model.transfer;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by sheiks on 12/07/17.
 */
@Setter
@Getter
public class HoldingTransferResponse {
    private String message;
    private HoldingsTransferRequest holdingsTransferRequest;

    public HoldingTransferResponse() {
    }

    public HoldingTransferResponse(String message, HoldingsTransferRequest holdingsTransferRequest) {
        this.message = message;
        this.holdingsTransferRequest = holdingsTransferRequest;
    }
}
