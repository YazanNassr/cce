package com.atypon.training.yazan.backend.collaboration.dsa;

import com.atypon.training.yazan.backend.collaboration.dsa.rope.Rope;
import lombok.Data;

@Data
public class InMemoryFile {
    private final Rope text;
    private Integer version = 0;

    public InMemoryFile(Rope text) {
        this.text = text;
    }
}
