package com.ramenpedia.repository;

import com.ramenpedia.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface StoreRepository extends JpaRepository<Store, Long>, JpaSpecificationExecutor<Store> {
    @Query("SELECT s FROM Store s ORDER BY s.id LIMIT 1")
    Store findOneForTest();
}

