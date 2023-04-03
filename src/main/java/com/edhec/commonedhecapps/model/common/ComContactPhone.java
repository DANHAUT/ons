package com.edhec.commonedhecapps.model.common;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the COM_CONTACT_PHONE database table.
 *
 */
@Entity
@Table(schema = "COMMON", name="COM_CONTACT_PHONE")
public class ComContactPhone implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ComContactPhonePK id;

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

    private BigDecimal nlia;

    @Column(name="ORDER_BY")
    private BigDecimal orderBy;

    //bi-directional many-to-one association to Contact
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CNT_ID",insertable = false, updatable = false)
    private ComContact comContact;

    //bi-directional many-to-one association to ComPhone
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PHN_ID",insertable = false, updatable = false)
    private ComPhone comPhone;

    public ComContactPhone() {
    }

    public ComContactPhonePK getId() {
        return this.id;
    }

    public void setId(ComContactPhonePK id) {
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

    public BigDecimal getNlia() {
        return this.nlia;
    }

    public void setNlia(BigDecimal nlia) {
        this.nlia = nlia;
    }

    public BigDecimal getOrderBy() {
        return this.orderBy;
    }

    public void setOrderBy(BigDecimal orderBy) {
        this.orderBy = orderBy;
    }

    public ComContact getComContact() {
        return this.comContact;
    }

    public void setComContact(ComContact comContact) {
        this.comContact = comContact;
    }

    public ComPhone getComPhone() {
        return this.comPhone;
    }

    public void setComPhone(ComPhone comPhone) {
        this.comPhone = comPhone;
    }

}
