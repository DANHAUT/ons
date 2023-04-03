package com.edhec.commonedhecapps.model.common;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the COM_CONTACT_NATIONALITY database table.
 *
 */
@Entity
@Table(schema = "COMMON", name="COM_CONTACT_NATIONALITY")
public class ComContactNationality implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ComContactNationalityPK id;

    @Column(name="CREATED_BY")
    private String createdBy;

    @Temporal(TemporalType.DATE)
    @Column(name="DATE_CREATED")
    private Date dateCreated;

    @Temporal(TemporalType.DATE)
    @Column(name="DATE_MODIFIED")
    private Date dateModified;

    @Column(name="MODIFIED_BY")
    private String modifiedBy;

    @Column(name="CRT_CKA_ID", insertable=false, updatable=false)
    private String crtCkaId;

    @Column(name="CRT_ID", insertable=false, updatable=false)
    private String crtId;

    //bi-directional many-to-one association to Contact
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CNT_ID",insertable = false, updatable = false)
    private ComContact comContact;

    //uni-directional many-to-one association to ComCriteria
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name="CRT_CKA_ID", referencedColumnName="CKA_ID",insertable = false, updatable = false),
            @JoinColumn(name="CRT_ID", referencedColumnName="ID",insertable = false, updatable = false)
    })
    private ComCriteria comCriteria;

    public ComContactNationality() {
    }

    public ComContactNationalityPK getId() {
        return this.id;
    }

    public void setId(ComContactNationalityPK id) {
        this.id = id;
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

    public String getModifiedBy() {
        return this.modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
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

    public String getCrtCkaId() {
        return crtCkaId;
    }

    public void setCrtCkaId(String crtCkaId) {
        this.crtCkaId = crtCkaId;
    }

    public String getCrtId() {
        return crtId;
    }

    public void setCrtId(String crtId) {
        this.crtId = crtId;
    }

}
