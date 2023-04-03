package com.edhec.commonedhecapps.model.common;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the COM_NTP_STATUS database table.
 *
 */
@Entity
@Table(schema = "COMMON", name="COM_NTP_STATUS")
public class ComNtpStatus implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

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

    //bi-directional many-to-one association to ComNaturalPerson
    @OneToMany(mappedBy="comNtpStatus")
    private List<ComNaturalPerson> comNaturalPersonList;

    //bi-directional many-to-one association to ComNtpStatusL
    @OneToMany(mappedBy="comNtpStatus")
    private List<ComNtpStatusLView> comNtpStatusLViewList;

    public ComNtpStatus() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
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

    public List<ComNaturalPerson> getComNaturalPersonList() {
        return this.comNaturalPersonList;
    }

    public void setComNaturalPersonList(List<ComNaturalPerson> comNaturalPersonList) {
        this.comNaturalPersonList = comNaturalPersonList;
    }

    public List<ComNtpStatusLView> getComNtpStatusLViewList() {
        return comNtpStatusLViewList;
    }

    public void setComNtpStatusLViewList(
            List<ComNtpStatusLView> comNtpStatusLViewList) {
        this.comNtpStatusLViewList = comNtpStatusLViewList;
    }



}
