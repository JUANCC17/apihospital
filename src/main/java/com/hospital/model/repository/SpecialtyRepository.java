package com.hospital.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital.model.domain.Specialty;

@Repository
public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {

	
}
