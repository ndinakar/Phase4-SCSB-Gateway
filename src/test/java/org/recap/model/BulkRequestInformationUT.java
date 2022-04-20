package org.recap.model;

import org.junit.jupiter.api.Test;
import org.recap.BaseTestCase;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Created by Anithav on 16/06/20.
 */

public class BulkRequestInformationUT  extends BaseTestCase {

    @Test
    public void bulkRequestInformation() {
        BulkRequestInformation bulkRequestInformation = new BulkRequestInformation();
        bulkRequestInformation.setPatronBarcode("123");
        bulkRequestInformation.setRequestingInstitution("PUL");
        assertNotNull(bulkRequestInformation.getPatronBarcode());
        assertNotNull(bulkRequestInformation.getRequestingInstitution());
    }
}
