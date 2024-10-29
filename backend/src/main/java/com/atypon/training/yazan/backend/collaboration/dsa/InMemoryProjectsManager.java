package com.atypon.training.yazan.backend.collaboration.dsa;

import com.atypon.training.yazan.backend.domain.ActiveProject;
import com.atypon.training.yazan.backend.repository.ActiveProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@AllArgsConstructor
public class InMemoryProjectsManager {
    private final ActiveProjectRepository activeProjectRepository;
    private final Map<String, InMemoryProject> projects
            = new ConcurrentHashMap<>();

    public InMemoryProject getInMemoryProject(String projectId) {
        addProject(projectId);
        return projects.get(projectId);
    }

    public void addProject(String projectId) {
        if (projects.containsKey(projectId)) {
            return;
        }

        var project = activeProjectRepository.findById(projectId);
        if (project.isPresent()) {
            var crrProject = project.get();
            projects.put(crrProject.getId(), new InMemoryProject(crrProject));
        }
    }

    public void removeProject(String projectId) {
        projects.remove(projectId);
    }
}
