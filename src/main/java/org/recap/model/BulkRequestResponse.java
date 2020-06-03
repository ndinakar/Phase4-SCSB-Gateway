package org.recap.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by rajeshbabuk on 10/10/17.
 */
@Getter
@Setter
public class BulkRequestResponse {
    private Integer bulkRequestId;
    private String screenMessage;
    private boolean success;
}
