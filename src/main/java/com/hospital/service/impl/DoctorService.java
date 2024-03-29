package com.hospital.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hospital.model.domain.Doctor;
import com.hospital.model.repository.DoctorRepository;
import com.hospital.service.IDoctorService;

@Service
public class DoctorService implements IDoctorService {

	@Autowired
	private DoctorRepository doctorRepository;

	@Override
	public List<Doctor> getAll() throws Exception {
		return doctorRepository.findAll();
	}

	@Override
	public Page<Doctor> getAll(Pageable pageable) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Doctor saveOrUpdate(Doctor entity) throws Exception {
		return doctorRepository.save(entity);
	}

	@Override
	public Optional<Doctor> getOne(Long id) throws Exception {
		return doctorRepository.findById(id);
	}

	@Override
	@Transactional
	public void deleteById(Long id) throws Exception {
		doctorRepository.deleteById(id);

	}



}
