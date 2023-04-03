package com.edhec.commonedhecapps.model;

import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the GROUPS database table.
 *
 */
@Entity
@Table(schema = "CAS",name="GROUPS")
public class Group implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private long id;

    @Column(name="CREATED_BY")
    private String createdBy;

    @Temporal( TemporalType.DATE)
    @Column(name="DATE_CREATED")
    private Date dateCreated;

    @Temporal( TemporalType.DATE)
    @Column(name="DATE_MODIFIED")
    private Date dateModified;

    @Column(name="GROUP_NAME")
    private String groupName;

    @Column(name="MODIFIED_BY")
    private String modifiedBy;

    private String source;

    //bi-directional many-to-one association to GroupAuthority
    @OneToMany(mappedBy="groupAssociated", fetch= FetchType.EAGER)
    //@Fetch(FetchMode.SUBSELECT)
    @BatchSize(size = 20)
    private List<GroupAuthority> groupAuthorities;

    //bi-directional many-to-one association to GroupMember
    @OneToMany(mappedBy="groupAssociated")
    private List<GroupMember> groupMembers;

    public Group() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
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

    public String getGroupName() {
        return this.groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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

    public List<GroupAuthority> getGroupAuthorities() {
        return this.groupAuthorities;
    }

    public void setGroupAuthorities(List<GroupAuthority> groupAuthorities) {
        this.groupAuthorities = groupAuthorities;
    }

    public List<GroupMember> getGroupMembers() {
        return this.groupMembers;
    }

    public void setGroupMembers(List<GroupMember> groupMembers) {
        this.groupMembers = groupMembers;
    }
}
