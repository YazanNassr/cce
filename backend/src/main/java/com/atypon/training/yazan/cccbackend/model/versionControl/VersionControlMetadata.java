package com.atypon.training.yazan.cccbackend.model.versionControl;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class VersionControlMetadata {
    @Id
    private String id;
    @Builder.Default
    private List<String> projectIds = new ArrayList<>();
    @Builder.Default
    private List<CommitRelation> relations = new ArrayList<>();
}
