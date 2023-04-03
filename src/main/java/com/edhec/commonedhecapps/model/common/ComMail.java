package com.edhec.commonedhecapps.model.common;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the COM_MAIL database table.
 *
 */
@Entity
@Table(schema = "COMMON", name="COM_MAIL")
public class ComMail implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Column(name="CREATED_BY")
    private String createdBy;

    @Temporal(TemporalType.DATE)
    @Column(name="DATE_CREATED")
    private Date dateCreated;

    @Temporal(TemporalType.DATE)
    @Column(name="DATE_MODIFIED")
    private Date dateModified;

    private String mail;

    @Column(name="MODIFIED_BY")
    private String modifiedBy;

    private String url;

    //bi-directional many-to-one association to ComContactMail
    @OneToMany(mappedBy="comMail")
    private List<ComContactMail> comContactMailList;

    public ComMail() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
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

    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getModifiedBy() {
        return this.modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<ComContactMail> getComContactMailList() {
        return this.comContactMailList;
    }

    public void setComContactMailList(List<ComContactMail> comContactMailList) {
        this.comContactMailList = comContactMailList;
    }

}
