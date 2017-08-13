package DTO;

/*
 * This class represents the DoctorDTO
 * The properties of DoctorDTO are
 * 		1. doctorId
 * 		2. username
 * 		3. password
 * 		4. email
 * 		5. specialization
 */
public class DoctorDTO {
	private int doctorId;
	private String username;
	private String password;
	private String email;
	private String specialization;
	
	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
}
