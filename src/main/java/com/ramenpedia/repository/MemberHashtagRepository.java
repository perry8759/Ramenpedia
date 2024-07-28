package com.ramenpedia.repository;

import com.ramenpedia.entity.MemberHashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface MemberHashtagRepository extends JpaRepository<MemberHashtag, Long> {

    @Modifying
    @Transactional
    @Query(value = "delete from member_hashtag mh left join hashtag h on(mh.fk_hashtag_if = h.id) where mh.fk_member_id = :memberId and h.type != :type",
            nativeQuery = true)
    void deleteAllByMemberIdAndNotType(@Param("memberId") Long memberId, @Param("type") String type);

    @Modifying
    @Transactional
    @Query(value = "delete from member_hashtag mh left join hashtag h on(mh.fk_hashtag_if = h.id) where mh.fk_member_id = :memberId and h.type = :type",
            nativeQuery = true)
    void deleteAllByMemberIdAndType(@Param("memberId") Long memberId, @Param("type") String type);
}
