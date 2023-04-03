package com.edhec.commonedhecapps.model.foreignstudent;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the SCO_FOREIGN_STUDENT_STATUS_V1 database table.
 *
 */
@Entity
@Table(schema="SCHOLARSHIP", name="SCO_FOREIGN_STUDENT_STATUS_V1")
public class ForeignStudentStatusView implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name="CRT_ID_STATUS")
    private String crtIdStatus;

    private String description;

    @Id
    @Column(name="FS_ID")
    private String fsId;

    private String status;

    @Column(name="STATUS_DATE")
    private Date statusDate;

    public ForeignStudentStatusView() {
    }

    public String getCrtIdStatus() {
        return this.crtIdStatus;
    }

    public void setCrtIdStatus(String crtIdStatus) {
        this.crtIdStatus = crtIdStatus;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFsId() {
        return this.fsId;
    }

    public void setFsId(String fsId) {
        this.fsId = fsId;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStatusDate() {
        return this.statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

}
