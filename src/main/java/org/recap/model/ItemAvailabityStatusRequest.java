package org.recap.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by akulak on 3/3/17.
 */
@Getter
@Setter
public class ItemAvailabityStatusRequest {
    private List<String> Barcodes;
}
