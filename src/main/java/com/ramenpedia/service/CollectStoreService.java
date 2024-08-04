package com.ramenpedia.service;

import com.ramenpedia.entity.CollectStore;
import com.ramenpedia.entity.Member;
import com.ramenpedia.enumerate.ResponseConstant;
import com.ramenpedia.exception.BusinessException;
import com.ramenpedia.repository.CollectStoreRepository;
import com.ramenpedia.repository.MemberRepository;
import com.ramenpedia.service.dto.StoreInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CollectStoreService {

    private final CollectStoreRepository collectStoreRepository;
    private final MemberRepository memberRepository;

    public CollectStoreService(CollectStoreRepository collectStoreRepository, MemberRepository memberRepository) {
        this.collectStoreRepository = collectStoreRepository;
        this.memberRepository = memberRepository;
    }

    public List<StoreInfo> getCollectStore(String email) {
        Member member = memberRepository.findByEmail(email);
        if (member == null) {
            log.error("Member not found, email: {}", email);
            throw new BusinessException(ResponseConstant.MEMBER_NOT_FOUND);
        }

        List<CollectStore> collectStoreList = collectStoreRepository.findByMemberId(member.getId());

        return collectStoreList.stream().map(
                collectStore -> new StoreInfo(collectStore.getStore().getId(), collectStore.getStore().getName(),
                        collectStore.getStore().getAddress(), collectStore.getStore().getScore(),
                        collectStore.getStore().getDescription())
        ).toList();
    }

    public void addCollectStore(String email, Long storeId) {
        Member member = memberRepository.findByEmail(email);
        if (member == null) {
            log.error("Member not found, email: {}", email);
            throw new BusinessException(ResponseConstant.MEMBER_NOT_FOUND);
        }

        // Check if the store is already in the collect list
        CollectStore collectStore = collectStoreRepository.findByMemberIdAndStoreId(member.getId(), storeId);
        if (collectStore != null) {
            log.error("Store already in the collect list");
            throw new BusinessException(ResponseConstant.STORE_ALREADY_IN_COLLECT_LIST);
        }

        collectStore = new CollectStore();
        collectStore.setMemberId(member.getId());
        collectStore.setStoreId(storeId);
        collectStoreRepository.save(collectStore);
    }

    public void deleteCollectStore(String email, Long storeId) {
        Member member = memberRepository.findByEmail(email);
        if (member == null) {
            log.error("Member not found, email: {}", email);
            throw new BusinessException(ResponseConstant.MEMBER_NOT_FOUND);
        }

        CollectStore collectStore = collectStoreRepository.findByMemberIdAndStoreId(member.getId(), storeId);
        collectStoreRepository.delete(collectStore);
    }
}
