package org.recap.model;

import lombok.Data;

import java.util.List;

/**
 * Created by sudhishk on 15/12/16.
 */
@Data
public class ItemRefileRequest  {
    private List<String> itemBarcodes;
    private List<Integer> requestIds;
}
