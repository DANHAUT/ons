package com.edhec.commonedhecapps.model.foreignstudent;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the SCO_FOREIGN_STUDENT database table.
 *
 */
@Entity
@Table(schema="SCHOLARSHIP", name="SCO_FOREIGN_STUDENT")
@NamedQueries({
        @NamedQuery(name = "ForeignStudent.findByUniversityId", query = "select o from ForeignStudent o where o.idUniversity = :idUniversity order by o.lastName asc"),
        @NamedQuery(name = "ForeignStudent.findById", query = "select o from ForeignStudent o where o.id = :id "),
        @NamedQuery(name = "ForeignStudent.findByCntIdTemp", query = "select o from ForeignStudent o where o.cntIdTemp = :cntIdTemp"),
        @NamedQuery(name = "ForeignStudent.findByCntId", query = "select o from ForeignStudent o where o.cntId = :cntId"),
        @NamedQuery(name = "ForeignStudent.findByIdNotHistory", query = "select o from ForeignStudent o where o.id = :id and o.dateHistory is null")
})
public class ForeignStudent implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private String address;

    private String birthcountry;

    private Date birthdate;

    private String birthplace;

    private String campus;

    private String city;

    private String country;

    @Column(name="CREATED_BY")
    private String createdBy;

    @Column(name="DATE_CREATED")
    private Date dateCreated;

    @Column(name="DATE_HISTORY")
    private Date dateHistory;

    @Column(name="DATE_MODIFIED")
    private Date dateModified;

    private String email;

    private String currentyear;

    private String currentyearoutof;

    @Column(name="PERIODE_CODE")
    private String periodeCode;

    @Column(name="MODULE_CODE")
    private String moduleCode;

    private String fall;

    @Column(name="FIRST_NAME")
    private String firstName;

    private String gender;

    @Column(name="ID_UNIVERSITY")
    private String idUniversity;

    @Column(name="CNT_ID_TEMP")
    private String cntIdTemp;

    @Column(name="CNT_ID")
    private String cntId;

    private String language;

    @Column(name="LAST_NAME")
    private String lastName;

    @Column(name="MODIFIED_BY")
    private String modifiedBy;

    private String nationality;

    @Column(name="SHY_ID")
    private String shyId;

    @Column(name="SCO_YEAR")
    private BigDecimal scoYear;

    private String spring;

    private String studylevel;

    private String telephone;

    private String winter;

    private String comments;

    @Column(name="DBLEDIPLOM")
    private String doubleDiploma;

    private String help;

    @Column(name="URL_ID")
    private String urlid;

    @Column(name="CURRENT_PROGRAM")
    private String currentProgram;

    @Column(name="EDHEC_PROGRAM")
    private String edhecProgram;

    @Column(name="INSURANCE")
    private String insurance;


    @OneToOne
    @JoinColumn(name = "ID", referencedColumnName="FS_ID",insertable = false, updatable = false)
    private ForeignStudentStatusView foreignStudentView;


    @OneToMany(mappedBy="foreignStudent",fetch = FetchType.LAZY)
    @Cascade( { org.hibernate.annotations.CascadeType.ALL,org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
    private List<ForeignStudentStatus> foreignStudentStatusList;


    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumns( {
            @JoinColumn(name="ID_UNIVERSITY", referencedColumnName="ID_UNIVERSITY", unique = false, nullable = false, insertable = false, updatable = false),
            @JoinColumn(name="SHY_ID", referencedColumnName="SHY_ID", unique = false, nullable = false, insertable = false, updatable = false),
            @JoinColumn(name="SCO_YEAR", referencedColumnName="SCO_YEAR", unique = false, nullable = false, insertable = false, updatable = false)
    })
    private ForeignUniversity university;

    public ForeignStudent() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthcountry() {
        return this.birthcountry;
    }

    public void setBirthcountry(String birthcountry) {
        this.birthcountry = birthcountry;
    }

    public Date getBirthdate() {
        return this.birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getBirthplace() {
        return this.birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public String getCampus() {
        return this.campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFall() {
        return this.fall;
    }

    public void setFall(String fall) {
        this.fall = fall;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdUniversity() {
        return this.idUniversity;
    }

    public void setIdUniversity(String idUniversity) {
        this.idUniversity = idUniversity;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getModifiedBy() {
        return this.modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getNationality() {
        return this.nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getShyId() {
        return this.shyId;
    }

    public void setShyId(String shyId) {
        this.shyId = shyId;
    }

    public String getSpring() {
        return this.spring;
    }

    public void setSpring(String spring) {
        this.spring = spring;
    }

    public String getStudylevel() {
        return this.studylevel;
    }

    public void setStudylevel(String studylevel) {
        this.studylevel = studylevel;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getWinter() {
        return this.winter;
    }

    public void setWinter(String winter) {
        this.winter = winter;
    }

    public String getCntIdTemp() {
        return cntIdTemp;
    }

    public void setCntIdTemp(String cntIdTemp) {
        this.cntIdTemp = cntIdTemp;
    }

    public String getCntId() {
        return cntId;
    }

    public void setCntId(String cntId) {
        this.cntId = cntId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public List<ForeignStudentStatus> getForeignStudentStatusList() {
        return foreignStudentStatusList;
    }

    public void setForeignStudentStatusList(
            List<ForeignStudentStatus> foreignStudentStatusList) {
        this.foreignStudentStatusList = foreignStudentStatusList;
    }

    public ForeignUniversity getUniversity() {
        return university;
    }

    public void setUniversity(ForeignUniversity university) {
        this.university = university;
    }

    public String getDoubleDiploma() {
        return doubleDiploma;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public void setDoubleDiploma(String doubleDiploma) {
        this.doubleDiploma = doubleDiploma;
    }

    public ForeignStudentStatusView getForeignStudentView() {
        return foreignStudentView;
    }

    public void setForeignStudentView(ForeignStudentStatusView foreignStudentView) {
        this.foreignStudentView = foreignStudentView;
    }

    public BigDecimal getScoYear() {
        return scoYear;
    }

    public void setScoYear(BigDecimal scoYear) {
        this.scoYear = scoYear;
    }

    public String getCurrentyear() {
        return currentyear;
    }

    public void setCurrentyear(String currentyear) {
        this.currentyear = currentyear;
    }

    public String getCurrentyearoutof() {
        return currentyearoutof;
    }

    public void setCurrentyearoutof(String currentyearoutof) {
        this.currentyearoutof = currentyearoutof;
    }

    public String getPeriodeCode() {
        return periodeCode;
    }

    public void setPeriodeCode(String periodeCode) {
        this.periodeCode = periodeCode;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getUrlid() {
        return urlid;
    }

    public void setUrlid(String urlid) {
        this.urlid = urlid;
    }

    public String getCurrentProgram() {
        return currentProgram;
    }

    public void setCurrentProgram(String currentProgram) {
        this.currentProgram = currentProgram;
    }

    public String getEdhecProgram() {
        return edhecProgram;
    }

    public void setEdhecProgram(String edhecProgram) {
        this.edhecProgram = edhecProgram;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }
}
