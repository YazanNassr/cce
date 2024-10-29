package com.atypon.training.yazan.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class ActiveProject {
    @Id
    private String id;
    private String name;
    private String ownerId;
    private String metadataId;
    @Builder.Default
    private List<ActiveFile> files = new ArrayList<>();
}
