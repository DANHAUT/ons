package com.edhec.commonedhecapps.model.foreignstudent;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

@Embeddable
public class ForeignStudentStatusPK implements Serializable {
    //default serial version id, required for serializable classes.
    private static final long serialVersionUID = 1L;

    @Column(name="FS_ID")
    private String fsId;

    @Column(name="CRT_ID_STATUS")
    private String crtIdStatus;

    @Column(name="STATUS_DATE")
    private Date statusDate;


    public ForeignStudentStatusPK() {
    }

    public String getFsId() {
        return fsId;
    }

    public void setFsId(String fsId) {
        this.fsId = fsId;
    }

    public String getCrtIdStatus() {
        return crtIdStatus;
    }

    public void setCrtIdStatus(String crtIdStatus) {
        this.crtIdStatus = crtIdStatus;
    }

    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((crtIdStatus == null) ? 0 : crtIdStatus.hashCode());
        result = prime * result + ((fsId == null) ? 0 : fsId.hashCode());
        result = prime * result
                + ((statusDate == null) ? 0 : statusDate.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ForeignStudentStatusPK other = (ForeignStudentStatusPK) obj;
        if (crtIdStatus == null) {
            if (other.crtIdStatus != null)
                return false;
        } else if (!crtIdStatus.equals(other.crtIdStatus))
            return false;
        if (fsId == null) {
            if (other.fsId != null)
                return false;
        } else if (!fsId.equals(other.fsId))
            return false;
        if (statusDate == null) {
            if (other.statusDate != null)
                return false;
        } else if (!statusDate.equals(other.statusDate))
            return false;
        return true;
    }
}
