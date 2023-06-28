package com.rewards.repository;

import com.rewards.entity.PurchaseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RewardsRepository extends JpaRepository<PurchaseInfo, Integer> {

    @Query(value = "SELECT * FROM purchase_info WHERE customer_id = :customer_id", nativeQuery = true)
    List<PurchaseInfo> findById(@Param("customer_id") int customer_id);
}
