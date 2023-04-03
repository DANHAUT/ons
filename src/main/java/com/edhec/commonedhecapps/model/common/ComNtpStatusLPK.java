package com.edhec.commonedhecapps.model.common;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * The primary key class for the COM_NTP_STATUS_L database table.
 *
 */
@Embeddable
public class ComNtpStatusLPK implements Serializable {
    //default serial version id, required for serializable classes.
    private static final long serialVersionUID = 1L;

    @Column(name="NTS_ID")
    private String ntsId;

    @Column(name="LNG_ID")
    private String lngId;

    public ComNtpStatusLPK() {
    }
    public String getNtsId() {
        return this.ntsId;
    }
    public void setNtsId(String ntsId) {
        this.ntsId = ntsId;
    }
    public String getLngId() {
        return this.lngId;
    }
    public void setLngId(String lngId) {
        this.lngId = lngId;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ComNtpStatusLPK)) {
            return false;
        }
        ComNtpStatusLPK castOther = (ComNtpStatusLPK)other;
        return
                this.ntsId.equals(castOther.ntsId)
                        && this.lngId.equals(castOther.lngId);
    }

    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + this.ntsId.hashCode();
        hash = hash * prime + this.lngId.hashCode();

        return hash;
    }
}
