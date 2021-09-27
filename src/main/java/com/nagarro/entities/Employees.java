package com.nagarro.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Employees {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int employeeId;
	@NotBlank(message="Employee Code cannot be blank !!")
	private String employeeCode;
	@Column(length = 100)
	@NotBlank(message="Employee Name cannot be blank !!")
	@Size(min=0 , max=50 , message = "Employee Name must be less than 100 Characters!!")
	private String employeeName;
	@Column(length = 500)
	@NotBlank(message="Employee Location cannot be blank !!")
	@Size(min=0 , max=500 , message = "Employee Location must be less than 500 Characters!!")
	private String employeeLocation;
	@Column(length = 100)
	@NotBlank(message="Email cannot be blank !!")
	@Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",message= "Invalid Email!!")
	@Size(min=0 , max=100 , message = "Email must be less than 100 Characters!!")
	private String employeeEmail;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotBlank(message="Date cannot be blank !!")
	@Pattern(regexp = "^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4}$",message= "Enter Date in Format DD/MM/YYYY !!")
	private String dateOfBirth;
	
	
	public Employees(int employeeId, String employeeCode, String employeeName, String employeeLocation,
			String employeeEmail, String dateOfBirth) {
		super();
		this.employeeId = employeeId;
		this.employeeCode = employeeCode;
		this.employeeName = employeeName;
		this.employeeLocation = employeeLocation;
		this.employeeEmail = employeeEmail;
		this.dateOfBirth = dateOfBirth;
	}

	public Employees() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeLocation() {
		return employeeLocation;
	}

	public void setEmployeeLocation(String employeeLocation) {
		this.employeeLocation = employeeLocation;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Override
	public String toString() {
		return "Employees [employeeId=" + employeeId + ", employeeCode=" + employeeCode + ", employeeName="
				+ employeeName + ", employeeLocation=" + employeeLocation + ", employeeEmail=" + employeeEmail
				+ ", dateOfBirth=" + dateOfBirth + "]";
	}
	
	
}
