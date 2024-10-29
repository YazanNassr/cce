package com.atypon.training.yazan.backend.codeExecution.configClasses;

import lombok.Builder;

@Builder
public record ContainerKillingConfig(
        String containerId,
        SIGNAL signal) {

    public enum SIGNAL {
        SIGKILL
    }
}
