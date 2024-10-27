package com.atypon.training.yazan.cccbackend.model;

import com.atypon.training.yazan.cccbackend.model.active.ActiveFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ActiveFileTest {
    private ActiveFile activeFile;

    @BeforeEach
    void reset() {
        activeFile = ActiveFile.builder()
                .parentPath("/src/app")
                .fileName("main.py")
                .sourceCode("print('Hello, World!')")
                .build();
    }

    @Test
    void testPathMethodNormalCase() {
        assertEquals("/src/app/main.py",
                activeFile.getFilePath());
    }

    @Test
    void testPathMethodNullParent() {
        activeFile.setParentPath(null);
        assertEquals("./main.py", activeFile.getFilePath());
    }

    @Test
    void testPathMethodNullFileName() {
        activeFile.setFileName(null);
        assertNull(activeFile.getFilePath());
    }

    @Test
    void testPathMethodPathEndsWithSlash() {
        activeFile.setParentPath(".//");
        assertEquals("./main.py", activeFile.getFilePath());
    }
}
