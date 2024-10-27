package com.atypon.training.yazan.cccbackend.service.impl;

import com.atypon.training.yazan.cccbackend.model.stable.StableProject;
import com.atypon.training.yazan.cccbackend.repository.stable.StableProjectRepository;
import com.atypon.training.yazan.cccbackend.service.StableProjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StableProjectServiceImpl implements StableProjectService {
    private final StableProjectRepository projectRepository;

    @Override
    public StableProject save(StableProject stableProject) {
        return projectRepository.save(stableProject);
    }

    @Override
    public StableProject findById(String projectId) {
        return projectRepository.findById(projectId)
                .orElse(null);
    }

    @Override
    public void delete(String projectId) {
        projectRepository.deleteById(projectId);
    }
}
