package org.recap.model;


import lombok.Getter;
import lombok.Setter;

/**
 * Created by akulak on 3/3/17.
 */
@Getter
@Setter
public class BibItemAvailabityStatusRequest {
    private String bibliographicId;
    private String institutionId;
}
