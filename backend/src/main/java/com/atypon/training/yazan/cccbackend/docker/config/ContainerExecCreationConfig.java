package com.atypon.training.yazan.cccbackend.docker.config;

import lombok.Builder;

import java.util.List;

@Builder
public record ContainerExecCreationConfig(
        String containerId,
        boolean attachStdout,
        boolean attachStderr,
        List<String> command) {
}
