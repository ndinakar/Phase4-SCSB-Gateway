package org.recap.model;

import org.junit.jupiter.api.Test;
import org.recap.BaseTestCase;
import org.recap.ScsbCommonConstants;
import org.recap.model.transfer.Destination;
import org.recap.model.transfer.HoldingTransferResponse;
import org.recap.model.transfer.HoldingsTransferRequest;
import org.recap.model.transfer.Source;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HoldingTransferResponseUT extends BaseTestCase {

    @Test
    public void holdingTransferResponse(){
        HoldingTransferResponse holdingTransferResponse = new HoldingTransferResponse(ScsbCommonConstants.SUCCESS,getHoldingsTransferRequest());
        assertEquals(ScsbCommonConstants.SUCCESS,holdingTransferResponse.getMessage());
        assertNotNull(holdingTransferResponse.getHoldingsTransferRequest());
     }

    private HoldingsTransferRequest getHoldingsTransferRequest() {
        HoldingsTransferRequest holdingsTransferRequest = new HoldingsTransferRequest();
        holdingsTransferRequest.setSource(getSource());
        holdingsTransferRequest.setDestination(getDestination());
        return holdingsTransferRequest;
    }

    private Destination getDestination() {
        Destination destination=new Destination();
        destination.setOwningInstitutionBibId("1");
        destination.setOwningInstitutionHoldingsId("2");
        return destination;
    }

    private Source getSource() {
        Source source=new Source();
        source.setOwningInstitutionHoldingsId("1");
        source.setOwningInstitutionBibId("2");
        return source;
    }

}
