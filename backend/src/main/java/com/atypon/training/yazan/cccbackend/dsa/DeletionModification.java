package com.atypon.training.yazan.cccbackend.dsa;

import com.atypon.training.yazan.cccbackend.dsa.rope.Rope;

public record DeletionModification(Integer index, Integer length) implements Modification {
    @Override
    public void apply(Rope rope) {
        rope.replace(index, index + length, "");
    }
}
