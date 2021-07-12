package com.seyon.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RegistrationService {
	
	private final RegistrationRepository registrationRepository;	
	
	@Autowired
	public RegistrationService(RegistrationRepository registrationRepository) {		
		this.registrationRepository = registrationRepository;
	}


	public List<Patient> getPatient() {
		
		return registrationRepository.findAll();
	}
	/**
	* Checks for Transactions, if not available creates a new transaction
  */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED )
	public void savePatient(Patient patient) {
		registrationRepository.save(patient);
	}
	
	/**
	* Checks for Transactions, If existing available, uses the existing one OR if not available execution with out transaction
  */
	@Transactional(propagation = Propagation.SUPPORTES, isolation = Isolation.READ_COMMITTED , noRollbackForClassName = {"NullpointerException"})
	public void savePatientContacts(Patient patient) {
		registrationRepository.save(patient);
	}

	/**
	* Checks for Transactions, If existing available, uses the existing one OR if not available throws exception 
 	*/
	@Transactional(propagation = Propagation.MANDATORY, isolation = Isolation.REPEATABLE_READ, rollbackForClassName = {"NullpointerException"} )
	public void saveCaseDetails(Patient patient) {
		registrationRepository.save(patient);
	}

	/**
	* always new tranaction, if existing available, suspense it 
 	*/
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE )
	public void saveAppointments(Patient patient) {
		registrationRepository.save(patient);
	}


	/**
	* if existing transactions, throws exceptions
	 */
	@Transactional(propagation = Propagation.NEVER, isolation = Isolation.READ_UNCOMMITTED )
	public void saveUser(Patient patient) {
		registrationRepository.save(patient);
	}

	/**
	* if existing transactions, suspend it and execute in non transactionl 
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, isolation = Isolation.SERIALIZABLE )
	public void saveRubrics(Patient patient) {
		registrationRepository.save(patient);
	}

}
