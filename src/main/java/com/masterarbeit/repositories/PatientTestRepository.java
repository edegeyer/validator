package com.masterarbeit.repositories;

import com.masterarbeit.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientTestRepository extends JpaRepository<Patient, Integer> {
}
