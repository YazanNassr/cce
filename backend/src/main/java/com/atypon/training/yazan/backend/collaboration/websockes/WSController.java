package com.atypon.training.yazan.backend.collaboration.websockes;

import com.atypon.training.yazan.backend.collaboration.dsa.InMemoryProjectsManager;
import com.atypon.training.yazan.backend.collaboration.dsa.ReplaceModification;
import com.atypon.training.yazan.backend.domain.ActiveProject;
import com.atypon.training.yazan.backend.repository.ActiveProjectRepository;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
@AllArgsConstructor
public class WSController {
    private final InMemoryProjectsManager projectsManager;
    private final ActiveProjectRepository activeProjectRepository;

    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/modify")
    public void modify(@Payload ReplaceModification modification) throws URISyntaxException {
        var proj = projectsManager.getInMemoryProject(modification.getProjectId());
        proj.queueModification(modification);

        String encodedProjectId = URLEncoder.encode(modification.getProjectId(), StandardCharsets.UTF_8);
        String encodedFilePath = URLEncoder.encode(modification.getFilePath(), StandardCharsets.UTF_8);

        String path = String.format("/topic/%s/%s", encodedProjectId, encodedFilePath);
        messagingTemplate.convertAndSend(path, proj.applyModification());
    }

    @MessageMapping("/{projectId}/save")
    public ActiveProject save(@DestinationVariable String projectId) {
        var proj = projectsManager.getInMemoryProject(projectId);
        return activeProjectRepository.save(proj.updateActiveProject());
    }
}
