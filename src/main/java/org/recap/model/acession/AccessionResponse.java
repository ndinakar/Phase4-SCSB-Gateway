package org.recap.model.acession;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by harikrishnanv on 16/6/17.
 */
@Getter
@Setter
public class AccessionResponse {
    private String itemBarcode;
    private String message;
}
