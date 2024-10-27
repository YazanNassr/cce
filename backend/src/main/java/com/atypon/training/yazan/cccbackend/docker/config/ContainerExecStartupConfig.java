package com.atypon.training.yazan.cccbackend.docker.config;

import lombok.Builder;

@Builder
public record ContainerExecStartupConfig(String execId) {

}
