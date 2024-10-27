package com.atypon.training.yazan.cccbackend.docker;

import com.atypon.training.yazan.cccbackend.docker.config.*;

public interface DockerClient {
    public String createContainer(ContainerCreationConfig config);
    public void startContainer(ContainerStartupConfig config);

    public String createExec(ContainerExecCreationConfig config);
    public String startExec(ContainerExecStartupConfig config);

    public void removeContainer(ContainerRemovalConfig config);
    public void killContainer(ContainerKillingConfig config);
}
