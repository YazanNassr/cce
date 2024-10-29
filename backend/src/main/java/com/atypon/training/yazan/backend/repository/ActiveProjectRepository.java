package com.atypon.training.yazan.backend.repository;

import com.atypon.training.yazan.backend.domain.ActiveFile;
import com.atypon.training.yazan.backend.domain.ActiveProject;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ActiveProjectRepository extends CrudRepository<ActiveProject, String> {
    List<ActiveProject> findAllByOwnerId(String ownerId);
}
