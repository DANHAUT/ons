package com.edhec.commonedhecapps.model.common;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the COM_NTP_STATUS_L_V1 database table.
 *
 */
@Entity
@Table(schema = "COMMON", name="COM_NTP_STATUS_L_V1")
public class ComNtpStatusLView implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ComNtpStatusLPK id;

    @Column(name="CREATED_BY")
    private String createdBy;

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

    @Column(name="NTS_ID",insertable = false, updatable = false)
    private String ntsId;

    //bi-directional many-to-one association to ComNtpStatus
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="NTS_ID",insertable = false, updatable = false)
    private ComNtpStatus comNtpStatus;

    public ComNtpStatusLView() {
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
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNtsId() {
        return this.ntsId;
    }

    public void setNtsId(String ntsId) {
        this.ntsId = ntsId;
    }

    public ComNtpStatus getComNtpStatus() {
        return comNtpStatus;
    }

    public void setComNtpStatus(ComNtpStatus comNtpStatus) {
        this.comNtpStatus = comNtpStatus;
    }


}
