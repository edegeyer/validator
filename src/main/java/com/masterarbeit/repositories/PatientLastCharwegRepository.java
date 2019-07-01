package com.masterarbeit.repositories;

import com.masterarbeit.entities.lastcharweg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientLastCharwegRepository extends JpaRepository<lastcharweg, Integer> {
}
