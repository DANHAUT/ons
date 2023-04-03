package com.edhec.commonedhecapps.model.common;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * The primary key class for the COM_CRITERIA_L database table.
 *
 */
@Embeddable
public class ComCriteriaLPK implements Serializable {
    //default serial version id, required for serializable classes.
    private static final long serialVersionUID = 1L;

    @Column(name="LNG_ID")
    private String lngId;

    @Column(name="CRT_ID")
    private String crtId;

    @Column(name="CRT_CKA_ID")
    private String crtCkaId;

    public ComCriteriaLPK() {
    }
    public String getLngId() {
        return this.lngId;
    }
    public void setLngId(String lngId) {
        this.lngId = lngId;
    }
    public String getCrtId() {
        return this.crtId;
    }
    public void setCrtId(String crtId) {
        this.crtId = crtId;
    }
    public String getCrtCkaId() {
        return this.crtCkaId;
    }
    public void setCrtCkaId(String crtCkaId) {
        this.crtCkaId = crtCkaId;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ComCriteriaLPK)) {
            return false;
        }
        ComCriteriaLPK castOther = (ComCriteriaLPK)other;
        return
                this.lngId.equals(castOther.lngId)
                        && this.crtId.equals(castOther.crtId)
                        && this.crtCkaId.equals(castOther.crtCkaId);
    }

    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + this.lngId.hashCode();
        hash = hash * prime + this.crtId.hashCode();
        hash = hash * prime + this.crtCkaId.hashCode();

        return hash;
    }
}
