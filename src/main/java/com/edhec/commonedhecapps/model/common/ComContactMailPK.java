package com.edhec.commonedhecapps.model.common;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * The primary key class for the COM_CONTACT_MAIL database table.
 *
 */
@Embeddable
public class ComContactMailPK implements Serializable {
    //default serial version id, required for serializable classes.
    private static final long serialVersionUID = 1L;

    @Column(name="CNT_ID")
    private String cntId;

    @Column(name="MAL_ID")
    private String malId;

    @Column(name="AKD_ID")
    private String akdId;

    public ComContactMailPK() {
    }
    public String getCntId() {
        return this.cntId;
    }
    public void setCntId(String cntId) {
        this.cntId = cntId;
    }
    public String getMalId() {
        return this.malId;
    }
    public void setMalId(String malId) {
        this.malId = malId;
    }
    public String getAkdId() {
        return this.akdId;
    }
    public void setAkdId(String akdId) {
        this.akdId = akdId;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ComContactMailPK)) {
            return false;
        }
        ComContactMailPK castOther = (ComContactMailPK)other;
        return
                this.cntId.equals(castOther.cntId)
                        && this.malId.equals(castOther.malId)
                        && this.akdId.equals(castOther.akdId);
    }

    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + this.cntId.hashCode();
        hash = hash * prime + this.malId.hashCode();
        hash = hash * prime + this.akdId.hashCode();

        return hash;
    }
}