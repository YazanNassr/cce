package com.atypon.training.yazan.backend.codeExecution.impl;

import com.atypon.training.yazan.backend.codeExecution.DockerClient;
import com.atypon.training.yazan.backend.codeExecution.DockerService;
import com.atypon.training.yazan.backend.codeExecution.configClasses.directive.ContainerConfigsPythonCode;
import com.atypon.training.yazan.backend.domain.ActiveFile;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DockerServiceImpl implements DockerService {
    private DockerClient dockerClient;

    @Override
    public String runPythonCode(List<ActiveFile> files, String inputFile, String mainFile) {
        var pythonContainerConfigs = new ContainerConfigsPythonCode();

        String containerId = dockerClient.createContainer(pythonContainerConfigs.creationConfig());
        dockerClient.startContainer(pythonContainerConfigs.startupConfig(containerId));

        files.forEach(file -> {
                    String execId = dockerClient.createExec(
                            pythonContainerConfigs.execCreationConfigCreateFile(containerId, file));

                    dockerClient.startExec(
                            pythonContainerConfigs.execStartupConfig(execId));
                });

        String execId = dockerClient.createExec(
                pythonContainerConfigs.execCreationConfigRunCode(containerId, mainFile, inputFile));
        String res = dockerClient.startExec(pythonContainerConfigs.execStartupConfig(execId));
        dockerClient.killContainer(pythonContainerConfigs.killingConfig(containerId));

        return res;
    }
}
