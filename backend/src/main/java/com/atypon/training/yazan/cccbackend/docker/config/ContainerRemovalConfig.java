package com.atypon.training.yazan.cccbackend.docker.config;

import lombok.Builder;

@Builder
public record ContainerRemovalConfig(
        String containerId,
        boolean force,
        boolean deleteVolumes) {
}
