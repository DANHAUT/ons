package com.edhec.commonedhecapps.web.foreignstudent.command;

import org.hibernate.validator.constraints.NotEmpty;
public class CourseUniversityAbroadCommand {

	@NotEmpty
	private String courseId;
	
	private String domain;
	
	private String academicStudyAbroadId;

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getAcademicStudyAbroadId() {
		return academicStudyAbroadId;
	}

	public void setAcademicStudyAbroadId(String academicStudyAbroadId) {
		this.academicStudyAbroadId = academicStudyAbroadId;
	}
	
	
}
