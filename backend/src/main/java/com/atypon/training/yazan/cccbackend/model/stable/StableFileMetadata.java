package com.atypon.training.yazan.cccbackend.model.stable;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StableFileMetadata {
    private String stableFileId;
    private String parentPath;
    private String fileName;
}
