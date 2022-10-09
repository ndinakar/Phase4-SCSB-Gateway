package org.recap.repository;

import org.recap.entity.ItemRequestReceivedInformationEntity;
import org.recap.repository.jpa.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author Dinakar N created on 14/09/22
 */
@Repository
public interface ItemRequestInformationRepository extends BaseRepository<ItemRequestReceivedInformationEntity> {
    @Query(value = "select requests from ItemRequestReceivedInformationEntity requests where requests.requestInstitution = :requestInstitution")
    Page<ItemRequestReceivedInformationEntity> findByInstitution(Pageable pageable, @Param("requestInstitution") String requestInstitution);

    @Query(value = "select requests from ItemRequestReceivedInformationEntity requests where requests.status = :status")
    Page<ItemRequestReceivedInformationEntity> findByStatus(Pageable pageable, @Param("status") String status);

    @Query(value = "select requests from ItemRequestReceivedInformationEntity requests where requests.requestInstitution = :requestInstitution and requests.status = :status")
    Page<ItemRequestReceivedInformationEntity> findByInstitutionAndStatus(Pageable pageable, @Param("requestInstitution") String requestInstitution, @Param("status") String status);

    @Modifying(clearAutomatically = true)
    @Query(value = "update ItemRequestReceivedInformationEntity entity set entity.status = :status , entity.statusId = :statusId where entity.id = :id")
    void update(@Param("id") Integer id, @Param("status") String status, @Param("statusId") Integer statusId);

    @Query(value = "select requests from ItemRequestReceivedInformationEntity requests where requests.statusId = :statusId")
    Page<ItemRequestReceivedInformationEntity> findAllByStatusId(Pageable pageable, @Param("statusId") Integer statusId);

    @Query(value = "select requests from ItemRequestReceivedInformationEntity requests where requests.requestInstitution = :requestInstitution and requests.status = :status and requests.date >= :fromDate and requests.date <= :toDate")
    Page<ItemRequestReceivedInformationEntity> findByInstitutionAndStatusAndFromDateAndEndDate(Pageable pageable, @Param("requestInstitution") String institution,@Param("status") String status,@Param("fromDate") Date fromDate,@Param("toDate") Date toDate);

    @Query(value = "select requests from ItemRequestReceivedInformationEntity requests where requests.requestInstitution = :requestInstitution and requests.date >= :fromDate and requests.date <= :toDate")
    Page<ItemRequestReceivedInformationEntity> findByInstitutionAndFromDateAndEndDate(Pageable pageable, @Param("requestInstitution") String institution,@Param("fromDate") Date fromDate,@Param("toDate") Date toDate);

    @Query(value = "select requests from ItemRequestReceivedInformationEntity requests where requests.requestInstitution = :requestInstitution and requests.status = :status  and requests.date >= :fromDate and requests.date <= :toDate")
    Page<ItemRequestReceivedInformationEntity> findByStatusAndFromDateAndEndDate(Pageable pageable,@Param("status") String status,@Param("fromDate") Date fromDate,@Param("toDate") Date toDate);

    @Query(value = "select requests from ItemRequestReceivedInformationEntity requests where requests.date >= :fromDate and requests.date <= :toDate")
    Page<ItemRequestReceivedInformationEntity> findByAndFromDateAndEndDate(Pageable pageable,@Param("fromDate") Date fromDate,@Param("toDate") Date toDate);

    @Query(value = "select requests from ItemRequestReceivedInformationEntity requests where requests.statusId = :statusId  and requests.date >= :fromDate and requests.date <= :toDate")
    Page<ItemRequestReceivedInformationEntity> findByStatusIdAndFromDateAndEndDate(Pageable pageable, @Param("statusId") Integer statusId,@Param("fromDate") Date fromDate,@Param("toDate") Date toDate);
}
