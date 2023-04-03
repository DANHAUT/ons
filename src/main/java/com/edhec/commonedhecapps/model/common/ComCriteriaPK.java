package com.edhec.commonedhecapps.model.common;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * The primary key class for the COM_CRITERIA database table.
 *
 */
@Embeddable
public class ComCriteriaPK implements Serializable {
    //default serial version id, required for serializable classes.
    private static final long serialVersionUID = 1L;

    private String id;

    @Column(name="CKA_ID")
    private String ckaId;

    public ComCriteriaPK() {
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCkaId() {
        return this.ckaId;
    }
    public void setCkaId(String ckaId) {
        this.ckaId = ckaId;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ComCriteriaPK)) {
            return false;
        }
        ComCriteriaPK castOther = (ComCriteriaPK)other;
        return
                this.id.equals(castOther.id)
                        && this.ckaId.equals(castOther.ckaId);
    }

    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + this.id.hashCode();
        hash = hash * prime + this.ckaId.hashCode();

        return hash;
    }
}
