package com.hospital.api;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hospital.exception.ModeloNotFoundException;
import com.hospital.model.domain.Doctor;
import com.hospital.service.IDoctorService;

@RestController
@RequestMapping(value = "/doctors")
public class DoctorController {

	@Autowired
	private IDoctorService doctorService;

	@GetMapping
	public ResponseEntity<List<Doctor>> getAllDoctors() {
		List<Doctor> doctors = new ArrayList<>();
		try {
			doctors = doctorService.getAll();

		} catch (Exception e) {
			throw new ModeloNotFoundException("ID: ");
		}
		return new ResponseEntity<List<Doctor>>(doctors, HttpStatus.OK);
	}

	@GetMapping(value = "/{doctorId}")
	public ResponseEntity<Doctor> getDoctorById(@PathVariable(value = "doctorId") Long doctorId) {
		Optional<Doctor> doctor;
		try {
			doctor = doctorService.getOne(doctorId);
			if (!doctor.isPresent()) {
				throw new ModeloNotFoundException("ID: " + doctorId);
			}
		} catch (Exception e) {
			throw new ModeloNotFoundException("ID: " + doctorId);
		}

		return new ResponseEntity<Doctor>(doctor.get(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Doctor> saveDoctor(@Valid @RequestBody Doctor doctor) {
		Doctor newDoctor = new Doctor();
		URI location;
		try {
			newDoctor = doctorService.saveOrUpdate(doctor);
			location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newDoctor.getId())
					.toUri();
		} catch (Exception e) {
			throw new ModeloNotFoundException("ID: ");
		}

		return ResponseEntity.created(location).build();
	}

	@PutMapping
	public ResponseEntity<Doctor> updateDoctor(@Valid @RequestBody Doctor doctor) {
		try {
			doctorService.saveOrUpdate(doctor);
		} catch (Exception e) {
			throw new ModeloNotFoundException("ID: ");
		}

		return new ResponseEntity<Doctor>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{doctorId}")
	public ResponseEntity<Doctor> deleteDoctor(@PathVariable(value = "doctorId") Long doctorId) {
		Optional<Doctor> doctor;
		try {
			doctor = doctorService.getOne(doctorId);

			if (!doctor.isPresent()) {
				throw new ModeloNotFoundException("ID: " + doctorId);
			} else {
				doctorService.deleteById(doctorId);
			}

		} catch (Exception e) {
			throw new ModeloNotFoundException("ID: " + doctorId);
		}

		return new ResponseEntity<Doctor>(HttpStatus.OK);
	}

}
