package com.atypon.training.yazan.cccbackend.service;

import com.atypon.training.yazan.cccbackend.model.stable.StableFile;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class StableFileServiceTest {
    @Autowired
    private StableFileService stableFileService;

    @AfterEach
    public void resetCollection(@Autowired MongoTemplate mongoTemplate) {
        mongoTemplate.dropCollection(StableFile.class);
    }

    @Test
    public void testSingleSave() {
        String fileContents = "source-code";

        var file = stableFileService.save(fileContents);

        assertNotNull(file);
        assertEquals(fileContents, file.getContent());
        assertEquals(1, file.getReferencesCount());
    }

    @Test
    public void testMultipleSave() {
        String fileContents = "source-code";

        var file1 = stableFileService.save(fileContents);
        var file2 = stableFileService.save(fileContents);
        var file3 = stableFileService.save(fileContents);

        assertEquals(file1.getId(), file2.getId());
        assertEquals(file1.getId(), file3.getId());
        assertEquals(3, file3.getReferencesCount());
    }

    @Test
    public void testSingleDelete() {
        String fileContents = "source-code";
        String fileId;

        var file = stableFileService.save(fileContents);
        fileId = file.getId();

        stableFileService.deleteById(fileId);
        file = stableFileService.findById(fileId);

        assertNull(file);
    }

    @Test
    public void testMultipleDelete() {
        String fileContents = "source-code";
        String fileId;

        var file1 = stableFileService.save(fileContents);
        var file2 = stableFileService.save(fileContents);
        var file3 = stableFileService.save(fileContents);
        fileId = file1.getId();

        stableFileService.deleteById(fileId);

        var fileAfterDelete = stableFileService.findById(fileId);

        assertEquals(2, fileAfterDelete.getReferencesCount());
    }
}
