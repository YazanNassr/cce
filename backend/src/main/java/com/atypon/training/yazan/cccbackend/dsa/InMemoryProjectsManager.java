package com.atypon.training.yazan.cccbackend.dsa;

import com.atypon.training.yazan.cccbackend.model.active.ActiveProject;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryProjectsManager {

    private final Map<String, InMemoryProject> projects
            = new ConcurrentHashMap<>();

    public InMemoryProject getInMemoryProject(String projectId) {
        return projects.get(projectId);
    }

    public void addProject(ActiveProject project) {
        if (projects.containsKey(project.getId())) {
            return;
        }

        projects.put(project.getId(), new InMemoryProject(project));
    }

    public void removeProject(String projectId) {
        projects.remove(projectId);
    }
}
