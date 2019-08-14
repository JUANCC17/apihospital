package com.hospital.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.model.domain.Patient;
import com.hospital.service.IPatientService;

@RestController
@RequestMapping("/patients")
public class PatientController {

	@Autowired
	private IPatientService patientService;

	
	@GetMapping
	public ResponseEntity< List<Patient>> getAllPatients(){
		List<Patient> patients = new ArrayList<>();
		try {
			patients = patientService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<List<Patient>>(patients, HttpStatus.OK);
	}
	





}
