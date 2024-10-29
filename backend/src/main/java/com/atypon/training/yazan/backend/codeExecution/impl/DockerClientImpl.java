package com.atypon.training.yazan.backend.codeExecution.impl;

import com.atypon.training.yazan.backend.codeExecution.DockerClient;
import com.atypon.training.yazan.backend.codeExecution.configClasses.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Component
@AllArgsConstructor
public class DockerClientImpl implements DockerClient {
    private WebClient webClient;

    @Override
    public String createContainer(ContainerCreationConfig config) {
        return webClient.post()
                .uri("/containers/create")
                .bodyValue(Map.of(
                        "Image", config.imageName()+":"+config.imageTag(),
                        "WorkingDir", config.workingDir(),
                        "Cmd", config.command(),
                        "HostConfig", Map.of( "AutoRemove", config.autoRemove() )
                ))
                .retrieve()
                .bodyToMono(Map.class)
                .map(res -> (String) res.get("Id"))
                .block();
    }

    @Override
    public void startContainer(ContainerStartupConfig config) {
        webClient.post()
                .uri(String.format("/containers/%s/start", config.containerId()))
                .bodyValue(Map.of())
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    @Override
    public String createExec(ContainerExecCreationConfig config) {
        return webClient.post()
                .uri(String.format("/containers/%s/exec", config.containerId()))
                .bodyValue(Map.of(
                        "Cmd", config.command(),
                        "AttachStdout", config.attachStdout(),
                        "AttachStderr", config.attachStderr()))
                .retrieve()
                .bodyToMono(Map.class)
                .map(res -> (String) res.get("Id"))
                .block();
    }

    @Override
    public String startExec(ContainerExecStartupConfig config) {
        return webClient.post()
                .uri(String.format("/exec/%s/start", config.execId()))
                .bodyValue(Map.of())
                .retrieve()
                .bodyToMono(String.class)
                .map(l -> l.substring(8))
                .block();
    }

    @Override
    public void removeContainer(ContainerRemovalConfig config) {
        webClient.delete().uri(
                        String.format("/containers/%s?v=%s&force=%s",
                                config.containerId(),
                                config.deleteVolumes(),
                                config.force()))
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    @Override
    public void killContainer(ContainerKillingConfig config) {
        webClient.post()
                .uri(String.format("/containers/%s/kill", config.containerId()))
                .bodyValue(Map.of(
                        "signal", config.signal()
                ))
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
