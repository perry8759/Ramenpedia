package com.ramenpedia.repository;

import com.ramenpedia.entity.Hashtag;
import com.ramenpedia.enumerate.HashtagType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HashtagRepository extends JpaRepository<Hashtag, Long>, JpaSpecificationExecutor<Hashtag> {
    List<Hashtag> findAllByType(HashtagType type);

    @Query(value = "select h.* from member_hashtag mh left join hashtag h on(mh.fk_hashtag_if = h.id) where mh.fk_member_id = :memberId and h.type = :type",
            nativeQuery = true)
    List<Hashtag> findByMemberIdAndType(@Param("memberId") Long memberId, @Param("type") String type);

    @Query(value = "select h.* from member_hashtag mh left join hashtag h on(mh.fk_hashtag_if = h.id) where mh.fk_member_id = :memberId and h.type != :type",
            nativeQuery = true)
    List<Hashtag> findByMemberIdAndNotType(@Param("memberId") Long memberId, @Param("type") String type);
}

