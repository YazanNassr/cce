package com.atypon.training.yazan.cccbackend.websockets;

import com.atypon.training.yazan.cccbackend.dsa.AdditionModification;
import com.atypon.training.yazan.cccbackend.dsa.DeletionModification;
import com.atypon.training.yazan.cccbackend.dsa.InMemoryProjectsManager;
import com.atypon.training.yazan.cccbackend.dsa.Modification;
import com.atypon.training.yazan.cccbackend.model.active.ActiveProject;
import com.atypon.training.yazan.cccbackend.service.ActiveProjectService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;


@Slf4j
@Controller
@AllArgsConstructor
@CrossOrigin("*")
public class WSController {
    private final InMemoryProjectsManager projectsManager;
    private final ActiveProjectService activeProjectService;

    public static record SubscriptionPayload(String projectId, String filePath) { }

    @MessageMapping("/echo")
    @SendTo("/topic/test")
    public String echo(@Payload String s) {
        return s;
    }

    @MessageMapping("/project/subscribe")
    public String subscribe(@Payload SubscriptionPayload payload) {
        // projectsManager.addProject(activeProjectService.findById(projectId));

        System.out.println(payload.projectId());
        System.out.println(payload.filePath());
        // System.out.println(activeProjectService.findById(projectId));

        return ""; //projectsManager.getInMemoryProject(projectId).getFileContents(filePath);
    }

    @MessageMapping("/project/{projectId}/{filePath}/add")
    @SendTo("/topic/project/{projectId}/{filePath}")
    public Modification sendMessage(
            @Payload AdditionModification modification,
            @PathVariable String projectId,
            @PathVariable String filePath) {

        projectsManager.getInMemoryProject(projectId).queueModification(filePath, modification);
        return projectsManager.getInMemoryProject(projectId).applyModification(filePath);
    }

    @MessageMapping("/project/{projectId}/{filePath}/rem")
    @SendTo("/topic/project/{projectId}/{filePath}")
    public Modification sendMessage(
            @Payload DeletionModification modification,
            @PathVariable String projectId,
            @PathVariable String filePath) {

        projectsManager.getInMemoryProject(projectId).queueModification(filePath, modification);
        return projectsManager.getInMemoryProject(projectId).applyModification(filePath);
    }

    @MessageMapping("/project/save/{projectId}")
    public void saveProject(@PathVariable String projectId) {
        ActiveProject activeProject =
                projectsManager.getInMemoryProject(projectId).updateActiveProject();
        activeProjectService.save(activeProject);
    }
}
