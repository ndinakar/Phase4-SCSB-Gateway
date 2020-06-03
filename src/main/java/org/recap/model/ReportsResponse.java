package org.recap.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajeshbabuk on 13/1/17.
 */
@Getter
@Setter
public class ReportsResponse {
    private long accessionPrivatePulCount;
    private long accessionPrivateCulCount;
    private long accessionPrivateNyplCount;
    private long accessionSharedPulCount;
    private long accessionSharedCulCount;
    private long accessionSharedNyplCount;
    private long accessionOpenPulCount;
    private long accessionOpenCulCount;
    private long accessionOpenNyplCount;

    private long deaccessionPrivatePulCount;
    private long deaccessionPrivateCulCount;
    private long deaccessionPrivateNyplCount;
    private long deaccessionSharedPulCount;
    private long deaccessionSharedCulCount;
    private long deaccessionSharedNyplCount;
    private long deaccessionOpenPulCount;
    private long deaccessionOpenCulCount;
    private long deaccessionOpenNyplCount;

    private long openPulCgdCount;
    private long openCulCgdCount;
    private long openNyplCgdCount;
    private long sharedPulCgdCount;
    private long sharedCulCgdCount;
    private long sharedNyplCgdCount;
    private long privatePulCgdCount;
    private long privateCulCgdCount;
    private long privateNyplCgdCount;

    private String totalRecordsCount = "0";
    private Integer totalPageCount = 0;
    private String message;
    private List<DeaccessionItemResultsRow> deaccessionItemResultsRows = new ArrayList<>();

    //IncompleteRecordsReport
    private String incompleteTotalRecordsCount = "0";
    private Integer incompleteTotalPageCount = 0;
    private Integer incompletePageNumber = 0;
    private Integer incompletePageSize = 10;
    private List<IncompleteReportResultsRow> incompleteReportResultsRows = new ArrayList<>();


    /**
     * Gets deaccession item results rows.
     *
     * @return the deaccession item results rows
     */
    public List<DeaccessionItemResultsRow> getDeaccessionItemResultsRows() {
        return deaccessionItemResultsRows;
    }

    /**
     * Sets deaccession item results rows.
     *
     * @param deaccessionItemResultsRows the deaccession item results rows
     */
    public void setDeaccessionItemResultsRows(List<DeaccessionItemResultsRow> deaccessionItemResultsRows) {
        this.deaccessionItemResultsRows = deaccessionItemResultsRows;
    }

    /**
     * Gets incomplete report results rows.
     *
     * @return the incomplete report results rows
     */
    public List<IncompleteReportResultsRow> getIncompleteReportResultsRows() {
        return incompleteReportResultsRows;
    }

    /**
     * Sets incomplete report results rows.
     *
     * @param incompleteReportResultsRows the incomplete report results rows
     */
    public void setIncompleteReportResultsRows(List<IncompleteReportResultsRow> incompleteReportResultsRows) {
        this.incompleteReportResultsRows = incompleteReportResultsRows;
    }
 }
