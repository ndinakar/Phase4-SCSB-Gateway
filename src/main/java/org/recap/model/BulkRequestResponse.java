package org.recap.model;

import lombok.Data;
/**
 * Created by rajeshbabuk on 10/10/17.
 */
@Data
public class BulkRequestResponse {
    private Integer bulkRequestId;
    private String screenMessage;
    private boolean success;
}
