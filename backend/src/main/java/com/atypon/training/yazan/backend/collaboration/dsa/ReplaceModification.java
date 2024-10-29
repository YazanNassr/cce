package com.atypon.training.yazan.backend.collaboration.dsa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplaceModification {
    private String projectId;
    private String filePath;
    private int start;
    private int end;
    private String newVal;
}
