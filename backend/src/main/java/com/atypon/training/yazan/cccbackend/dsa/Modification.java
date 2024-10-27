package com.atypon.training.yazan.cccbackend.dsa;

import com.atypon.training.yazan.cccbackend.dsa.rope.Rope;

public interface Modification {
    void apply(Rope rope);
}
