package com.hospital.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital.model.domain.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	
}
