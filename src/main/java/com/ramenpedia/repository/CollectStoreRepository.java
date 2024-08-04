package com.ramenpedia.repository;

import com.ramenpedia.entity.CollectStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CollectStoreRepository extends JpaRepository<CollectStore, Long>, JpaSpecificationExecutor<CollectStore> {

    List<CollectStore> findByMemberId(Long memberId);
    CollectStore findByMemberIdAndStoreId(Long memberId, Long storeId);
}

