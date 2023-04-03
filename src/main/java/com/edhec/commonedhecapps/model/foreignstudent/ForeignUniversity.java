package com.edhec.commonedhecapps.model.foreignstudent;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the SCO_FOREIGN_UNIVERSITY database table.
 *
 */
@Entity
@Table(schema="SCHOLARSHIP", name="SCO_FOREIGN_UNIVERSITY")
@NamedQueries({
        @NamedQuery(name = "ForeignUniversity.findByUniversityId", query = "select o from ForeignUniversity o where o.idUniversity = :idUniversity and o.dateHistory is null and (o.totalSemester>0 or o.ddSemester>0)"),
        @NamedQuery(name = "ForeignUniversity.findByUniversityIdAndProgram", query = "select o from ForeignUniversity o where o.idUniversity = :idUniversity and o.shyId = :shyId and o.scoYear = :scoYear and o.dateHistory is null and (o.totalSemester>0 or o.ddSemester>0)")
})
public class ForeignUniversity implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String SHYID_EDHEC = "DEVLIL00000000264";
    public static final String SHYID_ESPEME = "DEVLIL00000000265";
    public static final String FUNCTION_COORD_EDHEC = "9135";
    public static final String FUNCTION_COORD_ESPEME = "9136";
    public static final String COORD_MAIL_AKDID = "S";
    public static final String COORD_PHONE_AKDID = "S";
    public static final String COORD_PHONE_KOPID = "TELPRO";

    @Id
    private String id;

    @Column(name="COUNTRY_NAME")
    private String countryName;

    @Column(name="ADDRESS")
    private String address;

    @Column(name="ZIPCODE")
    private String zipcode;

    @Column(name="TOWN")
    private String town;

    @Column(name="CREATED_BY")
    private String createdBy;

    @Temporal( TemporalType.DATE)
    @Column(name="DATE_CREATED")
    private Date dateCreated;

    @Temporal( TemporalType.DATE)
    @Column(name="DATE_HISTORY")
    private Date dateHistory;

    @Temporal( TemporalType.DATE)
    @Column(name="DATE_MODIFIED")
    private Date dateModified;

    @Column(name="ID_UNIVERSITY")
    private String idUniversity;

    @Column(name="MODIFIED_BY")
    private String modifiedBy;

    @Column(name="SCO_YEAR")
    private BigDecimal scoYear;

    @Column(name="SHY_ID")
    private String shyId;

    @Column(name="TOTAL_SEMESTER")
    private BigDecimal totalSemester;

    @Column(name="UNIVERSITY_NAME")
    private String universityName;

    @Column(name="UNIVERSITY_NUMBER")
    private BigDecimal universityNumber;

    @Column(name="USED_SEMESTER")
    private BigDecimal usedSemester;

    @Column(name="DD_SEMESTER")
    private BigDecimal ddSemester;

    @Column(name="USED_DD_SEMESTER")
    private BigDecimal usedDdSemester;

    @Column(name="CODE_ERASMUS")
    private String codeErasmus;

    @Column(name="OLA")
    private String ola;

    @OneToMany(mappedBy="university")
    private List<ForeignStudent> foreignStudentList;

    public ForeignUniversity() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountryName() {
        return this.countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
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

    public Date getDateHistory() {
        return this.dateHistory;
    }

    public void setDateHistory(Date dateHistory) {
        this.dateHistory = dateHistory;
    }

    public Date getDateModified() {
        return this.dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public String getIdUniversity() {
        return this.idUniversity;
    }

    public void setIdUniversity(String idUniversity) {
        this.idUniversity = idUniversity;
    }

    public String getModifiedBy() {
        return this.modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public BigDecimal getScoYear() {
        return this.scoYear;
    }

    public void setScoYear(BigDecimal scoYear) {
        this.scoYear = scoYear;
    }

    public String getShyId() {
        return this.shyId;
    }

    public void setShyId(String shyId) {
        this.shyId = shyId;
    }

    public BigDecimal getTotalSemester() {
        return this.totalSemester;
    }

    public void setTotalSemester(BigDecimal totalSemester) {
        this.totalSemester = totalSemester;
    }

    public String getUniversityName() {
        return this.universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public BigDecimal getUniversityNumber() {
        return this.universityNumber;
    }

    public void setUniversityNumber(BigDecimal universityNumber) {
        this.universityNumber = universityNumber;
    }

    public BigDecimal getUsedSemester() {
        return this.usedSemester;
    }

    public void setUsedSemester(BigDecimal usedSemester) {
        this.usedSemester = usedSemester;
    }

    public List<ForeignStudent> getForeignStudentList() {
        return foreignStudentList;
    }

    public void setForeignStudentList(List<ForeignStudent> foreignStudentList) {
        this.foreignStudentList = foreignStudentList;
    }

    public BigDecimal getDdSemester() {
        return ddSemester;
    }

    public void setDdSemester(BigDecimal ddSemester) {
        this.ddSemester = ddSemester;
    }

    public BigDecimal getUsedDdSemester() {
        return usedDdSemester;
    }

    public void setUsedDdSemester(BigDecimal usedDdSemester) {
        this.usedDdSemester = usedDdSemester;
    }

    public String getCodeErasmus() {
        return codeErasmus;
    }

    public void setCodeErasmus(String codeErasmus) {
        this.codeErasmus = codeErasmus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getOla() {
        return ola;
    }

    public void setOla(String ola) {
        this.ola = ola;
    }
}
