package com.edhec.commonedhecapps.model.common;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the COM_CRITERIA database table.
 *
 */
@Entity
@FilterDef(name="comCriteriaLViewlngFilter",
        parameters=@ParamDef( name="lngFilterParam", type="string" ) )
@Table(schema = "COMMON", name="COM_CRITERIA")
public class ComCriteria implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ComCriteriaPK id;

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

    //bi-directional many-to-one association to ComCriteriaL
    @OneToMany(mappedBy="comCriteria", fetch = FetchType.EAGER)
    @Filter(
            name = "comCriteriaLViewlngFilter",
            condition="LNG_ID = :lngFilterParam"
    )
    @Fetch(FetchMode.SUBSELECT)
    private List<ComCriteriaLView> comCriteriaLViewList;

    //bi-directional many-to-one association to ComNaturalPerson
    @OneToMany(mappedBy="comCriteria")
    private List<ComNaturalPerson> comNaturalPersonList;

    public ComCriteria() {
    }

    public ComCriteriaPK getId() {
        return this.id;
    }

    public void setId(ComCriteriaPK id) {
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

    public List<ComCriteriaLView> getComCriteriaLViewList() {
        return comCriteriaLViewList;
    }

    public void setComCriteriaLViewList(List<ComCriteriaLView> comCriteriaLViewList) {
        this.comCriteriaLViewList = comCriteriaLViewList;
    }

    public List<ComNaturalPerson> getComNaturalPersonList() {
        return this.comNaturalPersonList;
    }

    public void setComNaturalPersonList(List<ComNaturalPerson> comNaturalPersonList) {
        this.comNaturalPersonList = comNaturalPersonList;
    }

}
