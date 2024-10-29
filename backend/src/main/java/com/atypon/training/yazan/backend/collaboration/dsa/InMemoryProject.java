package com.atypon.training.yazan.backend.collaboration.dsa;

import com.atypon.training.yazan.backend.collaboration.dsa.rope.Rope;
import com.atypon.training.yazan.backend.domain.ActiveProject;
import lombok.Getter;
import lombok.Synchronized;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class InMemoryProject {
    @Getter
    private final ActiveProject activeProject;

    private final Map<String, Rope> inMemFiles = new ConcurrentHashMap<>();
    private final Queue<ReplaceModification> queue
            = new ConcurrentLinkedQueue<>();

    public InMemoryProject(ActiveProject activeProject) {
        this.activeProject = activeProject;
        init();
    }

    private void init() {
        for (var file : activeProject.getFiles()) {
            inMemFiles.put(
                    file.getFilePath(),
                    Rope.from(file.getSourceCode()));
        }
    }

    public void queueModification(ReplaceModification modification) {
        queue.add(modification);
    }

    public ReplaceModification applyModification() {
        var modification = queue.poll();

        if (modification != null) {
            String path = modification.getFilePath();

            inMemFiles.put(path,
                    inMemFiles.get(path)
                            .replace(modification.getStart(), modification.getEnd(), modification.getNewVal()));

            return modification;
        }

        return null;
    }

    public String getFileContents(String path) {
        return inMemFiles.get(path).toString();
    }

    public ActiveProject updateActiveProject() {
        activeProject.getFiles()
                .forEach(file -> file.setSourceCode(getFileContents(file.getFilePath())));
        return activeProject;
    }
}
