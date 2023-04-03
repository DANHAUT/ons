package com.edhec.commonedhecapps.model.common;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the COM_CONTACT database table.
 *
 */
@Entity
@Table(schema = "COMMON", name="COM_CONTACT")
public class ComContact implements Serializable {
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

    @Column(name="INTERNAL_REFERENCE")
    private String internalReference;

    @Temporal(TemporalType.DATE)
    @Column(name="LAST_UPDATED")
    private Date lastUpdated;

    private String login;

    @Column(name="MODIFIED_BY")
    private String modifiedBy;

    private String name;

    private String password;

    @Column(name="WEB_CODE")
    private String webCode;

    //bi-directional many-to-one association to ComContactMail
    @OneToMany(mappedBy="comContact")
    private List<ComContactMail> comContactMailList;

    //bi-directional many-to-one association to ComContactNationality
    @OneToMany(mappedBy="comContact")
    private List<ComContactNationality> comContactNationalityList;

    //bi-directional many-to-one association to ComContactPhone
    @OneToMany(mappedBy="comContact")
    private List<ComContactPhone> comContactPhoneList;

    //bi-directional one-to-one association to ComNaturalPerson
    @OneToOne(mappedBy="comContact", fetch=FetchType.LAZY)
    private ComNaturalPerson comNaturalPerson;

    public ComContact() {
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

    public String getInternalReference() {
        return this.internalReference;
    }

    public void setInternalReference(String internalReference) {
        this.internalReference = internalReference;
    }

    public Date getLastUpdated() {
        return this.lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getModifiedBy() {
        return this.modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWebCode() {
        return this.webCode;
    }

    public void setWebCode(String webCode) {
        this.webCode = webCode;
    }

    public List<ComContactMail> getComContactMailList() {
        return this.comContactMailList;
    }

    public void setComContactMailList(List<ComContactMail> comContactMailList) {
        this.comContactMailList = comContactMailList;
    }

    public List<ComContactNationality> getComContactNationalityList() {
        return this.comContactNationalityList;
    }

    public void setComContactNationalityList(List<ComContactNationality> comContactNationalityList) {
        this.comContactNationalityList = comContactNationalityList;
    }

    public List<ComContactPhone> getComContactPhoneList() {
        return this.comContactPhoneList;
    }

    public void setComContactPhoneList(List<ComContactPhone> comContactPhoneList) {
        this.comContactPhoneList = comContactPhoneList;
    }

    public ComNaturalPerson getComNaturalPerson() {
        return this.comNaturalPerson;
    }

    public void setComNaturalPerson(ComNaturalPerson comNaturalPerson) {
        this.comNaturalPerson = comNaturalPerson;
    }
}
