package com.edhec.commonedhecapps.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * The primary key class for the GROUP_AUTHORITIES database table.
 *
 */
@Embeddable
public class GroupAuthorityPK implements Serializable {
    //default serial version id, required for serializable classes.
    private static final long serialVersionUID = 1L;

    @Column(name="GROUP_ID")
    private long groupId;

    private String authority;

    public GroupAuthorityPK() {
    }
    public long getGroupId() {
        return this.groupId;
    }
    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }
    public String getAuthority() {
        return this.authority;
    }
    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GroupAuthorityPK)) {
            return false;
        }
        GroupAuthorityPK castOther = (GroupAuthorityPK)other;
        return
                (this.groupId == castOther.groupId)
                        && this.authority.equals(castOther.authority);

    }

    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + ((int) (this.groupId ^ (this.groupId >>> 32)));
        hash = hash * prime + this.authority.hashCode();

        return hash;
    }
}
