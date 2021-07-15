package com.seyon.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "seyon/api/registration")
public class RegistrationController {

	private final RegistrationService registrationService;
	
	@Autowired	
	public RegistrationController(RegistrationService registrationService) {		
		this.registrationService = registrationService;
	}

	@GetMapping("/patients")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PATIENT')")
	public List<Patient> getPatients() {
		return registrationService.getPatients();
	}

	//seyon/user?id=101
	@GetMapping("/patient/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PATIENT')")
	public Patient getPatients(@RequestParam String id, @PathVariable("patient") String patient) {
		return registrationService.getPatient(id);
	}
	
	@PostMapping("/patient/save")
	@PreAuthorize("hasAuthority('patient:write')")
	public void savePatient(@RequestBody Patient patient) {
		registrationService.savePatient(patient);
	}

	@PutMapping("/patient/update/{id}")
	@PreAuthorize("hasAuthority('patient:write')")
	public void savePatient(@RequestParam String id, @RequestBody Patient patient) {
		registrationService.updatePatient(patient);
	}

	@DeleteMapping("/patient/delete/{id}")
	@PreAuthorize("hasAuthority('patient:write')")
	public void savePatient(@RequestParam String id ) {
		registrationService.deletePatient(id);
	}
	

}
