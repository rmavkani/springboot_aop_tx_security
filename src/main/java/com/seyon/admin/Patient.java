package com.seyon.admin;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class Patient {
	
	@Id
	@SequenceGenerator(name="patient_sequene",	sequenceName ="patient_sequene" , allocationSize = 1)
	@GeneratedValue(strategy =GenerationType.SEQUENCE, generator = "patient_sequene")	
	private Long id;
	private String name;
	private String sex;
	private Integer age;
	private String mobile;
	private String mail; 
	private Long caseId;
	private String caseSummary; 
	private String description;	
	
	


	public Patient() {
		
	}


	public Patient(Long id, String name, String sex, Integer age, String mobile, String mail, Long caseId,
			String caseSummary, String description) {
		
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.mobile = mobile;
		this.mail = mail;
		this.caseId = caseId;
		this.caseSummary = caseSummary;
		this.description = description;
	}
	
	
	public Patient(String name, String sex, Integer age, String mobile, String mail, Long caseId, String caseSummary,
			String description) {
		
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.mobile = mobile;
		this.mail = mail;
		this.caseId = caseId;
		this.caseSummary = caseSummary;
		this.description = description;
	}


	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Long getCaseId() {
		return caseId;
	}
	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}
	public String getCaseSummary() {
		return caseSummary;
	}
	public void setCaseSummary(String caseSummary) {
		this.caseSummary = caseSummary;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public String toString() {
		return "Registration [id=" + id + ", Name=" + name + ", sex=" + sex + ", age=" + age + ", mobile=" + mobile
				+ ", mail=" + mail + ", caseId=" + caseId + ", caseSummary=" + caseSummary + ", description="
				+ description + "]";
	} 
	
	

}
