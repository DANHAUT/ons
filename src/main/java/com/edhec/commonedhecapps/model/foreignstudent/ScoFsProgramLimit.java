package com.edhec.commonedhecapps.model.foreignstudent;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the SCO_FS_PROGRAM_LIMIT database table.
 * 
 */
@Entity
@Table(schema="SCHOLARSHIP", name="SCO_FS_PROGRAM_LIMIT")
@NamedQueries({
  @NamedQuery(name = "ScoFsProgramLimit.existLimit", query = "select o from ScoFsProgramLimit o where o.shyId = :shyId and o.scoYear = :scoYear and (o.campus = :campus or o.periodId = :periodId) and o.levelId = :levelId and o.dateLimit is null"),
  @NamedQuery(name = "ScoFsProgramLimit.dateLimit", query = "select o from ScoFsProgramLimit o where (o.shyId = :shyId or o.shyId is null) and (o.scoYear = :scoYear or o.scoYear is null) and o.dateLimit is not null")
})
public class ScoFsProgramLimit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String campus;

	@Column(name="LEVEL_ID")
	private String levelId;

	@Column(name="PERIOD_ID")
	private String periodId;

	private String message;

	@Column(name="SCO_YEAR")
	private String scoYear;

	@Column(name="SHY_ID")
	private String shyId;

	@Column(name="DATE_LIMIT")
	private Date dateLimit;

	@Column(name="START_DATE")
	private Date startDate;

	public ScoFsProgramLimit() {
    }

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCampus() {
		return this.campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}

	public String getLevelId() {
		return this.levelId;
	}

	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}

	public String getPeriodId() {
		return periodId;
	}

	public void setPeriodId(String periodId) {
		this.periodId = periodId;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getScoYear() {
		return this.scoYear;
	}

	public void setScoYear(String scoYear) {
		this.scoYear = scoYear;
	}

	public String getShyId() {
		return this.shyId;
	}

	public void setShyId(String shyId) {
		this.shyId = shyId;
	}

	public Date getDateLimit() {
		return dateLimit;
	}

	public void setDateLimit(Date dateLimit) {
		this.dateLimit = dateLimit;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

}