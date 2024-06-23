package com.ramenpedia.dao;

import com.ramenpedia.entity.Store;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class StoreDaoTest {

    @Autowired
    private StoreDao storeDao;

    @Test
    public void testGetAllStore() {
        // Given
        String storeName = "麵屋";
        List<Long> hashtagList = new ArrayList<>();
        hashtagList.add(1L);
        int page = 0;
        int size = 10;

        // When
        Page<Store> stores = storeDao.getAllStore(storeName, hashtagList, page, size);

        // Then
        assertNotNull(stores);
    }
}