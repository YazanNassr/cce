package com.atypon.training.yazan.cccbackend.service;

import com.atypon.training.yazan.cccbackend.model.stable.StableFile;

public interface StableFileService {
    StableFile save(String content);
    StableFile findById(String id);
    StableFile findByContent(String content);
    void deleteById(String id);
}
