package com.atypon.training.yazan.backend.codeExecution;

import com.atypon.training.yazan.backend.domain.ActiveFile;

import java.util.List;

public interface DockerService {
    String runPythonCode(List<ActiveFile> files, String inputFile, String mainFile);
}
