package com.androidthings.androidthings.Domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConditionRepository extends JpaRepository<Condition, Long> {
    Condition findBySelected(boolean selected);
}
