package com.edhec.commonedhecapps.model.common;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the COM_PHONE database table.
 *
 */
@Entity
@Table(schema = "COMMON", name="COM_PHONE")
public class ComPhone implements Serializable {
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

    @Column(name="MODIFIED_BY")
    private String modifiedBy;

    private String phone;

    //bi-directional many-to-one association to ComContactPhone
    @OneToMany(mappedBy="comPhone")
    private List<ComContactPhone> comContactPhoneList;

    public ComPhone() {
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

    public String getModifiedBy() {
        return this.modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<ComContactPhone> getComContactPhoneList() {
        return this.comContactPhoneList;
    }

    public void setComContactPhoneList(List<ComContactPhone> comContactPhoneList) {
        this.comContactPhoneList = comContactPhoneList;
    }

}
