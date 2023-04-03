package com.edhec.commonedhecapps.model.common;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the COM_NATURAL_PERSON database table.
 *
 */
@Entity
@Table(schema = "COMMON", name="COM_NATURAL_PERSON")
public class ComNaturalPerson implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="CNT_ID")
    private String cntId;

    @Temporal(TemporalType.DATE)
    @Column(name="BIRTH_DATE")
    private Date birthDate;

    @Column(name="BIRTH_NAME")
    private String birthName;

    @Column(name="BIRTH_PLACE")
    private String birthPlace;

    @Column(name="CREATED_BY")
    private String createdBy;

    @Temporal(TemporalType.DATE)
    @Column(name="DATE_CREATED")
    private Date dateCreated;

    @Temporal(TemporalType.DATE)
    @Column(name="DATE_MODIFIED")
    private Date dateModified;

    @Temporal(TemporalType.DATE)
    @Column(name="DEATH_DATE")
    private Date deathDate;

    @Column(name="FAD_ID")
    private String fadId;

    @Column(name="FIRST_NAME")
    private String firstName;

    @Column(name="FIRST_NAMES")
    private String firstNames;

    @Column(name="INE_NUMBER")
    private String ineNumber;

    @Column(name="MARITAL_NAME")
    private String maritalName;

    @Column(name="MODIFIED_BY")
    private String modifiedBy;

    @Column(name="MSTS_ID")
    private String mstsId;

    private BigDecimal sex;

    private String softwares;

    @Column(name="SS_NUMBER")
    private String ssNumber;

    @Column(name = "CRT_BIRTH_COUNTRY")
    protected String crtBirthCountry;

    @Column(name = "CRT_CKA_BIRTH_COUNTRY")
    protected String crtCkaBirthCountry;

    //bi-directional one-to-one association to Contact
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CNT_ID")
    private ComContact comContact;

    //bi-directional many-to-one association to ComCriteria
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name="CRT_BIRTH_COUNTRY", referencedColumnName="ID", insertable = false, updatable = false),
            @JoinColumn(name="CRT_CKA_BIRTH_COUNTRY", referencedColumnName="CKA_ID", insertable = false, updatable = false)
    })
    private ComCriteria comCriteria;

    //bi-directional many-to-one association to ComNtpStatus
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="NTS_ID")
    private ComNtpStatus comNtpStatus;

    public ComNaturalPerson() {
    }

    public String getCntId() {
        return this.cntId;
    }

    public void setCntId(String cntId) {
        this.cntId = cntId;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthName() {
        return this.birthName;
    }

    public void setBirthName(String birthName) {
        this.birthName = birthName;
    }

    public String getBirthPlace() {
        return this.birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
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

    public Date getDeathDate() {
        return this.deathDate;
    }

    public void setDeathDate(Date deathDate) {
        this.deathDate = deathDate;
    }

    public String getFadId() {
        return this.fadId;
    }

    public void setFadId(String fadId) {
        this.fadId = fadId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstNames() {
        return this.firstNames;
    }

    public void setFirstNames(String firstNames) {
        this.firstNames = firstNames;
    }

    public String getIneNumber() {
        return this.ineNumber;
    }

    public void setIneNumber(String ineNumber) {
        this.ineNumber = ineNumber;
    }

    public String getMaritalName() {
        return this.maritalName;
    }

    public void setMaritalName(String maritalName) {
        this.maritalName = maritalName;
    }

    public String getModifiedBy() {
        return this.modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getMstsId() {
        return this.mstsId;
    }

    public void setMstsId(String mstsId) {
        this.mstsId = mstsId;
    }

    public BigDecimal getSex() {
        return this.sex;
    }

    public void setSex(BigDecimal sex) {
        this.sex = sex;
    }

    public String getSoftwares() {
        return this.softwares;
    }

    public void setSoftwares(String softwares) {
        this.softwares = softwares;
    }

    public String getSsNumber() {
        return this.ssNumber;
    }

    public void setSsNumber(String ssNumber) {
        this.ssNumber = ssNumber;
    }

    public String getCrtBirthCountry() {
        return crtBirthCountry;
    }

    public void setCrtBirthCountry(String crtBirthCountry) {
        this.crtBirthCountry = crtBirthCountry;
    }

    public String getCrtCkaBirthCountry() {
        return crtCkaBirthCountry;
    }

    public void setCrtCkaBirthCountry(String crtCkaBirthCountry) {
        this.crtCkaBirthCountry = crtCkaBirthCountry;
    }

    public ComContact getComContact() {
        return this.comContact;
    }

    public void setComContact(ComContact comContact) {
        this.comContact = comContact;
    }

    public ComCriteria getComCriteria() {
        return this.comCriteria;
    }

    public void setComCriteria(ComCriteria comCriteria) {
        this.comCriteria = comCriteria;
    }

    public ComNtpStatus getComNtpStatus() {
        return this.comNtpStatus;
    }

    public void setComNtpStatus(ComNtpStatus comNtpStatus) {
        this.comNtpStatus = comNtpStatus;
    }

}
