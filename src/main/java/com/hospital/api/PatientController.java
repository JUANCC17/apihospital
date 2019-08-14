package com.hospital.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.exception.ModeloNotFoundException;
import com.hospital.model.domain.Patient;
import com.hospital.service.IPatientService;

@RestController
@RequestMapping("/patients")
public class PatientController {

	@Autowired
	private IPatientService patientService;

	@GetMapping
	public ResponseEntity<List<Patient>> getAllPatients() {
		List<Patient> patients = new ArrayList<>();
		try {
			patients = patientService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<List<Patient>>(patients, HttpStatus.OK);
	}

	@GetMapping(value = "/{patientId}")
	public ResponseEntity<Patient> getPatientById(@PathVariable(value = "patientId") Long patientId) {
		Optional<Patient> patient;
		try {
			patient = patientService.getOne(patientId);
			if (!patient.isPresent()) {
				throw new ModeloNotFoundException("ID: " + patientId);
			}
		} catch (Exception e) {
			throw new ModeloNotFoundException("ID: " + patientId);
		}

		return new ResponseEntity<Patient>(patient.get(), HttpStatus.OK);
	}
	
	

}
