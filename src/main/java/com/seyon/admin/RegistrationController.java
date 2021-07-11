package com.seyon.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "seyon/patient")
public class RegistrationController {

	private final RegistrationService registrationService;
	
	@Autowired	
	public RegistrationController(RegistrationService registrationService) {		
		this.registrationService = registrationService;
	}

	@GetMapping
	public List<Patient> getPatient() {
		return registrationService.getPatient();
	}
	
	@PostMapping
	public void savePatient(@RequestBody Patient patient) {
		registrationService.savePatient(patient);
	}

}
