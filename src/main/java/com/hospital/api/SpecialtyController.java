package com.hospital.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
	public ResponseEntity<List<Specialty>> getAllSpecialties(){
		List<Specialty> specialties = new ArrayList<>();
		
		try {
			specialties = specialtyService.getAll();
		} catch (Exception e) {
			throw new ModeloNotFoundException("ID: ");
		}
		
		return new ResponseEntity<List<Specialty>>(specialties,HttpStatus.OK);
		
	}
}
