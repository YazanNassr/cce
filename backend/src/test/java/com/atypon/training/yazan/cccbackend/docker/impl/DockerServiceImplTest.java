package com.atypon.training.yazan.cccbackend.docker.impl;

import com.atypon.training.yazan.cccbackend.docker.DockerService;
import com.atypon.training.yazan.cccbackend.model.active.ActiveFile;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
public class DockerServiceImplTest {
    @Autowired
    private DockerService dockerService;

    @Test
    void runSimpleProgram() {
        ActiveFile mainFile = ActiveFile.builder()
                .sourceCode("print(f'Hello, {input()}!')")
                .fileName("main.py").build();
        ActiveFile inputFile = ActiveFile.builder()
                .sourceCode("Yazan")
                .fileName("input.txt").build();

        String res = dockerService.runPythonCode(
                List.of(mainFile, inputFile),
                inputFile.getFilePath(),
                mainFile.getFilePath()
        ).trim();

        log.info(res);

        assertEquals("Hello, Yazan!", res);
    }


    @Test
    void runProgramMultipleFiles() {
        ActiveFile utilFile = ActiveFile.builder()
                .sourceCode("def f(name): print(f'Hello, {name}!')")
                .fileName("util.py").build();

        ActiveFile mainFile = ActiveFile.builder()
                .sourceCode("from util import *;f(input())")
                .fileName("main.py").build();

        ActiveFile inputFile = ActiveFile.builder()
                .sourceCode("Yazan")
                .fileName("input.txt").build();

        String res = dockerService.runPythonCode(
                List.of(mainFile, utilFile, inputFile),
                inputFile.getFilePath(),
                mainFile.getFilePath()
        ).trim();

        log.info(res);

        assertEquals("Hello, Yazan!", res);
    }
}
