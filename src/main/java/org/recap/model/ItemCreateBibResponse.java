package org.recap.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by sudhishk on 16/12/16.
 */
@Getter
@Setter
public class ItemCreateBibResponse extends AbstractResponseItem {

    private String itemId;
    private String bibId;
}
