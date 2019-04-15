package com.masterarbeit.repositories;


import com.masterarbeit.entities.fifthcharweg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Jan on 14.04.2017.
 */
@Repository
public interface PatientAnonymRepository extends JpaRepository<fifthcharweg, Integer> {
}
