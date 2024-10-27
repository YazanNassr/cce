package com.atypon.training.yazan.cccbackend.model.stable;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.HashIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class StableFile {
    @Id
    private String id;
    @HashIndexed
    private String content;
    @Builder.Default
    private Integer referencesCount = 1;
}
