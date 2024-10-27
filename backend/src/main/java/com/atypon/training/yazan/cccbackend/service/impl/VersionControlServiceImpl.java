package com.atypon.training.yazan.cccbackend.service.impl;

import com.atypon.training.yazan.cccbackend.dsa.needlemanWunsch.NeedlemanWunsch;
import com.atypon.training.yazan.cccbackend.model.active.*;
import com.atypon.training.yazan.cccbackend.model.stable.*;
import com.atypon.training.yazan.cccbackend.model.versionControl.*;
import com.atypon.training.yazan.cccbackend.repository.versionControl.VersionControlMetadataRepository;
import com.atypon.training.yazan.cccbackend.service.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@AllArgsConstructor
public class VersionControlServiceImpl implements VersionControlService {
    private final StableFileService stableFileService;
    private final StableProjectService stableProjectService;
    private final ActiveProjectService activeProjectService;
    private final VersionControlMetadataRepository versionControlMetadataRepository;

    @Override
    @Transactional
    public ActiveProject createNewProject(ActiveProject activeProject) {
        var project = activeProjectService.save(activeProject);
        var metadata = createMetadata(project);
        project.setMetadataId(metadata.getId());
        activeProjectService.save(project);

        return project;
    }

    private VersionControlMetadata createMetadata(ActiveProject project) {
        return versionControlMetadataRepository.save(
                VersionControlMetadata.builder()
                        .projectIds(List.of(project.getId()))
                        .build());
    }

    @Override
    @Transactional
    public StableProject commitActiveProject(ActiveProject activeProject) {
        var stableProject = StableProject.builder()
                .name(activeProject.getName())
                .metadataId(activeProject.getMetadataId())
                .build();

        for (var file : activeProject.getFiles()) {
            var stableFile = stableFileService.save(file.getSourceCode());

            var filesMetadata = StableFileMetadata.builder()
                    .stableFileId(stableFile.getId())
                    .parentPath(file.getParentPath())
                    .fileName(file.getFileName())
                    .build();

            stableProject.getFiles().add(filesMetadata);
        }

        var savedProject = stableProjectService.save(stableProject);

        var metadata = versionControlMetadataRepository.findById(savedProject.getMetadataId())
                .orElseThrow(RuntimeException::new);
        metadata.setProjectIds(metadata.getProjectIds().stream()
                .map(id -> (id.equals(activeProject.getId())) ?
                        stableProject.getId() : id).toList());
        metadata.setRelations(
                metadata.getRelations().stream()
                        .peek(r -> {
                            if (r.getChildId().equals(activeProject.getId())) {
                                r.setChildId(stableProject.getId());
                            }
                        }).toList());
        versionControlMetadataRepository.save(metadata);

        return stableProject;
    }

    @Override
    @Transactional
    public ActiveProject forkStableProject(StableProject stableProject) {
        var activeFiles = stableProject.getFiles().stream()
                .map(file -> ActiveFile.builder()
                        .sourceCode(stableFileService.findById(file.getStableFileId()).getContent())
                        .fileName(file.getFileName())
                        .parentPath(file.getParentPath())
                        .build())
                .toList();

        ActiveProject project = ActiveProject.builder()
                .name("untitled-project")
                .metadataId(stableProject.getMetadataId())
                .files(activeFiles)
                .build();

        var savedProject = activeProjectService.save(project);

        VersionControlMetadata versionControlMetadata
                = versionControlMetadataRepository.findById(project.getMetadataId())
                    .orElse(null);

        if (versionControlMetadata == null) {
            return null;
        }

        versionControlMetadata.getProjectIds().add(savedProject.getId());

        versionControlMetadata.getRelations().add(
                CommitRelation.builder()
                        .parentId(stableProject.getId())
                        .childId(savedProject.getId())
                        .build()
        );

        versionControlMetadataRepository.save(versionControlMetadata);

        return project;
    }

    @Override
    public ActiveProject mergeStableProjects(String projectId1, String projectId2) {
        StableProject stableProject1 = stableProjectService.findById(projectId1);
        StableProject stableProject2 = stableProjectService.findById(projectId2);

        NeedlemanWunsch mergingAlgorithm = new NeedlemanWunsch();

        Map<String, ActiveFile> files = new HashMap<>();

        stableProject1.getFiles().stream()
                .map(fileMetadata -> ActiveFile.builder()
                        .parentPath(fileMetadata.getParentPath())
                        .fileName(fileMetadata.getFileName())
                        .sourceCode(stableFileService.findById(fileMetadata.getStableFileId()).getContent())
                        .build())
                .forEach(file -> {
                    if (files.containsKey(file.getFileName())) {

                        String mergedSourceCode = mergingAlgorithm.mergeStrings(
                                files.get(file.getFilePath()).getSourceCode(),
                                file.getSourceCode());

                        file.setSourceCode(mergedSourceCode);

                        files.put(file.getFilePath(), file);
                    } else {
                        files.put(file.getFilePath(), file);
                    }
                });

        return activeProjectService.save(ActiveProject.builder()
                .name("untitled-project")
                .files(files.values().stream().toList())
                .metadataId(stableProject1.getMetadataId())
                .build());
    }

    @Override
    public VersionControlMetadata getVersionControlMetadata(String id) {
        return versionControlMetadataRepository.findById(id)
                .orElse(null);
    }
}
