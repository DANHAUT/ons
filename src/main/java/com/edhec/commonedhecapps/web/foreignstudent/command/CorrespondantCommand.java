package com.edhec.commonedhecapps.web.foreignstudent.command;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class CorrespondantCommand {

	@NotEmpty
	@Size(min=2, max=50)
	private String lastName;
	@NotEmpty
	@Size(min=2, max=50)
	private String firstName;
	@NotEmpty
	@Email
	@Size(min=3, max=80)
	private String email;
	@Size(max=15)
	private String codeErasmus;
	@NotEmpty
	@Size(max=200)
	private String title;
	@Size(max=20)
	private String phone;
	@NotEmpty
	@Size(max=200)
	private String address;
	@NotEmpty
	@Size(max=30)
	private String zipcode;
	@NotEmpty
	@Size(max=100)
	private String town;
	@NotEmpty
	@Size(max=60)
	private String country; 
	private String softwares;
	private String ola;
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCodeErasmus() {
		return codeErasmus;
	}
	public void setCodeErasmus(String codeErasmus) {
		this.codeErasmus = codeErasmus;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getSoftwares() {
		return softwares;
	}
	public void setSoftwares(String softwares) {
		this.softwares = softwares;
	}

	public String getOla() {
		return ola;
	}

	public void setOla(String ola) {
		this.ola = ola;
	}
}
