package com.edhec.commonedhecapps.web.foreignstudent.command;

import java.util.Date;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


public class ForeignStudentCommand {
	
	@NotEmpty
	@Size(min=2, max=50)
	private String studentLastName;
	
	@NotEmpty
	@Size(min=2, max=50)
	private String studentFirstName;
	
	@NotEmpty
	@Email
	@Size(min=3, max=80)
	private String studentEmail;
	
	@NotEmpty
	@DecimalMin("1")
	@DecimalMax("5")
	private String currentyear;

	@NotEmpty
	@DecimalMin("1")
	@DecimalMax("5")
	private String currentyearoutof;

	@NotNull
	private String studentTitle;
	
	@NotNull
	private Date birthDate;

	@NotNull
	protected String nationality;
	
	/*@NotNull*/
	private String studentCampus;
	
	@NotNull
	private String studentLevel;
	
	/*@NotNull*/
	private String S1studentLevel;

	/*@NotNull*/
	private String S2studentLevel;

	/*@NotNull*/
	private String FullS1studentLevel;

	/*@NotNull*/
	private String FullS2studentLevel;

	/*@NotNull*/
	private String period;
		
	private String foreignStudentId;
	
	private String semesterUsed;
	
	/*@NotNull*/
	private String doubleDiploma;
	
	private String help;
	
	public String getStudentLastName() {
		return studentLastName;
	}
	public void setStudentLastName(String studentLastName) {
		this.studentLastName = studentLastName;
	}
	public String getStudentFirstName() {
		return studentFirstName;
	}
	public void setStudentFirstName(String studentFirstName) {
		this.studentFirstName = studentFirstName;
	}
	public String getStudentEmail() {
		return studentEmail;
	}
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
	public String getStudentTitle() {
		return studentTitle;
	}
	public void setStudentTitle(String studentTitle) {
		this.studentTitle = studentTitle;
	}

	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getForeignStudentId() {
		return foreignStudentId;
	}
	public void setForeignStudentId(String foreignStudentId) {
		this.foreignStudentId = foreignStudentId;
	}
	public String getStudentLevel() {
		return studentLevel;
	}
	public void setStudentLevel(String studentLevel) {
		this.studentLevel = studentLevel;
	}
	public String getSemesterUsed() {
		return semesterUsed;
	}
	public void setSemesterUsed(String semesterUsed) {
		this.semesterUsed = semesterUsed;
	}
	public String getDoubleDiploma() {
		return doubleDiploma;
	}
	public void setDoubleDiploma(String doubleDiploma) {
		this.doubleDiploma = doubleDiploma;
	}
	public String getStudentCampus() {
		return studentCampus;
	}
	public void setStudentCampus(String studentCampus) {
		this.studentCampus = studentCampus;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getHelp() {
		return help;
	}
	public void setHelp(String help) {
		this.help = help;
	}
	public String getS1studentLevel() {
		return S1studentLevel;
	}
	public void setS1studentLevel(String s1studentLevel) {
		S1studentLevel = s1studentLevel;
	}
	public String getS2studentLevel() {
		return S2studentLevel;
	}
	public void setS2studentLevel(String s2studentLevel) {
		S2studentLevel = s2studentLevel;
	}
	public String getFullS1studentLevel() {
		return FullS1studentLevel;
	}
	public void setFullS1studentLevel(String fullS1studentLevel) {
		FullS1studentLevel = fullS1studentLevel;
	}
	public String getFullS2studentLevel() {
		return FullS2studentLevel;
	}
	public void setFullS2studentLevel(String fullS2studentLevel) {
		FullS2studentLevel = fullS2studentLevel;
	}
	public String getCurrentyear() {
		return currentyear;
	}
	public void setCurrentyear(String currentyear) {
		this.currentyear = currentyear;
	}
	public String getCurrentyearoutof() {
		return currentyearoutof;
	}
	public void setCurrentyearoutof(String currentyearoutof) {
		this.currentyearoutof = currentyearoutof;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
}
