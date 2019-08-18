package com.hospital.api;

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

import com.hospital.exception.ModeloNotFoundException;
import com.hospital.model.domain.Specialty;
import com.hospital.service.ISpecialtyService;

@RestController
@RequestMapping("/specialties")
public class SpecialtyController {

	@Autowired
	private ISpecialtyService specialtyService;

	@GetMapping
	public ResponseEntity<List<Specialty>> getAllSpecialties() {
		List<Specialty> specialties = new ArrayList<>();

		try {
			specialties = specialtyService.getAll();
		} catch (Exception e) {
			throw new ModeloNotFoundException("ID: ");
		}

		return new ResponseEntity<List<Specialty>>(specialties, HttpStatus.OK);

	}

	@GetMapping(value = "/{specialtyId}")
	public ResponseEntity<Specialty> getSpecialtyById(@PathVariable(value = "specialtyId") Long specialtyId) {
		Optional<Specialty> specialty;
		try {
			specialty = specialtyService.getOne(specialtyId);
			if (!specialty.isPresent()) {
				throw new ModeloNotFoundException("ID: " + specialtyId);
			}
		} catch (Exception e) {
			throw new ModeloNotFoundException("ID: " + specialtyId);
		}

		return new ResponseEntity<Specialty>(specialty.get(), HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<Specialty> saveSpecialty(@Valid @RequestBody Specialty specialty) {
		Specialty newSpecialty = new Specialty();
		try {
			newSpecialty = specialtyService.saveOrUpdate(specialty);
		} catch (Exception e) {
			throw new ModeloNotFoundException("ID: ");
		}

		return new ResponseEntity<Specialty>(newSpecialty, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<Specialty> updateSpecialty(@Valid @RequestBody Specialty specialty) {
		try {
			specialtyService.saveOrUpdate(specialty);
		} catch (Exception e) {
			throw new ModeloNotFoundException("ID: ");
		}

		return new ResponseEntity<Specialty>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{specialtyId}")
	public ResponseEntity<Specialty> deleteSpecialty(@PathVariable(value = "specialtyId") Long specialtyId) {
		Optional<Specialty> specialty;
		try {
			specialty = specialtyService.getOne(specialtyId);

			if (!specialty.isPresent()) {
				throw new ModeloNotFoundException("ID: " + specialtyId);
			} else {
				specialtyService.deleteById(specialtyId);
			}

		} catch (Exception e) {
			throw new ModeloNotFoundException("ID: " + specialtyId);
		}

		return new ResponseEntity<Specialty>(HttpStatus.OK);
	}
}
