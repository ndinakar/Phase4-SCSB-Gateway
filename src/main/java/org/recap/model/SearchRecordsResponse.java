package org.recap.model;

import lombok.Getter;
import lombok.Setter;
import org.recap.model.search.SearchRecordsCommonResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajeshbabuk on 2/1/17.
 */
@Getter
@Setter
public class SearchRecordsResponse extends SearchRecordsCommonResponse {
    private List<SearchResultRow> searchResultRows = new ArrayList<>();
}
