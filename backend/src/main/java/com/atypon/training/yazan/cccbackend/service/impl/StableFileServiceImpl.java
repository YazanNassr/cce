package com.atypon.training.yazan.cccbackend.service.impl;

import com.atypon.training.yazan.cccbackend.model.stable.StableFile;
import com.atypon.training.yazan.cccbackend.repository.stable.StableFileRepository;
import com.atypon.training.yazan.cccbackend.service.StableFileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StableFileServiceImpl implements StableFileService {
    private final StableFileRepository fileRepository;

    @Override
    public StableFile save(String content) {
        StableFile storedFile = findByContent(content);

        if (storedFile == null) {
            return fileRepository.save(StableFile.builder()
                    .content(content)
                    .build());
        }

        fileRepository.findAndIncrementReferencesCountById(storedFile.getId());

        return findById(storedFile.getId());
    }

    @Override
    public StableFile findById(String id) {
        return fileRepository.findById(id)
                .orElse(null);
    }

    @Override
    public StableFile findByContent(String content) {
        return fileRepository.findByContent(content)
                .orElse(null);
    }

    @Override
    public void deleteById(String id) {
        StableFile file = findById(id);

        if (file == null) {
            return;
        }

        if (file.getReferencesCount() > 1) {
            fileRepository.findAndDecrementReferencesCountById(id);
            return;
        }

        fileRepository.deleteById(id);
    }
}
