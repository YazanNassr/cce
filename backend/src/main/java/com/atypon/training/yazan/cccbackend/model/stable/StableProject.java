package com.atypon.training.yazan.cccbackend.model.stable;

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
public class StableProject {
    @Id
    private String id;
    private String name;
    private String metadataId;
    @Builder.Default
    private List<StableFileMetadata> files = new ArrayList<>();
}
