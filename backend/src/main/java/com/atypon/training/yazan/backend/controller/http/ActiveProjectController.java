package com.atypon.training.yazan.backend.controller.http;

import com.atypon.training.yazan.backend.codeExecution.DockerService;
import com.atypon.training.yazan.backend.domain.ActiveFile;
import com.atypon.training.yazan.backend.domain.ActiveProject;
import com.atypon.training.yazan.backend.repository.ActiveProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/project")
@AllArgsConstructor
public class ActiveProjectController {
    private final ActiveProjectRepository activeProjectRepository;
    private final DockerService dockerService;

    @GetMapping("/all")
    public List<ActiveProject> getAll(Principal principal) {
        return activeProjectRepository.findAllByOwnerId(principal.getName());
    }

    @GetMapping("/{id}")
    public ActiveProject getById(@PathVariable String id, Principal principal) {
        var optProj = activeProjectRepository.findById(id);
        if (optProj.isEmpty()) {
            return null;
        }

        var proj = optProj.get();

        if (proj.getOwnerId().equals(principal.getName())) {
            return proj;
        }

        return null;
    }

    @PostMapping
    public ActiveProject create(@RequestBody ActiveProject activeProject, Principal principal) {
        activeProject.setOwnerId(principal.getName());
        return activeProjectRepository.save(activeProject);
    }

    @PutMapping
    public ActiveProject update(@RequestBody ActiveProject activeProject, Principal principal) {
        activeProject.setOwnerId(principal.getName());
        return activeProjectRepository.save(activeProject);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id, Principal principal) {
        var optProj = activeProjectRepository.findById(id);
        if (optProj.isPresent()) {
            var proj = optProj.get();
            if (proj.getOwnerId().equals(principal.getName())) {
                activeProjectRepository.deleteById(id);
            }
        }
    }

    public record RunInfo(String mainFilePath, String input) { }
    @PostMapping("/{id}/run")
    public String runProject(@PathVariable String id, @RequestBody RunInfo runInfo, Principal principal) {
        var project = getById(id, principal);
        if (project == null) {
            return null;
        }
        var files = project.getFiles();
        files.add(ActiveFile.builder()
                .parentPath(".")
                .fileName("input.txt")
                .sourceCode(runInfo.input)
                .build());
        return dockerService.runPythonCode(files, "./input.txt", runInfo.mainFilePath);
    }
}
