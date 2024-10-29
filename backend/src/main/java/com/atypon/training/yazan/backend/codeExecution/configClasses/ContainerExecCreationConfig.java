package com.atypon.training.yazan.backend.codeExecution.configClasses;

import lombok.Builder;

import java.util.List;

@Builder
public record ContainerExecCreationConfig(
        String containerId,
        boolean attachStdout,
        boolean attachStderr,
        List<String> command) {
}
