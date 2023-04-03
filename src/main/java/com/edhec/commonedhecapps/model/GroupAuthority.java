package com.edhec.commonedhecapps.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the GROUP_AUTHORITIES database table.
 *
 */
@Entity
@Table(schema = "CAS", name="GROUP_AUTHORITIES")
public class GroupAuthority implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private GroupAuthorityPK id;

    @Column(name="CREATED_BY")
    private String createdBy;

    @Temporal( TemporalType.DATE)
    @Column(name="DATE_CREATED")
    private Date dateCreated;

    @Temporal( TemporalType.DATE)
    @Column(name="DATE_MODIFIED")
    private Date dateModified;

    @Column(name="MODIFIED_BY")
    private String modifiedBy;

    private String source;

    //bi-directional many-to-one association to Group
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "GROUP_ID",insertable = false, updatable = false)
    private Group groupAssociated;

    public GroupAuthority() {
    }

    public GroupAuthorityPK getId() {
        return this.id;
    }

    public void setId(GroupAuthorityPK id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
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

    public String getModifiedBy() {
        return this.modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Group getGroupAssociated() {
        return groupAssociated;
    }

    public void setGroupAssociated(Group groupAssociated) {
        this.groupAssociated = groupAssociated;
    }

}
