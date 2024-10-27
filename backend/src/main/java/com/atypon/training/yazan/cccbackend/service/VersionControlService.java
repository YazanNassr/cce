package com.atypon.training.yazan.cccbackend.service;

import com.atypon.training.yazan.cccbackend.model.active.ActiveProject;
import com.atypon.training.yazan.cccbackend.model.stable.StableProject;
import com.atypon.training.yazan.cccbackend.model.versionControl.VersionControlMetadata;

public interface VersionControlService {
    ActiveProject createNewProject(ActiveProject activeProject);
    StableProject commitActiveProject(ActiveProject project);
    ActiveProject forkStableProject(StableProject project);
    ActiveProject mergeStableProjects(String projectId1, String projectId2);
    VersionControlMetadata getVersionControlMetadata(String id);
}
