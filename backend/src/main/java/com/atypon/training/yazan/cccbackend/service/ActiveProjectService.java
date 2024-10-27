package com.atypon.training.yazan.cccbackend.service;

import com.atypon.training.yazan.cccbackend.model.active.ActiveProject;

import java.util.List;

public interface ActiveProjectService {
    ActiveProject save(ActiveProject activeProject);
    ActiveProject findById(String projectId);
    ActiveProject update(ActiveProject activeProject);
    void delete(String projectId);
    List<ActiveProject> findAll();
}
