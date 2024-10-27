package com.atypon.training.yazan.cccbackend.repository.stable;

import com.atypon.training.yazan.cccbackend.model.stable.StableFile;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StableFileRepository extends CrudRepository<StableFile, String> {
    @Update("{ '$inc' :  { 'referencesCount' : 1 } }")
    int findAndIncrementReferencesCountById(String fileId);

    @Update("{ '$inc' :  { 'referencesCount' : -1 } }")
    int findAndDecrementReferencesCountById(String fileId);

    Optional<StableFile> findByContent(String content);
}
