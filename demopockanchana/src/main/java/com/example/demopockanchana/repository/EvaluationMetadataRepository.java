package com.example.demopockanchana.repository;

import com.example.demopockanchana.domain.EvaluationMetadata;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluationMetadataRepository extends JpaRepository<EvaluationMetadata,Long> {

    EvaluationMetadata findByTypeAndId(String type, long id);
    EvaluationMetadata findById(long id);
}
