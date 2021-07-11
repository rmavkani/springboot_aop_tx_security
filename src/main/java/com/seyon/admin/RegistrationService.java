package com.seyon.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
	
	private final RegistrationRepository registrationRepository;	
	
	@Autowired
	public RegistrationService(RegistrationRepository registrationRepository) {		
		this.registrationRepository = registrationRepository;
	}


	public List<Patient> getPatient() {
		
		return registrationRepository.findAll();
	}
	
	public void savePatient(Patient patient) {
		registrationRepository.save(patient);
	}

}
