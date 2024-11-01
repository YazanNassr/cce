package com.atypon.training.yazan.backend.codeExecution.configClasses.directive;


import com.atypon.training.yazan.backend.codeExecution.configClasses.*;
import com.atypon.training.yazan.backend.domain.ActiveFile;

public interface ContainerConfigs {
    public ContainerCreationConfig creationConfig();
    public ContainerStartupConfig startupConfig(String containerId);

    public ContainerExecCreationConfig execCreationConfigCreateFile(
            String containerId, ActiveFile file);

    public ContainerExecCreationConfig execCreationConfigRunCode(
            String containerId, String mainFile, String inputFile);
    public ContainerExecStartupConfig execStartupConfig(String execId);

    public ContainerKillingConfig killingConfig(String containerId);
    public ContainerRemovalConfig removalConfig(String containerId);
}
