package com.atypon.training.yazan.cccbackend.docker.config;

import lombok.Builder;

@Builder
public record ContainerKillingConfig(
        String containerId,
        ContainerKillingConfig.SIGNAL signal) {

    public enum SIGNAL {
        SIGKILL
    }
}
