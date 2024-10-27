package com.atypon.training.yazan.cccbackend.docker;

import com.atypon.training.yazan.cccbackend.model.active.ActiveFile;

import java.util.List;

public interface DockerService {
    String runPythonCode(List<ActiveFile> files, String inputFile, String mainFile);
}
