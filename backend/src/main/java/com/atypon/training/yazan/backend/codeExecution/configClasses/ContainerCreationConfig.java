package com.atypon.training.yazan.backend.codeExecution.configClasses;

import lombok.Builder;

import java.util.List;

@Builder
public record ContainerCreationConfig(
        String imageName,
        String imageTag,
        String workingDir,
        boolean autoRemove,
        List<String> command) {
}
