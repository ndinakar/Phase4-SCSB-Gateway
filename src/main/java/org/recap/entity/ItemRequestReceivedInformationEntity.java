package org.recap.entity;

import lombok.Getter;
import lombok.Setter;
import org.recap.model.jpa.AbstractEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Dinakar N created on 14/09/22
 */

@Entity
@Table( name = "item_request_log_t",
        catalog = "")
@AttributeOverride(
        name = "id",
        column = @Column(
                name = "ID"
        )
)
@Setter
@Getter
public class ItemRequestReceivedInformationEntity extends AbstractEntity<Integer> {
    @Column(
            name = "REQUEST_INSTITUTION"
    )
    private String requestInstitution;

    @Column(
            name = "ITEM_OWNING_INSTITUTION"
    )
    private String itemOwningInstitution;

    @Column(
            name = "REQUEST_RECEIVED"
    )
    private String requestRecieved;
    @Column(
            name = "REQUESTED_ITEM_BARCODE"
    )
    private String requestedItemBarcode;

    @Column(
            name = "STATUS_ID"
    )
    private Integer statusId;
    @Column(
            name = "STATUS"
    )
    private String status;

    @Column(
            name = "DATE"
    )
    private Date date;
}
