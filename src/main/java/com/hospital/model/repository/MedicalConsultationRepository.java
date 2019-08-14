package com.hospital.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.model.domain.MedicalConsultation;

public interface MedicalConsultationRepository extends JpaRepository<MedicalConsultation, Long> {

	
	
}
