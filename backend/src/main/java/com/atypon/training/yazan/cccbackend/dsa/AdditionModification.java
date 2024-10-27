package com.atypon.training.yazan.cccbackend.dsa;

import com.atypon.training.yazan.cccbackend.dsa.rope.Rope;

public record AdditionModification(Integer index, String value) implements Modification {
    @Override
    public void apply(Rope rope) {
        rope.replace(index, index, value);
    }
}
