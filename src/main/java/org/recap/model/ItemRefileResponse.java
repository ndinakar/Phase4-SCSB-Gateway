package org.recap.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by sudhishk on 15/12/16.
 */
@Getter
@Setter
public class ItemRefileResponse extends AbstractResponseItem {
    private String screenMessage;
    private boolean success;
}
