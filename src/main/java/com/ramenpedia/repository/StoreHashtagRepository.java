package com.ramenpedia.repository;

import com.ramenpedia.entity.StoreHashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Set;

public interface StoreHashtagRepository extends JpaRepository<StoreHashtag, Long>, JpaSpecificationExecutor<StoreHashtag> {
}

