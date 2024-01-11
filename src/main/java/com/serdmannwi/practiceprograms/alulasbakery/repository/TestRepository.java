package com.serdmannwi.practiceprograms.alulasbakery.repository;

import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<TestRecord, String> {
}
