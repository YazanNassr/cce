package com.atypon.training.yazan.cccbackend.service;

import com.atypon.training.yazan.cccbackend.model.active.ActiveFile;
import com.atypon.training.yazan.cccbackend.model.active.ActiveProject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class VersionControlServiceTest {
    @Autowired
    private VersionControlService versionControlService;

    private ActiveFile exampleFile;
    private ActiveProject exampleProject;

    @BeforeEach
    public void resetDatabase(@Autowired MongoTemplate mongoTemplate) {
        mongoTemplate.dropCollection(ActiveProject.class);
    }

    @BeforeEach
    public void initExampleObjects() {
        exampleFile = ActiveFile.builder()
                .parentPath("parent-path")
                .fileName("file-name.ext")
                .sourceCode("source-code")
                .build();

        exampleProject = ActiveProject.builder()
                .name("Awesome project")
                .files(List.of(exampleFile))
                .build();
    }

    @Test
    public void testCreateMetadata() {
        var project = new ActiveProject();

        var savedProject = versionControlService.createNewProject(project);
        var metadataId = savedProject.getMetadataId();
        var metadata = versionControlService.getVersionControlMetadata(metadataId);

        assertNotNull(savedProject);
        assertNotNull(metadata);
        assertEquals(1, metadata.getProjectIds().size());
        assertEquals(project.getId(), metadata.getProjectIds().getFirst());
        assertEquals(0, metadata.getRelations().size());
    }


    @Test
    public void testCreateProject() {
        var projectToSave = exampleProject;

        var savedProject = versionControlService
                .createNewProject(projectToSave);

        assertNotNull(savedProject);
        assertEquals(projectToSave.getName(), savedProject.getName());
        assertEquals(projectToSave.getFiles(), savedProject.getFiles());
    }

    @Test
    public void testCommitActiveProject() {
        var projectToSave = exampleProject;
        var savedProject = versionControlService.createNewProject(projectToSave);

        var commitedProject = versionControlService.commitActiveProject(savedProject);

        assertNotNull(commitedProject);
    }

}
