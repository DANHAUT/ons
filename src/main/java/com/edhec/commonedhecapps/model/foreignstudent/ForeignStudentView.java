package com.edhec.commonedhecapps.model.foreignstudent;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import java.sql.Date;


/**
 * The persistent class for the SCO_FOREIGN_STUDENT_V1 database table.
 * 
 */
@Entity
@Table(schema="SCHOLARSHIP", name="SCO_FOREIGN_STUDENT_INS_V1")
@NamedQueries({
  @NamedQuery(name = "ForeignStudentView.findByUniversityIdAndStatus", query = "select o from ForeignStudentView o where o.idUniversity = :idUniversity and o.crtIdStatus = :crtIdStatus and o.shyId = :shyId and o.scoYear = :scoYear and o.dateHistory is null order by o.lastName asc"),
  @NamedQuery(name = "ForeignStudentView.existEmail", query = "select o from ForeignStudentView o where trim(lower(o.email)) = trim(lower(:email)) and o.dateHistory is null and o.crtIdStatus <>'A' "),
  @NamedQuery(name = "ForeignStudentView.findByUniversityId", query = "select o from ForeignStudentView o where o.idUniversity = :idUniversity and o.shyId = :shyId and o.scoYear = :scoYear and o.dateHistory is null order by o.lastName asc")
})
public class ForeignStudentView implements Serializable {
	private static final long serialVersionUID = 1L;

	private String address;

	private String birthcountry;

	private Date birthdate;

	@Column(name="BIRTHDATE_FR")
	private String birthdateFr;

	private String birthplace;

	private String campus;

	private String city;

	private String country;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Column(name="CRT_ID_STATUS")
	private String crtIdStatus;

	@Column(name="DATE_CREATED")
	private Date dateCreated;

	@Column(name="DATE_MODIFIED")
	private Date dateModified;

	private String description;

	private String email;
	
	@Column(name="PERIODE_CODE")
	private String periodeCode;

	@Column(name="MODULE_CODE")
	private String moduleCode;

	@Column(name="MODULE_LIB")
	private String moduleLib;

	private String fall;

	@Column(name="FIRST_NAME")
	private String firstName;

	@Transient
	private String firstNameUnquoted;

	@Column(name="FS_ID")
	private String fsId;

	private String gender;

	@Id
	private String id;

	@Column(name="ID_UNIVERSITY")
	private String idUniversity;

	private String language;

	@Column(name="LAST_NAME")
	private String lastName;

	@Transient
	private String lastNameUnquoted;

	@Column(name="MODIFIED_BY")
	private String modifiedBy;

	private String nationality;

	@Column(name="SHY_ID")
	private String shyId;

	@Column(name="SCO_YEAR")
	private BigDecimal scoYear;

	private String spring;

	private String status;

	@Column(name="STATUS_DATE")
	private Date statusDate;

	private String studylevel;

	private String studylevelS1;

	private String studylevelS2;

	private String telephone;

	private String university;

	@Column(name="UNIVERSITY_INTERNAL_REFERENCE")
	private String universityInternalReference;

	@Column(name="DATE_HISTORY")
	private Date dateHistory;
	
	private String winter;
	
	@Column(name="DBLEDIPLOM")
	private String doubleDiploma;
	
	private String help;

	@Transient
	private String helpUnquoted;

	private String currentyear;

	private String currentyearoutof;

	@Column(name="URL_ID")
	private String urlid;

	@Column(name="CURRENT_PROGRAM")
	private String currentProgram;

	@Column(name="EDHEC_PROGRAM")
	private String edhecProgram;

	@Column(name="INSURANCE")
	private String insurance;

	public ForeignStudentView() {
    }

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBirthcountry() {
		return this.birthcountry;
	}

	public void setBirthcountry(String birthcountry) {
		this.birthcountry = birthcountry;
	}

	public Date getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getBirthdateFr() {
		return birthdateFr;
	}

	public void setBirthdateFr(String birthdateFr) {
		this.birthdateFr = birthdateFr;
	}

	public String getBirthplace() {
		return this.birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	public String getCampus() {
		return this.campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCrtIdStatus() {
		return this.crtIdStatus;
	}

	public void setCrtIdStatus(String crtIdStatus) {
		this.crtIdStatus = crtIdStatus;
	}

	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateModified() {
		return this.dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFall() {
		return this.fall;
	}

	public void setFall(String fall) {
		this.fall = fall;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFsId() {
		return this.fsId;
	}

	public void setFsId(String fsId) {
		this.fsId = fsId;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdUniversity() {
		return this.idUniversity;
	}

	public void setIdUniversity(String idUniversity) {
		this.idUniversity = idUniversity;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getNationality() {
		return this.nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getShyId() {
		return this.shyId;
	}

	public void setShyId(String shyId) {
		this.shyId = shyId;
	}

	public String getSpring() {
		return this.spring;
	}

	public void setSpring(String spring) {
		this.spring = spring;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStatusDate() {
		return this.statusDate;
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}

	public String getStudylevel() {
		return this.studylevel;
	}

	public void setStudylevel(String studylevel) {
		this.studylevel = studylevel;
	}
	
	public String getStudylevelS1() {
		return studylevelS1;
	}

	public void setStudylevelS1(String studylevelS1) {
		this.studylevelS1 = studylevelS1;
	}

	public String getStudylevelS2() {
		return studylevelS2;
	}

	public void setStudylevelS2(String studylevelS2) {
		this.studylevelS2 = studylevelS2;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUniversity() {
		return this.university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public String getUniversityInternalReference() {
		return this.universityInternalReference;
	}

	public void setUniversityInternalReference(String universityInternalReference) {
		this.universityInternalReference = universityInternalReference;
	}

	public String getWinter() {
		return this.winter;
	}

	public void setWinter(String winter) {
		this.winter = winter;
	}

	public Date getDateHistory() {
		return dateHistory;
	}

	public void setDateHistory(Date dateHistory) {
		this.dateHistory = dateHistory;
	}

	public String getDoubleDiploma() {
		return doubleDiploma;
	}

	public void setDoubleDiploma(String doubleDiploma) {
		this.doubleDiploma = doubleDiploma;
	}

	public BigDecimal getScoYear() {
		return scoYear;
	}

	public void setScoYear(BigDecimal scoYear) {
		this.scoYear = scoYear;
	}

	public String getHelp() {
		return help;
	}

	public void setHelp(String help) {
		this.help = help;
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

	public String getPeriodeCode() {
		return periodeCode;
	}

	public void setPeriodeCode(String periodeCode) {
		this.periodeCode = periodeCode;
	}

	public String getModuleCode() {
		return moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	public String getModuleLib() {
		return moduleLib;
	}

	public void setModuleLib(String moduleLib) {
		this.moduleLib = moduleLib;
	}

	public String getUrlid() {
		return urlid;
	}

	public void setUrlid(String urlid) {
		this.urlid = urlid;
	}

	public String getCurrentProgram() {
		return currentProgram;
	}

	public void setCurrentProgram(String currentProgram) {
		this.currentProgram = currentProgram;
	}

	public String getEdhecProgram() {
		return edhecProgram;
	}

	public void setEdhecProgram(String edhecProgram) {
		this.edhecProgram = edhecProgram;
	}

	public String getInsurance() {
		return insurance;
	}

	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}

	public String getFirstNameUnquoted() {
		return firstNameUnquoted;
	}

	public void setFirstNameUnquoted(String firstNameUnquoted) {
		this.firstNameUnquoted = firstNameUnquoted;
	}

	public String getLastNameUnquoted() {
		return lastNameUnquoted;
	}

	public void setLastNameUnquoted(String lastNameUnquoted) {
		this.lastNameUnquoted = lastNameUnquoted;
	}

	public String getHelpUnquoted() {
		return helpUnquoted;
	}

	public void setHelpUnquoted(String helpUnquoted) {
		this.helpUnquoted = helpUnquoted;
	}
}
