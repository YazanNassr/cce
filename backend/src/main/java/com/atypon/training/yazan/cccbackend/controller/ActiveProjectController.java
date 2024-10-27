package com.atypon.training.yazan.cccbackend.controller;

import com.atypon.training.yazan.cccbackend.model.active.ActiveProject;
import com.atypon.training.yazan.cccbackend.service.ActiveProjectService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController("/project")
public class ActiveProjectController {
    private final ActiveProjectService activeProjectService;

    @GetMapping("")
    public List<ActiveProject> getAllActiveProjects() {
        return List.of();
        // return activeProjectService.findAll();
    }

    @GetMapping("/{id}")
    public ActiveProject getActiveProject(@PathVariable String id) {
        return activeProjectService.findById(id);
    }

    @PutMapping("")
    public ActiveProject updateActiveProject(@RequestBody ActiveProject activeProject) {
        return activeProjectService.update(activeProject);
    }

    @PostMapping("")
    public ActiveProject createActiveProject(@RequestBody ActiveProject activeProject) {
        return  activeProjectService.save(activeProject);
    }

    @DeleteMapping("/{id}")
    public void deleteActiveProject(@PathVariable String id) {
        activeProjectService.delete(id);
    }
}
