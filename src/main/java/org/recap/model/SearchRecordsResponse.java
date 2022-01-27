package org.recap.model;

import lombok.Data;

import org.recap.model.search.SearchRecordsCommonResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajeshbabuk on 2/1/17.
 */
@Data
public class SearchRecordsResponse extends SearchRecordsCommonResponse {
    private List<SearchResultRow> searchResultRows = new ArrayList<>();
}
