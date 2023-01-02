package com.info5059.casestudy.purchaseOrder;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PurchaseOrderRepository extends CrudRepository<PurchaseOrder, Long> {
    // will return the number of rows deleted
    @Modifying
    @Transactional
    @Query("delete from PurchaseOrder where id = ?1")
    int deleteOne(Long poid);
}