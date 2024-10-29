package com.atypon.training.yazan.backend.codeExecution.configClasses.directive;


import com.atypon.training.yazan.backend.codeExecution.configClasses.*;
import com.atypon.training.yazan.backend.domain.ActiveFile;

import java.util.List;

public class ContainerConfigsPythonCode implements ContainerConfigs {
    private final String timeToSleep = "10";

    public ContainerCreationConfig creationConfig() {
        return ContainerCreationConfig.builder()
                .imageName("python")
                .imageTag("3")
                .autoRemove(true)
                .workingDir("/usr/src/app/")
                .command(List.of("sleep", timeToSleep))
                .build();
    }

    @Override
    public ContainerStartupConfig startupConfig(String containerId) {
        return ContainerStartupConfig.builder()
                .containerId(containerId)
                .build();
    }

    @Override
    public ContainerExecCreationConfig execCreationConfigCreateFile(
            String containerId, ActiveFile file) {

        String parentDirs = file.getParentPath() == null ? "." : file.getParentPath();

        return execCreationConfig(containerId,
                List.of("bash", "-c",
                        String.format("mkdir -p %s && echo -e \"%s\" > %s",
                                parentDirs, file.getSourceCode(), file.getFilePath())
                )
        );
    }

    @Override
    public ContainerExecCreationConfig execCreationConfigRunCode(
            String containerId, String mainFile, String inputFile) {

        return execCreationConfig(containerId,
                List.of("bash", "-c", String.format("python %s < %s", mainFile, inputFile))
        );
    }

    @Override
    public ContainerExecStartupConfig execStartupConfig(String execId) {
        return ContainerExecStartupConfig.builder()
                .execId(execId)
                .build();
    }

    @Override
    public ContainerKillingConfig killingConfig(String containerId) {
        return ContainerKillingConfig.builder()
                .containerId(containerId)
                .signal(ContainerKillingConfig.SIGNAL.SIGKILL)
                .build();
    }

    @Override
    public ContainerRemovalConfig removalConfig(String containerId) {
        return ContainerRemovalConfig.builder()
                .containerId(containerId)
                .deleteVolumes(true)
                .force(true)
                .build();
    }

    private ContainerExecCreationConfig execCreationConfig(String containerId, List<String> command) {
        return ContainerExecCreationConfig.builder()
                .containerId(containerId)
                .attachStdout(true)
                .attachStderr(true)
                .command(command)
                .build();
    }
}
