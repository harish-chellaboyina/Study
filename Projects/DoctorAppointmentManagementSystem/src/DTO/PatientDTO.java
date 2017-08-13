package DTO;


/*
 * This class represents the PatientDTO
 * The properties of PatientDTO are
 * 		1. patientId
 * 		2. username
 * 		3. password
 * 		4. email
 */
public class PatientDTO {
	private int patientId;
	private String username;
	private String password;
	private String email;

	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
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

}
