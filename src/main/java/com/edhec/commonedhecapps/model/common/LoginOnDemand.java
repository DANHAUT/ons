package com.edhec.commonedhecapps.model.common;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import java.sql.Date;
import java.math.BigDecimal;


/**
 * The persistent class for the COM_LOGIN_ON_DEMANDS database table.
 * 
 */
@Entity
@Table(schema="COMMON", name="COM_LOGIN_ON_DEMANDS")
@NamedQueries(
{@NamedQuery(name="LoginOnDemand.findById", query="select l from LoginOnDemand l where l.dId = :id"),
@NamedQuery(name="LoginOnDemand.findByEmail", query="select l from LoginOnDemand l where l.dEmail = :email"),
@NamedQuery(name="LoginOnDemand.findByActivation", query="select l from LoginOnDemand l where l.dActivation = :activation")}
)
public class LoginOnDemand implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="D_ID")
	private String dId;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Column(name="D_ACTIVATION")
	private String dActivation;

	@Column(name="D_EMAIL")
	private String dEmail;

	@Column(name="D_FIRST_NAME")
	private String dFirstName;

	@Column(name="D_INTERNAL_REFERENCE")
	private String dInternalReference;

	@Column(name="D_LOGIN")
	private String dLogin;

	@Column(name="D_NAME")
	private String dName;

	@Column(name="D_PASSWORD")
	private String dPassword;

	@Column(name="D_SEX")
	private BigDecimal dSex;

	@Column(name="D_STATUS")
	private String dStatus;

	@Column(name="D_TEL_FIXE")
	private String dTelFixe;

	@Column(name="D_TEL_MOB")
	private String dTelMob;

	@Column(name="D_TITLE")
	private String dTitle;

	@Column(name="DATE_CREATED")
	private Date dateCreated;

	@Column(name="DATE_MODIFIED")
	private Date dateModified;

	@Column(name="MODIFIED_BY")
	private String modifiedBy;

    public LoginOnDemand() {
    }

	public String getDId() {
		return this.dId;
	}

	public void setDId(String dId) {
		this.dId = dId;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getDActivation() {
		return this.dActivation;
	}

	public void setDActivation(String dActivation) {
		this.dActivation = dActivation;
	}

	public String getDEmail() {
		return this.dEmail;
	}

	public void setDEmail(String dEmail) {
		this.dEmail = dEmail;
	}

	public String getDFirstName() {
		return this.dFirstName;
	}

	public void setDFirstName(String dFirstName) {
		this.dFirstName = dFirstName;
	}

	public String getDInternalReference() {
		return this.dInternalReference;
	}

	public void setDInternalReference(String dInternalReference) {
		this.dInternalReference = dInternalReference;
	}

	public String getDLogin() {
		return this.dLogin;
	}

	public void setDLogin(String dLogin) {
		this.dLogin = dLogin;
	}

	public String getDName() {
		return this.dName;
	}

	public void setDName(String dName) {
		this.dName = dName;
	}

	public String getDPassword() {
		return this.dPassword;
	}

	public void setDPassword(String dPassword) {
		this.dPassword = dPassword;
	}

	public BigDecimal getDSex() {
		return this.dSex;
	}

	public void setDSex(BigDecimal dSex) {
		this.dSex = dSex;
	}

	public String getDStatus() {
		return this.dStatus;
	}

	public void setDStatus(String dStatus) {
		this.dStatus = dStatus;
	}

	public String getDTelFixe() {
		return this.dTelFixe;
	}

	public void setDTelFixe(String dTelFixe) {
		this.dTelFixe = dTelFixe;
	}

	public String getDTelMob() {
		return this.dTelMob;
	}

	public void setDTelMob(String dTelMob) {
		this.dTelMob = dTelMob;
	}

	public String getDTitle() {
		return this.dTitle;
	}

	public void setDTitle(String dTitle) {
		this.dTitle = dTitle;
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

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

}