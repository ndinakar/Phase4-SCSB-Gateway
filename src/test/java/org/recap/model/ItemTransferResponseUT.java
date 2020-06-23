package org.recap.model;

import org.junit.Test;
import org.recap.BaseTestCase;
import org.recap.model.transfer.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Anitha on 13/6/20.
 */

public class ItemTransferResponseUT extends BaseTestCase {

    @Test
    public void itemTransferResponse(){
        ItemTransferResponse itemTransferResponse = new ItemTransferResponse();
        itemTransferResponse.setMessage("Success");
        ItemSource itemSource = new ItemSource();
        itemSource.setOwningInstitutionItemId("PUL");
        ItemDestination itemDestination = new ItemDestination();
        itemDestination.setOwningInstitutionItemId("CUL");
        ItemTransferRequest itemTransferRequest = new ItemTransferRequest();
        itemTransferRequest.setSource(itemSource);
        itemTransferRequest.setDestination(itemDestination);
        itemTransferResponse.setItemTransferRequest(itemTransferRequest);
        assertNotNull(itemTransferResponse.getMessage());
        assertNotNull(itemTransferResponse.getItemTransferRequest());

    }
}
