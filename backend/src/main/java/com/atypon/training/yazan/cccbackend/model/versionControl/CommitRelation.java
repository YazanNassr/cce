package com.atypon.training.yazan.cccbackend.model.versionControl;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommitRelation {
    private String parentId;
    private String childId;
}
