package org.recap.model;

import lombok.Data;

import java.util.Date;

/**
 * Created by chenchulakshmig on 12/10/16.
 */
@Data
public class ScsbRequest {
    private String reportType;
    private String transmissionType;
    private Date dateFrom;
    private Date dateTo;
}
