package com.atypon.training.yazan.backend.codeExecution.configClasses;

import lombok.Builder;

@Builder
public record ContainerRemovalConfig(
        String containerId,
        boolean force,
        boolean deleteVolumes) {
}
