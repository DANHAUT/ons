package com.edhec.commonedhecapps.model.common;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the COM_JOB database table.
 * 
 */
@Embeddable
public class ComJobPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ARP_CNT_ID")
	private String arpCntId;

	@Column(name="NTP_CNT_ID")
	private String ntpCntId;

	@Column(name="DATE_FROM")
	private java.util.Date dateFrom;

	@Column(name="ORDER_BY")
	private long orderBy;

    public ComJobPK() {
    }
	public String getArpCntId() {
		return this.arpCntId;
	}
	public void setArpCntId(String arpCntId) {
		this.arpCntId = arpCntId;
	}
	public String getNtpCntId() {
		return this.ntpCntId;
	}
	public void setNtpCntId(String ntpCntId) {
		this.ntpCntId = ntpCntId;
	}
	public java.util.Date getDateFrom() {
		return this.dateFrom;
	}
	public void setDateFrom(java.util.Date dateFrom) {
		this.dateFrom = dateFrom;
	}
	public long getOrderBy() {
		return this.orderBy;
	}
	public void setOrderBy(long orderBy) {
		this.orderBy = orderBy;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ComJobPK)) {
			return false;
		}
		ComJobPK castOther = (ComJobPK)other;
		return 
			this.arpCntId.equals(castOther.arpCntId)
			&& this.ntpCntId.equals(castOther.ntpCntId)
			&& this.dateFrom.equals(castOther.dateFrom)
			&& (this.orderBy == castOther.orderBy);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.arpCntId.hashCode();
		hash = hash * prime + this.ntpCntId.hashCode();
		hash = hash * prime + this.dateFrom.hashCode();
		hash = hash * prime + ((int) (this.orderBy ^ (this.orderBy >>> 32)));
		
		return hash;
    }
}