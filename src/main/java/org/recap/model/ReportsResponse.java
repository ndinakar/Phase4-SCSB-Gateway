package org.recap.model;

import lombok.Getter;
import lombok.Setter;
import org.recap.model.reports.ReportsBase;

/**
 * Created by rajeshbabuk on 13/1/17.
 */
@Getter
@Setter
public class ReportsResponse extends ReportsBase {
    private String message;

    //IncompleteRecordsReport
    private Integer incompletePageNumber = 0;
    private Integer incompletePageSize = 10;

 }
