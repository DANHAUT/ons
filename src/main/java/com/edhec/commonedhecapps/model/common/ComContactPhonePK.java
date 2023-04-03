package com.edhec.commonedhecapps.model.common;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * The primary key class for the COM_CONTACT_PHONE database table.
 *
 */
@Embeddable
public class ComContactPhonePK implements Serializable {
    //default serial version id, required for serializable classes.
    private static final long serialVersionUID = 1L;

    @Column(name="CNT_ID")
    private String cntId;

    @Column(name="PHN_ID")
    private String phnId;

    @Column(name="AKD_ID")
    private String akdId;

    @Column(name="KOP_ID")
    private String kopId;

    public ComContactPhonePK() {
    }
    public String getCntId() {
        return this.cntId;
    }
    public void setCntId(String cntId) {
        this.cntId = cntId;
    }
    public String getPhnId() {
        return this.phnId;
    }
    public void setPhnId(String phnId) {
        this.phnId = phnId;
    }
    public String getAkdId() {
        return this.akdId;
    }
    public void setAkdId(String akdId) {
        this.akdId = akdId;
    }
    public String getKopId() {
        return this.kopId;
    }
    public void setKopId(String kopId) {
        this.kopId = kopId;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ComContactPhonePK)) {
            return false;
        }
        ComContactPhonePK castOther = (ComContactPhonePK)other;
        return
                this.cntId.equals(castOther.cntId)
                        && this.phnId.equals(castOther.phnId)
                        && this.akdId.equals(castOther.akdId)
                        && this.kopId.equals(castOther.kopId);
    }

    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + this.cntId.hashCode();
        hash = hash * prime + this.phnId.hashCode();
        hash = hash * prime + this.akdId.hashCode();
        hash = hash * prime + this.kopId.hashCode();

        return hash;
    }
}
