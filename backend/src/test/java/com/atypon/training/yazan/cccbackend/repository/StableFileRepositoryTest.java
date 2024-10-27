package com.atypon.training.yazan.cccbackend.repository;

import com.atypon.training.yazan.cccbackend.model.stable.StableFile;
import com.atypon.training.yazan.cccbackend.repository.stable.StableFileRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class StableFileRepositoryTest {
    @Autowired
    private StableFileRepository stableFileRepository;

    @Test
    public void testIncrementReferencesCount() {
        StableFile stableFile = StableFile.builder()
                .content("place-holder")
                .build();

        var savedFile = stableFileRepository.save(stableFile);
        stableFileRepository.findAndIncrementReferencesCountById(savedFile.getId());
        var fileAfterUpdate = stableFileRepository.findById(savedFile.getId()).get();

        assertEquals(2, fileAfterUpdate.getReferencesCount());

    }

    @Test
    public void testInitialReferencesCount() {
        StableFile stableFile = StableFile.builder()
                .content("place-holder")
                .build();

        var savedFile = stableFileRepository.save(stableFile);

        assertEquals(1, savedFile.getReferencesCount());
    }

    @Test
    public void testSaveFile() {
        StableFile stableFile = StableFile.builder()
                .content("place-holder")
                .build();

        var savedFile = stableFileRepository.save(stableFile);

        assertNotNull(savedFile);
        assertEquals(stableFile.getContent(), savedFile.getContent());
    }


}
