package com.atypon.training.yazan.cccbackend.model.active;

import lombok.*;

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
