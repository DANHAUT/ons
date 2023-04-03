package com.edhec.commonedhecapps.model.foreignstudent;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the SCO_FOREIGN_STUDENT_STATUS database table.
 *
 */
@Entity
@Table(schema="SCHOLARSHIP", name="SCO_FOREIGN_STUDENT_STATUS")
public class ForeignStudentStatus implements Serializable {
    private static final long serialVersionUID = 1L;

    private String cc;

    @EmbeddedId
    private ForeignStudentStatusPK pk;

    @Column(name="CREATED_BY")
    private String createdBy;

    @Column(name="CRT_CKA_ID_STATUS")
    private String crtCkaIdStatus;

    @Column(name="CRT_ID_STATUS",insertable = false, updatable = false)
    private String crtIdStatus;

    @Column(name="DATE_CREATED")
    private Date dateCreated;

    @Column(name="DATE_MODIFIED")
    private Date dateModified;

    @Column(name="FS_ID",insertable = false, updatable = false)
    private String fsId;

    @Column(name="MAILING_DATE")
    private Date mailingDate;

    private String message;

    @Column(name="MODIFIED_BY")
    private String modifiedBy;

    @Column(name="STATUS_DATE",insertable = false, updatable = false)
    private Date statusDate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="FS_ID",insertable = false, updatable = false)
    private ForeignStudent foreignStudent;

    public ForeignStudentStatus() {
    }

    public String getCc() {
        return this.cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCrtCkaIdStatus() {
        return this.crtCkaIdStatus;
    }

    public void setCrtCkaIdStatus(String crtCkaIdStatus) {
        this.crtCkaIdStatus = crtCkaIdStatus;
    }

    public String getCrtIdStatus() {
        return this.crtIdStatus;
    }

    public void setCrtIdStatus(String crtIdStatus) {
        this.crtIdStatus = crtIdStatus;
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

    public String getFsId() {
        return this.fsId;
    }

    public void setFsId(String fsId) {
        this.fsId = fsId;
    }

    public Date getMailingDate() {
        return this.mailingDate;
    }

    public void setMailingDate(Date mailingDate) {
        this.mailingDate = mailingDate;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getModifiedBy() {
        return this.modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getStatusDate() {
        return this.statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    public ForeignStudentStatusPK getPk() {
        return pk;
    }

    public void setPk(ForeignStudentStatusPK pk) {
        this.pk = pk;
    }

    public ForeignStudent getForeignStudent() {
        return foreignStudent;
    }

    public void setForeignStudent(ForeignStudent foreignStudent) {
        this.foreignStudent = foreignStudent;
    }
}
