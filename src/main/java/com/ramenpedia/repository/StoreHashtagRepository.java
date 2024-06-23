package com.ramenpedia.repository;

import com.ramenpedia.entity.Store;
import com.ramenpedia.entity.StoreHashtag;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface StoreHashtagRepository extends JpaRepository<StoreHashtag, Long>, JpaSpecificationExecutor<StoreHashtag> {
}

