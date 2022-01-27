package org.recap.model.transfer;

import lombok.Data;


/**
 * Created by sheiks on 12/07/17.
 */
@Data
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
