package com.edhec.commonedhecapps.model.common;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the COM_CRITERIA_L_V1 database table.
 *
 */
@Entity
@Table(schema = "COMMON", name="COM_CRITERIA_L_V1")
@NamedQuery(name = "ComCriteriaLView.findAllByParameters", query = "select o from ComCriteriaLView o where o.crtCkaId = :crtCkaId and o.lngId = :lngId order by o.name")
public class ComCriteriaLView implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ComCriteriaLPK id;

    @Column(name="CREATED_BY")
    private String createdBy;

    @Column(name="CRT_CKA_ID",insertable = false, updatable = false)
    private String crtCkaId;

    @Column(name="CRT_ID",insertable = false, updatable = false)
    private String crtId;

    @Temporal(TemporalType.DATE)
    @Column(name="DATE_CREATED")
    private Date dateCreated;

    @Temporal(TemporalType.DATE)
    @Column(name="DATE_MODIFIED")
    private Date dateModified;

    private String description;

    @Column(name="LNG_ID",insertable = false, updatable = false)
    private String lngId;

    @Column(name="MODIFIED_BY")
    private String modifiedBy;

    private String name;

    @Column(name="ORDER_BY")
    private BigDecimal orderBy;

    //bi-directional many-to-one association to ComCriteria
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name="CRT_CKA_ID", referencedColumnName="CKA_ID",insertable = false, updatable = false),
            @JoinColumn(name="CRT_ID", referencedColumnName="ID",insertable = false, updatable = false)
    })
    private ComCriteria comCriteria;

    public ComCriteriaLView() {
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCrtCkaId() {
        return this.crtCkaId;
    }

    public void setCrtCkaId(String crtCkaId) {
        this.crtCkaId = crtCkaId;
    }

    public String getCrtId() {
        return this.crtId;
    }

    public void setCrtId(String crtId) {
        this.crtId = crtId;
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

    public String getLngId() {
        return this.lngId;
    }

    public void setLngId(String lngId) {
        this.lngId = lngId;
    }

    public String getModifiedBy() {
        return this.modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getName() {
        //return StringUtils.capitalize(this.name.trim().toLowerCase());
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getOrderBy() {
        return this.orderBy;
    }

    public void setOrderBy(BigDecimal orderBy) {
        this.orderBy = orderBy;
    }

    public ComCriteria getComCriteria() {
        return comCriteria;
    }

    public void setComCriteria(ComCriteria comCriteria) {
        this.comCriteria = comCriteria;
    }

}
