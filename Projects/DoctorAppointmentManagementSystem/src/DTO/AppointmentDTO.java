package DTO;

import org.apache.commons.lang3.StringUtils;

/*
 * This class represents the AppointmentDTO
 * The properties of AppointmentDTO are
 * 		1. patientUserName
 * 		2. appointmentId
 * 		3. doctorUserName
 * 		4. doctorName
 * 		5. patientName
 * 		6. patientRemarks
 * 		7. doctorRemarks
 * 		8. appointmentDate
 * 		9. appointmentSlot
 */
public class AppointmentDTO {
	
	private int patientUserName;
	private int appointmentId;
	private int doctorUserName;
	private String doctorName;
	private String patientName;
	private String patientRemarks = "";
	private String doctorRemarks = "";
	private String appointmentDate;
	private String appointmentSlot;
	
	public int getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}


	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public int getPatientUserName() {
		return patientUserName;
	}
	public void setPatientUserName(int patientUserName) {
		this.patientUserName = patientUserName;
	}
	public int getDoctorUserName() {
		return doctorUserName;
	}
	public void setDoctorUserName(int doctorUserName) {
		this.doctorUserName = doctorUserName;
	}
	public String getPatientRemarks() {
		return patientRemarks;
	}
	public void setPatientRemarks(String patientRemarks) {
		this.patientRemarks = patientRemarks;
	}
	public String getDoctorRemarks() {
		return doctorRemarks;
	}
	public void setDoctorRemarks(String doctorRemarks) {
		this.doctorRemarks = doctorRemarks;
	}
	public String getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public String getAppointmentSlot() {
		return appointmentSlot;
	}
	public void setAppointmentSlot(String appointmentSlot) {
		this.appointmentSlot = appointmentSlot;
	}
}