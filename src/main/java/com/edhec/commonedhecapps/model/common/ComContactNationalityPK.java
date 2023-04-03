package com.edhec.commonedhecapps.model.common;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * The primary key class for the COM_CONTACT_NATIONALITY database table.
 *
 */
@Embeddable
public class ComContactNationalityPK implements Serializable {
    //default serial version id, required for serializable classes.
    private static final long serialVersionUID = 1L;

    @Column(name="CNT_ID")
    private String cntId;

    @Column(name="CRT_CKA_ID")
    private String crtCkaId;

    @Column(name="CRT_ID")
    private String crtId;

    public ComContactNationalityPK() {
    }
    public String getCntId() {
        return this.cntId;
    }
    public void setCntId(String cntId) {
        this.cntId = cntId;
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

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ComContactNationalityPK)) {
            return false;
        }
        ComContactNationalityPK castOther = (ComContactNationalityPK)other;
        return
                this.cntId.equals(castOther.cntId)
                        && this.crtCkaId.equals(castOther.crtCkaId)
                        && this.crtId.equals(castOther.crtId);
    }

    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + this.cntId.hashCode();
        hash = hash * prime + this.crtCkaId.hashCode();
        hash = hash * prime + this.crtId.hashCode();

        return hash;
    }
}
