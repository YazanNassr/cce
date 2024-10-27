package com.atypon.training.yazan.cccbackend.service.impl;

import com.atypon.training.yazan.cccbackend.model.active.ActiveProject;
import com.atypon.training.yazan.cccbackend.repository.active.ActiveProjectRepository;
import com.atypon.training.yazan.cccbackend.service.ActiveProjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
@AllArgsConstructor
public class ActiveProjectServiceImpl implements ActiveProjectService {
    private final ActiveProjectRepository projectRepository;

    @Override
    public ActiveProject save(ActiveProject activeProject) {
        return projectRepository.save(activeProject);
    }

    @Override
    public ActiveProject findById(String projectId) {
        return projectRepository.findById(projectId)
                .orElse(null);
    }

    @Override
    public ActiveProject update(ActiveProject activeProject) {
        return projectRepository.save(activeProject);
    }

    @Override
    public void delete(String projectId) {
        projectRepository.deleteById(projectId);
    }

    @Override
    public List<ActiveProject> findAll() {
        List<ActiveProject> res = new LinkedList<>();
        projectRepository.findAll().forEach(res::add);
        return res;
    }
}
