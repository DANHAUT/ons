package com.edhec.commonedhecapps.model.common;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the COM_JOB database table.
 * 
 */
@Entity
@NamedQueries({
	  @NamedQuery(name = "ComJob.findByArpCntId", query = "select o from ComJob o where o.arpCntId = :arpCntId and o.dateTo is null")
})
@Table(schema="COMMON", name="COM_JOB")
public class ComJob implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ComJobPK id;

	@Column(name="ADR_ID")
	private String adrId;

	@Column(name="ARP_CNT_ID", insertable = false, updatable = false)
	private String arpCntId;
	
	@Column(name="CREATED_BY")
	private String createdBy;

	@Column(name="CRT_CKA_ID_FUNCTION")
	private String crtCkaIdFunction;

	@Column(name="CRT_ID_FUNCTION")
	private String crtIdFunction;

	@Column(name="DATE_CREATED")
	private Date dateCreated;

	@Column(name="DATE_MODIFIED")
	private java.util.Date dateModified;

	@Column(name="DATE_TO")
	private Date dateTo;

	private String description;

	@Column(name="FAX_ID")
	private String faxId;

	@Column(name="MAL_ID")
	private String malId;

	@Column(name="MOB_ID")
	private String mobId;

	@Column(name="MODIFIED_BY")
	private String modifiedBy;

	@Column(name="PHN_ID")
	private String phnId;

	//bi-directional many-to-one association to ComArtificialPerson
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="ARP_CNT_ID", insertable = false, updatable = false)
	private ComArtificialPerson comArtificialPerson;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="NTP_CNT_ID", insertable = false, updatable = false)
	private ComNaturalPerson comNaturalPerson;

    public ComJob() {
    }

	public ComJobPK getId() {
		return this.id;
	}

	public void setId(ComJobPK id) {
		this.id = id;
	}
	
	public String getAdrId() {
		return this.adrId;
	}

	public void setAdrId(String adrId) {
		this.adrId = adrId;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCrtCkaIdFunction() {
		return this.crtCkaIdFunction;
	}

	public void setCrtCkaIdFunction(String crtCkaIdFunction) {
		this.crtCkaIdFunction = crtCkaIdFunction;
	}

	public String getCrtIdFunction() {
		return this.crtIdFunction;
	}

	public void setCrtIdFunction(String crtIdFunction) {
		this.crtIdFunction = crtIdFunction;
	}

	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public java.util.Date getDateModified() {
		return this.dateModified;
	}

	public void setDateModified(java.util.Date dateModified) {
		this.dateModified = dateModified;
	}

	public Date getDateTo() {
		return this.dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFaxId() {
		return this.faxId;
	}

	public void setFaxId(String faxId) {
		this.faxId = faxId;
	}

	public String getMalId() {
		return this.malId;
	}

	public void setMalId(String malId) {
		this.malId = malId;
	}

	public String getMobId() {
		return this.mobId;
	}

	public void setMobId(String mobId) {
		this.mobId = mobId;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getPhnId() {
		return this.phnId;
	}

	public void setPhnId(String phnId) {
		this.phnId = phnId;
	}

	public ComArtificialPerson getComArtificialPerson() {
		return this.comArtificialPerson;
	}

	public void setComArtificialPerson(ComArtificialPerson comArtificialPerson) {
		this.comArtificialPerson = comArtificialPerson;
	}

	public ComNaturalPerson getComNaturalPerson() {
		return comNaturalPerson;
	}

	public void setComNaturalPerson(ComNaturalPerson comNaturalPerson) {
		this.comNaturalPerson = comNaturalPerson;
	}

	public String getArpCntId() {
		return arpCntId;
	}

	public void setArpCntId(String arpCntId) {
		this.arpCntId = arpCntId;
	}
	
}