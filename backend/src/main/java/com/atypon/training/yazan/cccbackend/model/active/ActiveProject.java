package com.atypon.training.yazan.cccbackend.model.active;

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
public class ActiveProject {
    @Id
    private String id;
    private String name;
    @Builder.Default
    private List<ActiveFile> files = new ArrayList<>();
    private String metadataId;
}
