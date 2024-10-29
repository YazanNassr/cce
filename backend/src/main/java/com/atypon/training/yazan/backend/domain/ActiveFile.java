package com.atypon.training.yazan.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActiveFile {
   @Builder.Default
    private String parentPath = ".";
    private String fileName;
    private String sourceCode;

    public String getFilePath() {
        if (fileName == null) {
            return null;
        }
        return getParentPath() + "/" + fileName;
    }
}
