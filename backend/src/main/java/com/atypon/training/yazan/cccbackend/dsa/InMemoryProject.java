package com.atypon.training.yazan.cccbackend.dsa;

import com.atypon.training.yazan.cccbackend.dsa.rope.Rope;
import com.atypon.training.yazan.cccbackend.model.active.ActiveProject;
import lombok.Getter;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class InMemoryProject {
    @Getter
    private final ActiveProject activeProject;

    private final Map<String, Rope> inMemFiles = new HashMap<>();
    private final Queue<Modification> queue
            = new ConcurrentLinkedQueue<>();

    public InMemoryProject(ActiveProject activeProject) {
        this.activeProject = activeProject;
        init();
    }

    private void init() {
        for (var file : activeProject.getFiles()) {
            inMemFiles.put(file.getFilePath(), Rope.from(file.getSourceCode()));
        }
    }

    public void queueModification(String path, Modification modification) {
        queue.add(modification);
    }

    public Modification applyModification(String path) {
        var modification = queue.poll();

        if (modification != null) {
            modification.apply(inMemFiles.get(path));
        }

        return modification;
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
