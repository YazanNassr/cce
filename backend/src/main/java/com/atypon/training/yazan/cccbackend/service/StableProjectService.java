package com.atypon.training.yazan.cccbackend.service;

import com.atypon.training.yazan.cccbackend.model.stable.StableProject;

public interface StableProjectService {
    StableProject save(StableProject stableProject);
    StableProject findById(String projectId);
    void delete(String projectId);
}
