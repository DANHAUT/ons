package com.edhec.commonedhecapps.model.common;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;


/**
 * The persistent class for the COM_ARTIFICIAL_PERSON database table.
 * 
 */
@Entity
@Table(schema="COMMON", name="COM_ARTIFICIAL_PERSON")
@NamedQueries({
	  @NamedQuery(name = "ComArtificialPerson.findByCntId", query = "select o from ComArtificialPerson o where o.cntId = :cntId")
})
public class ComArtificialPerson implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CNT_ID")
	private String cntId;

//	@Column(name="COMPANY_NAME")
//	private String companyName;

	@Column(name="CREATED_BY")
	private String createdBy;

//	@Column(name="CRT_CKA_ID_NAF_CODE")
//	private String crtCkaIdNafCode;
//
//	@Column(name="CRT_CKA_ID_NATIONALITY")
//	private String crtCkaIdNationality;
//
//	@Column(name="CRT_ID_NAF_CODE")
//	private String crtIdNafCode;
//
//	@Column(name="CRT_ID_NATIONALITY")
//	private String crtIdNationality;

	@Column(name="DATE_CREATED")
	private Date dateCreated;

	@Column(name="DATE_MODIFIED")
	private Date dateModified;

//	@Column(name="KAP_ID")
//	private String kapId;

	@Column(name="MODIFIED_BY")
	private String modifiedBy;

	//bi-directional many-to-one association to ComJob
	@OneToMany(mappedBy="comArtificialPerson", fetch = FetchType.LAZY)
	@Cascade( { org.hibernate.annotations.CascadeType.ALL,org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
	private List<ComJob> comJobList;

	
	//bi-directional one-to-one association to ComContact
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="CNT_ID")
	private ComContact comContact;
	
    public ComArtificialPerson() {
    }

	public String getCntId() {
		return this.cntId;
	}

	public void setCntId(String cntId) {
		this.cntId = cntId;
	}

//	public String getCompanyName() {
//		return this.companyName;
//	}
//
//	public void setCompanyName(String companyName) {
//		this.companyName = companyName;
//	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

//	public String getCrtCkaIdNafCode() {
//		return this.crtCkaIdNafCode;
//	}
//
//	public void setCrtCkaIdNafCode(String crtCkaIdNafCode) {
//		this.crtCkaIdNafCode = crtCkaIdNafCode;
//	}
//
//	public String getCrtCkaIdNationality() {
//		return this.crtCkaIdNationality;
//	}
//
//	public void setCrtCkaIdNationality(String crtCkaIdNationality) {
//		this.crtCkaIdNationality = crtCkaIdNationality;
//	}
//
//	public String getCrtIdNafCode() {
//		return this.crtIdNafCode;
//	}
//
//	public void setCrtIdNafCode(String crtIdNafCode) {
//		this.crtIdNafCode = crtIdNafCode;
//	}
//
//	public String getCrtIdNationality() {
//		return this.crtIdNationality;
//	}
//
//	public void setCrtIdNationality(String crtIdNationality) {
//		this.crtIdNationality = crtIdNationality;
//	}

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

//	public String getKapId() {
//		return this.kapId;
//	}
//
//	public void setKapId(String kapId) {
//		this.kapId = kapId;
//	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public List<ComJob> getComJobList() {
		return this.comJobList;
	}

	public void setComJobList(List<ComJob> comJobList) {
		this.comJobList = comJobList;
	}

	public ComContact getComContact() {
		return comContact;
	}

	public void setComContact(ComContact comContact) {
		this.comContact = comContact;
	}
	
}