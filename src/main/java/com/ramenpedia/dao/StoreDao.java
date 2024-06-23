package com.ramenpedia.dao;

import com.ramenpedia.entity.Store;
import com.ramenpedia.entity.StoreHashtag;
import com.ramenpedia.repository.StoreHashtagRepository;
import com.ramenpedia.repository.StoreRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class StoreDao {

    private final StoreRepository storeRepository;

    public StoreDao(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public Page<Store> getAllStore(String storeName, List<Long> hashtagList, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Specification<Store> spec = (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotEmpty(storeName)) {
                predicates.add(builder.like(root.get("name"), storeName + "%"));
            }

            if (hashtagList != null && !hashtagList.isEmpty()) {
                Join<StoreHashtag, Store> join = root.join("storeHashtags");
                Expression<Long> hashtagIdExp = join.get("hashtagId").as(Long.class);
                predicates.add(hashtagIdExp.in(hashtagList));
            }

            query.where(builder.and(predicates.toArray(new Predicate[0])));
            return query.getRestriction();
        };

        return storeRepository.findAll(spec, pageable);
    }
}