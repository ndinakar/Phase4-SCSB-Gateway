package org.recap.model;

import lombok.Data;

/**
 * Created by sudhishk on 16/12/16.
 */
@Data
public class ItemCreateBibResponse extends AbstractResponseItem {

    private String itemId;
    private String bibId;
}
