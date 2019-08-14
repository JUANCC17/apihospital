package com.hospital.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital.model.domain.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>{


}
