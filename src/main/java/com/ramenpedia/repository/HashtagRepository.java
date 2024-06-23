package com.ramenpedia.repository;

import com.ramenpedia.entity.Hashtag;
import com.ramenpedia.enumerate.HashtagType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface HashtagRepository extends JpaRepository<Hashtag, Long>, JpaSpecificationExecutor<Hashtag> {
    List<Hashtag> findAllByType(HashtagType type);
}

