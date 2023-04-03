package com.edhec.commonedhecapps.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the GROUP_MEMBERS database table.
 *
 */
@Entity
@Table(schema = "CAS", name="GROUP_MEMBERS")
@SequenceGenerator(schema = "CAS", name = "SEQ_STORE", sequenceName = "GROUP_MEMBERS_SEQ", allocationSize = 1)
public class GroupMember implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_STORE")
    private long id;

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
    @JoinColumn(name = "group_id",insertable = false, updatable = false)
    @Fetch(FetchMode.JOIN)
    private Group groupAssociated;

    //bi-directional many-to-one association to User
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="USERNAME")
    private User user;

    public GroupMember() {
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

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
