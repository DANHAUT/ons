package com.edhec.commonedhecapps.model.foreignstudent;


import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the BOOK_NIV_CHOIX_INCOMING database table.
 * 
 */
@Entity
@Table(schema="SCHOLARSHIP", name="BOOK_NIV_CHOIX_INCOMING")
@NamedQueries({
	  @NamedQuery(name="BookNivChoixIncoming.findAll", query="SELECT b FROM BookNivChoixIncoming b"),
	  @NamedQuery(name = "BookNivChoixIncoming.findByCodeWithoutSpec", query = "select b FROM BookNivChoixIncoming b where b.anneeUniv=:anneeUniv and b.code=:code and b.specialCode is null"),
	})
public class BookNivChoixIncoming implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ANNEE_UNIV")
	private BigDecimal anneeUniv;

	@Column(name="CAMPUS_CODE")
	private String campusCode;

	private String campus;

	@Id
	private String code;

	@Column(name="DBLE_DEGREE")
	private String dbleDegree;

	@Column(name="LEVEL_CODE")
	private String levelCode;

	@Column(name="LEVEL_LIB")
	private String levelLib;

	private String libelle;

	@Column(name="PERIODE_CODE")
	private String periodeCode;

	@Column(name="SPECIAL_CODE")
	private String specialCode;

	@Column(name="SPECIAL_LIB")
	private String specialLib;

	@Column(name="KIND_OF_SELECT")
	private String kindOfSelect;

	public BookNivChoixIncoming() {
	}

	public BigDecimal getAnneeUniv() {
		return this.anneeUniv;
	}

	public void setAnneeUniv(BigDecimal anneeUniv) {
		this.anneeUniv = anneeUniv;
	}

	public String getCampusCode() {
		return this.campusCode;
	}

	public void setCampusCode(String campusCode) {
		this.campusCode = campusCode;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDbleDegree() {
		return this.dbleDegree;
	}

	public void setDbleDegree(String dbleDegree) {
		this.dbleDegree = dbleDegree;
	}

	public String getLevelCode() {
		return this.levelCode;
	}

	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}

	public String getLevelLib() {
		return this.levelLib;
	}

	public void setLevelLib(String levelLib) {
		this.levelLib = levelLib;
	}

	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getPeriodeCode() {
		return this.periodeCode;
	}

	public void setPeriodeCode(String periodeCode) {
		this.periodeCode = periodeCode;
	}

	public String getSpecialCode() {
		return this.specialCode;
	}

	public void setSpecialCode(String specialCode) {
		this.specialCode = specialCode;
	}

	public String getSpecialLib() {
		return this.specialLib;
	}

	public void setSpecialLib(String specialLib) {
		this.specialLib = specialLib;
	}

	public String getKindOfSelect() {
		return kindOfSelect;
	}

	public void setKindOfSelect(String kindOfSelect) {
		this.kindOfSelect = kindOfSelect;
	}

	public String getCampus() {
		return campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}

}